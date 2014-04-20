package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class ResourcesSubList extends ListActivity {
	
	Intent intent;
	Bundle bundle = new Bundle();
	
	String[] titles = {"FAQ", "District Opportunities", "School Site"};
	String[] descriptions = {"Questions about Volunteering", "Volunteer on Committees!", "Volunteer at Schools!"};
	
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
       
	    this.setListAdapter(new CustomArrayAdapter(this, titles, descriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
	        case 0:  
	        	intent = new Intent(ResourcesSubList.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/210");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	        case 1:          	
	        	intent = new Intent(ResourcesSubList.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/211");
	        	intent.putExtras(bundle);
	    		startActivity(intent);
	        	break;
	        case 2:  
	        	intent = new Intent(ResourcesSubList.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/212");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	    		
	    	default: break;
		}
	}
}