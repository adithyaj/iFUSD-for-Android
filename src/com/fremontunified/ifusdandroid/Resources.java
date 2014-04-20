package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class Resources extends ListActivity {
	
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
		
		String[] resourcesTitles = {"Parent Resources", "Student Resources", "Get Involved", "Library", "Naviance"};
		String[] resourcesDescriptions = {"Lots of Resources for Parents!", "Course Catalogs and Resources", "Volunteer Opportunities", "Search School Libraries!", "Get college information from Naviance!"};
		
	    this.setListAdapter(new CustomArrayAdapter(this, resourcesTitles, resourcesDescriptions));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		switch (position) {
        case 0:  
        	intent = new Intent(Resources.this, HtmlPage.class);
        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/85");
    		intent.putExtras(bundle);
    		startActivity(intent);
    		break;
        case 1:          	
        	intent = new Intent(Resources.this, HtmlPage.class);
        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/88");
        	intent.putExtras(bundle);
    		startActivity(intent);
        	break;
        case 2:  
        	intent = new Intent(Resources.this, ResourcesSubList.class);
    		startActivity(intent);
    		break;
        case 3:  
        	intent = new Intent(Resources.this, HtmlPage.class);
        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/118");
        	intent.putExtras(bundle);
    		startActivity(intent);
    		break;
        case 4: 
        	intent = new Intent(Resources.this, NavianceList.class);
    		startActivity(intent);
    		break;
       
        default:  break;
    }
	}
	
}