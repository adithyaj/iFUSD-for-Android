package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class Settings extends ListActivity {

	Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.coloredlistview);

	    
	    //code to center title. Must be on every page
		 
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
            
    		String[] settingsTitles = {"Push Notifications", "Preferences", "About this App"};
    		String[] settingsDescriptions = {"Choose schools to receive notifications from", "Control features and submit feedback!", "Find out more about the team behind this app"};
    		
    	    this.setListAdapter(new CustomArrayAdapter(this, settingsTitles, settingsDescriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
			switch (position) {
	        case 0:  
	        	intent = new Intent(Settings.this, Push.class);
	        	startActivity(intent);
	    		break;
	        case 1:  
	        	intent = new Intent(Settings.this, Preferences.class);
	        	startActivity(intent);
	    		break;
	        case 2:  
	        	intent = new Intent(Settings.this, About.class);
	        	startActivity(intent);
	    		break;
	        default:  break;
	    }
	}
}