package com.isummon.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.isummon.R;


public class MainActivity extends Activity {
	BMapManager mBMapMan = null;
	private MyOverlay mOverlay = null;
	MapView mMapView = null;
	String TAG = "BMTest";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mBMapMan = ((TestApplication) this.getApplication()).getBMapManager();
		setContentView(R.layout.activity_main);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		 /**
         *  设置地图是否响应点击事件  .
         */
		mMapView.getController().enableClick(true);
		
	

		// 设置启用内置的缩放控件
		MapController mMapController = mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		// 用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
		mMapController.setCenter(getDefaultGeoPoint());// 设置地图中心点
		mMapController.setZoom(getDefaultZoomClass());// 设置地图zoom级别
		
		
		initOverlay();
		mMapView.regMapTouchListner(new MKMapTouchListener() {
			
			@Override
			public void onMapLongClick(GeoPoint point) {
				// TODO Auto-generated method stub
				showAddActFragment();

////				Toast.makeText(getApplicationContext(), point.toString(), Toast.LENGTH_LONG).show();
//				final int longitude = point.getLatitudeE6();
//				final int latitude = point.getLatitudeE6();
//
////				Dialog alertDialog =new AlertDialog.Builder(MainActivity.this).setTitle("hahha").create();
////				alertDialog.show();
//				new AlertDialog.Builder(MainActivity.this).setTitle("添加新的活动")
//				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						Intent intent = new Intent();
//						intent.setClass(getApplicationContext(), AddActivity.class);
//						intent.putExtra("longitude", longitude);
//						intent.putExtra("latitude", latitude);
//						MainActivity.this.startActivity(intent);
//						//startActivityForResult;-----------------------------
//					}
//				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						return;
//					}
//				}).create().show();
				
				mOverlay.addItem(new OverlayItem(point, "hha", "heh"));
				mMapView.refresh();
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

    private void showAddActFragment() {
        new AddActPopupWindow().showAtLocation(
                findViewById(R.id.bmapsView),
                Gravity.TOP,
                0, 0 );
    }
	
	public void initOverlay(){
         mOverlay = new MyOverlay(getResources().getDrawable(R.drawable.icon_gcoding),mMapView);	
         
         GeoPoint p1 = new GeoPoint ((int)(31.195*1E6),(int)(121.604*1E6));
         OverlayItem item1 = new OverlayItem(p1,"覆盖物1","");
         item1.setMarker(getResources().getDrawable(R.drawable.icon_gcoding));
         mOverlay.addItem(item1);
         mMapView.getOverlays().add(mOverlay);
         
         
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

	
	
	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mMapView.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()){
			case R.id.view_all_act : 
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ListActivity.class);
				startActivity(intent);
				break;
			case R.id.exit_all:
				android.os.Process.killProcess(android.os.Process.myPid());  
			
		}
		
	
		return super.onOptionsItemSelected(item);
	}

	/************************* Inner class, itemized layout *********/
	public class MyOverlay extends ItemizedOverlay<OverlayItem> {

		public MyOverlay(Drawable defaultMarker, MapView mapView) {
			super(defaultMarker, mapView);
			// TODO Auto-generated constructor stub
		}
		
//		public boolean onTap(GeoPoint pt , MapView mMapView){
//			Toast.makeText(getApplicationContext(),"22"+  pt.toString(), Toast.LENGTH_LONG).show();
//			return false;
//			
//		}
		
		protected boolean onTap(int index){
			getItem(index);
		
			
//			Toast.makeText(getApplicationContext(), "item index: " + index + " content: " + item.getTitle(), Toast.LENGTH_SHORT).show();
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), ShowActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
			
			return false;
		}
		
		

	}

	/*********************** Private method ************************/
	private GeoPoint getDefaultGeoPoint() {
		GeoPoint point = new GeoPoint((int) (31.195719 * 1E6),
				(int) (121.604423 * 1E6));
		return point;
	}

	private int getDefaultZoomClass() {
		// zoom from 3 to 19,
		return 19;
	}

    private class AddActPopupWindow extends PopupWindow {
        private AddActPopupWindow() {
            super(getLayoutInflater().inflate(R.layout.window_add_act, null),
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);
            setBackgroundDrawable(getResources().getDrawable(R.drawable.logbg));
            setFocusable(true);
            setTouchable(true);
            setOutsideTouchable(false);

//            setOnDismissListener(new OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    AddActPopupWindow.this.dismiss();
//                }
//            });
        }
    }

}

//class MyAlertDialog extends AlertDialog{
//	private GeoPoint mGeopoint = null;
//	private Context context = null;
//	public MyAlertDialog(Context context, GeoPoint geo) {
//		super(context);
//		this.mGeopoint = geo;
//		this.context = context;
//		// TODO Auto-generated constructor stub
//		
//		
//	}
//	
//	public AlertDialog getMyDialog(){
//		return new AlertDialog.Builder(context).setTitle("添加新的活动")
//				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						Intent intent = new Intent();
//						intent.setClass(getContext(), AddActivity.class);
//						intent.putExtra("long", mGeopoint.getLongitudeE6());
//						intent.putExtra("lati", mGeopoint.getLatitudeE6());
//						getOwnerActivity().startActivity(intent);
//					}
//				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//					
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						return;
//					}
//				}).create();
//	}
//	
//
//	
//}
