package com.fremontunified.ifusdandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

public class SchoolInfo extends MapActivity {
	
	Bundle bundle = new Bundle();
	
	int position;
	GeoPoint point;
	String imageUrl;
	
	WebView mapImage;
	boolean openMap = true;
	boolean firstLoad = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    
	    setContentView(R.layout.schoolinfo);
	   
	    //code to center title. Must be on every page
	    
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    
	    imageUrl = "http://maps.google.com/staticmap?zoom=15&markers=";
	    
	    position = getIntent().getExtras().getInt("location");
	    		
//	    ViewFlipper mapFlipper = (ViewFlipper) findViewById(R.id.mapflipper);
	    
	    mapImage = (WebView) findViewById(R.id.mapScreen);
	    mapImage.setClickable(true);
	    mapImage.setOnTouchListener(mapClickListener);
	    mapImage.loadUrl("http://www.google.com");
	    mapImage.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
	        	
                if(firstLoad == false) {
                	imageUrl = imageUrl + "&size=" + mapImage.getWidth() + "x" + mapImage.getHeight() + "&key=0atSfNIlA8dqUGiZ3goe8GDOqReJcx7LUr68qTg";
	        		Log.e("image Url", imageUrl);
	        		Log.e("Width", Integer.toString(mapImage.getWidth()));
	        		mapImage.loadUrl(imageUrl);
	        		firstLoad = true;
                }
            }
        });
	    
	    TextView schoolName = (TextView) findViewById(R.id.schoolName);
	    TextView schoolPrincipal = (TextView) findViewById(R.id.schoolprincipal);
	    TextView schoolSecretary = (TextView) findViewById(R.id.schoolsec);
	    TextView schoolLocation = (TextView) findViewById(R.id.location);
	    
	    Button callButton = (Button) findViewById(R.id.schoolCallButton);
//	    Button websiteButton = (Button) findViewById(R.id.schoolSiteButton);
	    Button headlineButton = (Button) findViewById(R.id.schoolHeadlinesButton);
	    Button eventButton = (Button) findViewById(R.id.schoolEventsButton);
	    Button announcementButton = (Button) findViewById(R.id.schoolAnnouncementsButton);

	    
	    switch (Global.schoolType) {
			case 0:
				imageUrl = imageUrl + Float.valueOf(Global.elemLatitude[position])/1000000 + "," + Float.valueOf(Global.elemLongitude[position])/1000000;
				
				schoolLocation.setText(Global.elemLocation[position]);
				schoolName.setText(Global.elemNames[position]);
				schoolPrincipal.setText(Global.elemPrincipal[position]);
				schoolSecretary.setText(Global.elemSecretary[position]);
				callButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + Global.elemPhone[position]));
					    startActivity(i);
					  }
					});
				headlineButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.elemHeadlines[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				eventButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.elemEvents[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				announcementButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.elemAnnouncements[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				point = new GeoPoint(Integer.valueOf(Global.elemLatitude[position]), Integer.valueOf(Global.elemLongitude[position]));
				break;
			case 1:
				imageUrl = imageUrl + Float.valueOf(Global.middleLatitude[position])/1000000 + "," + Float.valueOf(Global.middleLongitude[position])/1000000;
				
				schoolLocation.setText(Global.middleLocation[position]);
				schoolName.setText(Global.middleNames[position]);
				schoolPrincipal.setText(Global.middlePrincipal[position]);
				schoolSecretary.setText(Global.middleSecretary[position]);
				callButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + Global.middlePhone[position]));
					    startActivity(i);
					  }
					});
				headlineButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.middleHeadlines[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				eventButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.middleEvents[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				announcementButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.middleAnnouncements[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				point = new GeoPoint(Integer.valueOf(Global.middleLatitude[position]), Integer.valueOf(Global.middleLongitude[position]));
				break;
			case 2:
				imageUrl = imageUrl + Float.valueOf(Global.highLatitude[position])/1000000 + "," + Float.valueOf(Global.highLongitude[position])/1000000;
				
				schoolLocation.setText(Global.highLocation[position]);
				schoolName.setText(Global.highNames[position]);
				schoolPrincipal.setText(Global.highPrincipal[position]);
				schoolSecretary.setText(Global.highSecretary[position]);
				callButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + Global.highPhone[position]));
					    startActivity(i);
					  }
					});
				headlineButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.highHeadlines[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				eventButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.highEvents[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				announcementButton.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
					    Intent i = new Intent(SchoolInfo.this, Website.class);
					    bundle.putString("url", Global.highAnnouncements[position]);
					    bundle.putString("title", "School Website");
			    		i.putExtras(bundle);
					    startActivity(i);
					  }
					});
				point = new GeoPoint(Integer.valueOf(Global.highLatitude[position]), Integer.valueOf(Global.highLongitude[position]));
				break;
			default: break;

	    }

	}
	
	@Override
	public void onResume() {
		super.onResume();
		openMap = true;
	}
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	private OnTouchListener mapClickListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent e){
			
			if(openMap == true) {
				openMap = false;
				Intent intent = new Intent(SchoolInfo.this, MapViewScreen.class);
				intent.putExtra("postition", position);
				startActivity(intent);
			}
    		return false;
		}
	};
		
}