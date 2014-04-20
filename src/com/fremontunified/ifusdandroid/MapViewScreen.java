package com.fremontunified.ifusdandroid;

import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapViewScreen extends MapActivity {
	
	int position;
	
	GeoPoint point;
	OverlayItem overlayitem;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

	    setContentView(R.layout.mapview);
		
	    position = getIntent().getExtras().getInt("position");
	    
	    //code to center title. Must be on every page
	    
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    
		MapView mapView = (MapView) findViewById(R.id.largemapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Resources res = this.getResources();
		Drawable drawable = res.getDrawable(R.drawable.bluemarker);
		MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable);
	    
	    switch (Global.schoolType) {
			case 0:
				point = new GeoPoint(new Integer(Global.elemLatitude[position]), new Integer(Global.elemLongitude[position]));
				overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
				break;
			case 1:
				point = new GeoPoint(new Integer(Global.middleLatitude[position]), new Integer(Global.middleLongitude[position]));
				overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
				break;
			case 2:
				point = new GeoPoint(new Integer(Global.highLatitude[position]), new Integer(Global.highLongitude[position]));
				overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
				break;
			default: break;
	    }
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		
		MapController mapController = mapView.getController();
		mapController.setCenter(point);
		mapController.setZoom(15);
	}


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}