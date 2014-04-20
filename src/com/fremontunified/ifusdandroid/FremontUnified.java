package com.fremontunified.ifusdandroid;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class FremontUnified extends Activity {
    /** Called when the activity is first created. */

	ImageView newsImg;
	ImageView calendarImg; 
	ImageView resourcesImg;
	ImageView boardOfEdImg;
	ImageView districtImg;
	ImageView schoolsImg;
	ImageView communicationsImg;
	ImageView emergencyImg;
	ImageView settingsImg;

	Bundle bundle = new Bundle();
	
	


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.main);

	    
	    
	    //code to center title. Must be on every page
 
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    

	    newsImg = (ImageView)findViewById(R.id.mainimg1);
	    newsImg.setOnClickListener(mainListener);
	    
	    calendarImg = (ImageView)findViewById(R.id.mainimg2);
	    calendarImg.setOnClickListener(mainListener);
	    
	    resourcesImg = (ImageView)findViewById(R.id.mainimg3);
	    resourcesImg.setOnClickListener(mainListener);
	    
	    boardOfEdImg = (ImageView)findViewById(R.id.mainimg4);
	    boardOfEdImg.setOnClickListener(mainListener);
	    
	    districtImg = (ImageView)findViewById(R.id.mainimg5);
	    districtImg.setOnClickListener(mainListener);
	    
	    schoolsImg = (ImageView)findViewById(R.id.mainimg6);
	    schoolsImg.setOnClickListener(mainListener);
	    
	    communicationsImg = (ImageView)findViewById(R.id.mainimg7);
	    communicationsImg.setOnClickListener(mainListener);
	    
	    emergencyImg = (ImageView)findViewById(R.id.mainimg8);
	    emergencyImg.setOnClickListener(mainListener);
	    
	    settingsImg = (ImageView)findViewById(R.id.mainimg9);
	    settingsImg.setOnClickListener(mainListener);
	    
    
	}
	
	private OnClickListener mainListener = new OnClickListener() {
		public void onClick(View v){
			if (v == newsImg) {
	    		Intent intent = new Intent(FremontUnified.this, News.class);
	    		Global.htmlTitle = "News";
	    		startActivity(intent);
			} else if (v == calendarImg) {
				Intent intent = new Intent(FremontUnified.this, Website.class);
				//Opens webpage with URL of the google calendar
				bundle.putString("url", "https://www.google.com/calendar/b/0/embed?title=Fremont+Unified&showTitle=0&showPrint=0&showCalendars=0&showTz=0&mode=AGENDA&height=400&wkst=1&bgcolor=%234f88e3&src=tccs29ntuhek8s5knmu1j9j840@group.calendar.google.com&ctz=America/Los_Angeles&color=%23856508&gsessionid=OK");
				intent.putExtras(bundle);
	    		Global.htmlTitle = "Calendar";
				startActivity(intent);
			} else if (v == resourcesImg) {
	    		Intent intent = new Intent(FremontUnified.this, Resources.class);
	    		Global.htmlTitle = "Resources";
	    		startActivity(intent);
			} else if (v == boardOfEdImg) {
	    		Intent intent = new Intent(FremontUnified.this, BoardOfEd.class);
	    		Global.htmlTitle = "Board of Education";
	    		startActivity(intent);
			} else if (v == districtImg) {
	    		Intent intent = new Intent(FremontUnified.this, District.class);
	    		Global.htmlTitle = "District";
	    		startActivity(intent);
			} else if (v == schoolsImg) {
				Intent intent = new Intent(FremontUnified.this, Schools.class);
				Global.htmlTitle = "Schools";
				startActivity(intent);
			} else if (v == communicationsImg) {
	    		Intent intent = new Intent(FremontUnified.this, Communications.class);
	    		Global.htmlTitle = "Communications";
	    		startActivity(intent);
			} else if (v == emergencyImg) {
	    		Intent intent = new Intent(FremontUnified.this, HtmlPage.class);
	    		Global.htmlTitle = "Emergency";
	    		bundle.putString("url", "http://www.fremont.k12.ca.us/domain/3692");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
			} else if (v == settingsImg) {
				Global.htmlTitle = "Settings";
				Intent intent = new Intent(FremontUnified.this, Settings.class);
				startActivity(intent);
			} 
		}
	};
}