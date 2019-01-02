package personal.wl.mobilepointapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.ui.base.BaseActivity;


public class LocationActivity extends BaseActivity implements CloudListener {

    LocationClient mLocationClient;
    public MyLocationListener mLocationListener = new MyLocationListener();
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
        CloudManager.getInstance().init(LocationActivity.this);
        initBaiduMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        //销毁定位
        mLocationClient.stop();
        //关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    public void searchAround(View view) {
        NearbySearchInfo info = new NearbySearchInfo();
        info.ak = AppConstant.BAIDUMAP_LBS_AK;
        info.geoTableId = AppConstant.BAIDUMAP_LBS_TABLE_ID;
        info.radius = 10000;
        info.location = "114.347138,29.856274";
        CloudManager.getInstance().nearbySearch(info);
    }

    private void initBaiduMap() {
        //地图初始化
        mBaiduMap = mMapView.getMap();
        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(this);
        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);//打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.location_map_view);
    }

    @Override
    public void onGetSearchResult(CloudSearchResult cloudSearchResult, int i) {
        if (cloudSearchResult != null && cloudSearchResult.poiList != null
                && cloudSearchResult.poiList.size() > 0) {
            //清除
            mBaiduMap.clear();
            LatLng latLng;
            BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (CloudPoiInfo poiInfo : cloudSearchResult.poiList) {
                latLng = new LatLng(poiInfo.latitude,poiInfo.longitude);
                MarkerOptions options = new MarkerOptions().icon(descriptor).position(latLng);
                mBaiduMap.addOverlay(options);
                builder.include(latLng);
            }
        }
    }

    @Override
    public void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i) {

    }

    @Override
    public void onGetCloudRgcResult(CloudRgcResult cloudRgcResult, int i) {

    }


    /**
     * 定位监听
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //MapView被销毁之后不再接收新的位置
            if (bdLocation == null || mMapView == null) {
                return;
            }
            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .direction(100)//设置获取到的方向信息，顺时针0~360
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locationData);
            if (isFirstLoc) {
                isFirstLoc = false;
                Log.i("Location",bdLocation.getLatitude()+","+bdLocation.getLongitude());
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newMapStatus(builder.target(latLng).zoom(18.0f).build());
                mBaiduMap.setMapStatus(statusUpdate);
                mBaiduMap.animateMapStatus(statusUpdate);
            }

            Log.d(TAG, "SUCCESS: ");
            Log.i(TAG, "城市代码--->" +
                    bdLocation.getCityCode() + "\t经度--->" +
                    bdLocation.getLatitude() + "\t纬度-->" +
                    bdLocation.getLongitude() + "\t使用的定位类型-->" +
                    bdLocation.getCoorType() + "\t国家-->" +
                    bdLocation.getCountry() + "\t城市-->" +
                    bdLocation.getCity() + "\t地址-->" +
                    bdLocation.getAddrStr());

        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
