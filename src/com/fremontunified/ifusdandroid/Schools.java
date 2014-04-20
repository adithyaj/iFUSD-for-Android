package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class Schools extends ListActivity {
	
	Intent intent;
	Bundle bundle = new Bundle();
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

	    //code to center title. Must be on every page
	    
        setContentView(R.layout.coloredlistview);

	    
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
		
		String[] schoolsTitles = {"Elementary Schools", "Junior High Schools", "High Schools", "More Schools", "School Accountability"};
		String[] schoolsDescriptions = {"K-6 Schools", "7th and 8th grade", "9th - 12th grade", "Alternative Programs", "API scores (SARC)"};
		
	    this.setListAdapter(new CustomArrayAdapter(this, schoolsTitles, schoolsDescriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
			switch (position) {
	        case 0:  
	        	intent = new Intent(Schools.this, SchoolsList.class);
	        	Global.schoolType = 0;
	    		startActivity(intent);
	    		break;
	        case 1:          	
	        	intent = new Intent(Schools.this, SchoolsList.class);
	        	Global.schoolType = 1;
	    		startActivity(intent);
	        	break;
	        case 2:  
	        	intent = new Intent(Schools.this, SchoolsList.class);
	        	Global.schoolType = 2;
	        	startActivity(intent);
	    		break;
	        case 3:  
	        	intent = new Intent(Schools.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/9");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	        case 4: 
	        	intent = new Intent(Schools.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/16");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	       
	        default:  break;
	    }
	}
}