package personal.wl.mobilepointapp.webservice;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import static personal.wl.mobilepointapp.common.AppConstant.Method_QUERY_PRODUCT_BY_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.WSDL_NAMESPACE;
import static personal.wl.mobilepointapp.common.AppConstant.WSDL_URI;

public class CallWebservices extends AsyncTask<String, Integer, String>  implements WebServiceInterface{
    private final String TAG = "webservice";
    private String method;
    private String para;
    private String para_value;
    private JSONArray jsobj;

    private WebServiceInterface webServiceInterface;


    public CallWebservices(WebServiceInterface wi,String method, String para, String para_value)  {
        this.method = method;
        this.para = para;
        this.para_value = para_value;
        this.webServiceInterface=wi;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        try {
            String soapAction = WSDL_NAMESPACE + "/" + Method_QUERY_PRODUCT_BY_BARCODE;
            SoapObject request = new SoapObject(WSDL_NAMESPACE, this.method);
            request.addProperty(WSDL_NAMESPACE, this.para, this.para_value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
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
