package com.isummon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.BMapManager;
import com.isummon.R;
import com.isummon.view.ISummonMapView;


public class MainActivity extends Activity {
    private BMapManager mBMapMan;
    private ISummonMapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBMapMan = ((TestApplication) this.getApplication()).getBMapManager();
        setContentView(R.layout.activity_main);

        mMapView = (ISummonMapView) findViewById(R.id.bmapsView);
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

        switch (item.getItemId()) {
            case R.id.menu_all_act:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_exit:
                android.os.Process.killProcess(android.os.Process.myPid());

        }


        return super.onOptionsItemSelected(item);
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
