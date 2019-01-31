package personal.wl.mobilepointapp.webservice;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.common.AppConstant;

import static personal.wl.mobilepointapp.common.AppConstant.Method_QUERY_PRODUCT_BY_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.WSDL_NAMESPACE;
import static personal.wl.mobilepointapp.common.AppConstant.WSDL_URI;

public class CallWebservices extends AsyncTask<String, Integer, String> implements WebServiceInterface {
    private final String TAG = "webservice";
    private String method;
    private String para;
    private String para_value;
    private List<WebServicePara> paraList = new ArrayList<>();
    private WebServicePara parain;
    private JSONArray jsobj;

    private WebServiceInterface webServiceInterface;


    public CallWebservices(WebServiceInterface wi, String method, List<WebServicePara> paraList) {
        this.method = method;
        this.para = para;
        this.para_value = para_value;
        this.webServiceInterface = wi;
        this.paraList = paraList;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        try {
            String soapAction = WSDL_NAMESPACE + "/" + Method_QUERY_PRODUCT_BY_BARCODE;
            SoapObject request = new SoapObject(WSDL_NAMESPACE, this.method);

            for (int i = 0; i < paraList.size(); i++) {
                parain = paraList.get(i);
                request.addProperty(WSDL_NAMESPACE, parain.getPara_name(), parain.getPara_value());
            }


            //加入soap header 头部认证
            Element headers[] = new Element[1];
            headers[0] = new Element().createElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
//            headers[0].setAttribute(null, "soap:mustUnderstand", "1");

            Element to = new Element().createElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "UsernameToken");
            Element action1 = new Element().createElement(null, "n0:Username");
            action1.addChild(Node.TEXT, AppConstant.WSDL_USERNAME);
            to.addChild(Node.ELEMENT, action1);
            Element action2 = new Element().createElement(null, "n0:Password");
//            action2.setAttribute(null, "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
            action2.setAttribute(null, "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
            action2.addChild(Node.TEXT, AppConstant.WSDL_PASSWORD);
            to.addChild(Node.ELEMENT, action2);
            headers[0].addChild(Node.ELEMENT, to);





            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
            envelope.headerOut = headers;
            envelope.bodyOut = request;
            envelope.dotNet = false;
            envelope.implicitTypes = true;
            envelope.setAddAdornments(false);
            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
            httpTransportSE.call(soapAction, envelope);
            Object object = (Object) envelope.getResponse();
            String jsonstr = object.toString();
            jsobj = new JSONArray(jsonstr);

        } catch (Exception e) {
            Log.i(TAG, "doInBackground: " + e.toString());
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(String result) {
        // 将WebService返回的结果显示在TextView中
        this.webServiceInterface.onRecevicedResult(jsobj);
    }


    @Override
    public void onRecevicedResult(JSONArray jsonArray) {

    }
}
