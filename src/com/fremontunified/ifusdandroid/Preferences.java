package com.fremontunified.ifusdandroid;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Preferences extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.preferences);

	    Button feedback = (Button) findViewById(R.id.feedbackbutton);
	    Button tellFriend = (Button) findViewById(R.id.tellfriend);
	    
	    feedback.setOnClickListener(feedbackListener);
	    tellFriend.setOnClickListener(friendListener);
	    
	    //code to center title. Must be on every page
 
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    
	}
	
	private OnClickListener feedbackListener = new OnClickListener() {
		public void onClick(View v){
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
			
			String aEmailList[] = { "adityajung@gmail.com"};  
			  
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
			  
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "FUSD Application Feedback"); 
			
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hello, Please enter your bug report of feature suggestion below:");

			
			emailIntent.setType("message/rfc822");  
			
			
			
			startActivity(emailIntent);  
		}
	};
	
	private OnClickListener friendListener = new OnClickListener() {
		public void onClick(View v){
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
			String body = "<html><body>The Fremont Unified School District released an app called DroidFUSD for Android smartphones. It's a great way to stay informed about current events across the district. You should check it out! It's free on Google Play!" + 
    		"<br/><a href=\"https://play.google.com/store/apps/details?id=com.fusd\" target=\"_blank\"><img src=\"http://www.android.com/images/brand/android_app_on_play_logo_small.png\"/>  DroidFUSD Android App Released</a></body></html>";
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out the DroidFUSD Android App"); 
			
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));

			
			emailIntent.setType("text/html");  
			
			
			
			startActivity(emailIntent);  
		}
	};
}