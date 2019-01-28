package personal.wl.mobilepointapp.common;

/**
 * Created by asus on 2016/8/28.
 */
public class AppConstant {

    /**
     * SharePreference存储路径
     */
    public static final String SHARE_PATH = "MobilePointApp";

    /**
     * 百度地图LBS云检索服务器端AK
     */
    public static final String BAIDUMAP_LBS_AK = "E84ymUzHfE4SK03DUfpDEPTS4t8tGPzh";
    /**
     * 云检索数据表ID
     */
    public static final int BAIDUMAP_LBS_TABLE_ID = 151092;

    /**
     * Bmob的AppID
     */
    public static final String BMOB_AppID = "54a361492568a4b5d169f70622f9752f";

    /**
     * 数据请求基地址
     */
    public static final String BASE_URL = "http://192.168.168.169/apk/";
//    public static final String BASE_URL = "http://7xij5m.com1.z0.glb.clouddn.com/";

    /**
     * 猜你喜欢
     */
    public static final String RECOMMEND_URL = BASE_URL + "spRecommend.txt";

    /**
     * 热门电影
     */
    public static final String HOT_FILM_URL = BASE_URL + "filmHot_refresh.txt";

    /**
     * 用户ID Key(Bmob)
     */
    public static final String KEY_USER_ID = "userId";

    /**
     * 城市key
     */
    public static final String KEY_CITY = "city";

    public static final String PAY_PLUGIN_NAME = "bp.db";


    public static final String WSDL_URI = "http://192.168.81.136:8000/?wsdl";
    public static final String WSDL_NAMESPACE = "http://shangyi.weiliang.webservice";

    public static final String Method_QUERY_PRODUCT_BY_BARCODE = "get_product_by_barcode";
    public static final String Method_GET_FUNCTION_MENU_ALL = "get_functionmenu_all";
    public static final String Method_GET_BRANCH_PAYMENNT_ = "Get_Branch_PayMent";
    public static final String Method_GET_BRANCH_EMPLOYEE_ = "Get_Branch_Employee_all";

    public static final String PARA_GET_FUNCTION_MENU_ALL = "branchcode";

    public static final String PARA_BRANCHCODE = "branchcode";
    public static final String PARA_BARCODE = "barcode";
    public static final String PARA_PRONAME = "proname";
    public static final String PARA_DOMAIN_ACCOUNT = "domainaccount";


    public static final int SKU_SELECT_RESULT_CODE = 8112;
    public static final int PAYMMENT_SELECT_RESULT_CODE = 8122;
    public static final int PAYMMENT_NEED_PAY_CODE = 8132;
    public static final int OPERATOR_NEED_CODE = 8142;


    public static final String SKU_SELECT_RESULT_EXTRA_CODE = "skuselected";
    public static final String PAYMMENT_SELECT_RESULT_EXTRA_CODE = "PAYMMENTS";
    public static final String OPERATOR_SELECT_RESULT_EXTRA_CODE = "OPERATOR";

    public static final String PAYMMENT_NEED_PAY_EXTRA_CODE = "PAYMMENTS_AMAOUNT";



    public static final String CURRENT_TRANSACATIONS="current_transcations";
    public static final String CURRENT_PAYMENT="current_payment";
    public static final String CURRENT_SALEORDERS="current_saleorders";
    public static final String CURRENT_OPERATOR="current_opertator";



}
