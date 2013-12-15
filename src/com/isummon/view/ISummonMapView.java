package com.isummon.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.isummon.R;
import com.isummon.activity.AddActivity;
import com.isummon.activity.ShowActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by horzwxy on 12/15/13.
 */
public class ISummonMapView extends MapView {


    private MyOverlay mOverlay = null;

    public ISummonMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setBuiltInZoomControls(true);
        /**
         *  设置地图是否响应点击事件  .
         */
        getController().enableClick(true);


        // 设置启用内置的缩放控件
        MapController mMapController = getController();
        // 得到mMapView的控制权,可以用它控制和驱动平移和缩放
        // 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        mMapController.setCenter(getDefaultGeoPoint());// 设置地图中心点
        mMapController.setZoom(getDefaultZoomClass());// 设置地图zoom级别


        initOverlay();
        //mMapView.reg
        regMapTouchListner(new MKMapTouchListener() {

            @Override
            public void onMapLongClick(GeoPoint point) {

                final int longitude = point.getLatitudeE6();
                final int latitude = point.getLatitudeE6();

                mOverlay.addItem(new OverlayItem(point, "hha", "heh"));
                refresh();

                // I don't know why, but MapView completes invalidating not before the AddActivity starts
                // So I cannot see the newly-added balloon through the AddActivity background
                // this is the dummy solution: delay starting activity
                // todo
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showAddActActivity(longitude, latitude);
                    }
                }, 500);

            }

            @Override
            public void onMapDoubleClick(GeoPoint arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onMapClick(GeoPoint arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void setLongTouchAvailable(boolean isAvailable) {

    }

    public void showHd(List<Integer> hdIdList) {

    }

    private void showAddActActivity(int longitude, int latitude) {
        Intent intent = new Intent(getContext(), AddActivity.class);
        intent.putExtra("longitude", longitude);
        intent.putExtra("latitude", latitude);
        getContext().startActivity(intent);
    }

    private void initOverlay() {
        mOverlay = new MyOverlay(getContext().getResources().getDrawable(R.drawable.icon_gcoding), this);

        GeoPoint p1 = new GeoPoint((int) (31.195 * 1E6), (int) (121.604 * 1E6));
        OverlayItem item1 = new OverlayItem(p1, "覆盖物1", "");
        item1.setMarker(getResources().getDrawable(R.drawable.icon_gcoding));
        mOverlay.addItem(item1);
        getOverlays().add(mOverlay);


//         //popup overlay
//
//         PopupClickListener popListener = new PopupClickListener() {
//
//			@Override
//			public void onClickedPopup(int arg0) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(), "tost!", Toast.LENGTH_SHORT).show();
//			}
//		};
//
//		pop = new PopupOverlay(mMapView, popListener);
    }

    private GeoPoint getDefaultGeoPoint() {
        GeoPoint point = new GeoPoint((int) (31.195719 * 1E6),
                (int) (121.604423 * 1E6));
        return point;
    }

    private int getDefaultZoomClass() {
        // zoom from 3 to 19,
        return 19;

    }

    /**
     * ********************** Inner class, itemized layout ********
     */
    private class MyOverlay extends ItemizedOverlay<OverlayItem> {

        public MyOverlay(Drawable defaultMarker, MapView mapView) {
            super(defaultMarker, mapView);
            // TODO Auto-generated constructor stub
        }

//		public boolean onTap(GeoPoint pt , MapView mMapView){
//			Toast.makeText(getApplicationContext(),"22"+  pt.toString(), Toast.LENGTH_LONG).show();
//			return false;
//
//		}

        protected boolean onTap(int index) {
            getItem(index);


//			Toast.makeText(getApplicationContext(), "item index: " + index + " content: " + item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getContext().getApplicationContext(), ShowActivity.class);
            intent.putExtra("index", index);
            getContext().startActivity(intent);

            return false;
        }


    }

    /*********************** Private method ************************/
}
