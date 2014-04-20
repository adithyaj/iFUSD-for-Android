package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class District extends ListActivity {
	
	Intent intent;
	Bundle bundle = new Bundle();
	
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
		
		String[] districtTitles = {"Office of the Superintendent", "About FUSD", "Contact FUSD", "Giving to the District", "District Committees", "FUSD Budget", "Business Services"};
		String[] districtDescriptions = {"Updates from the Superintendent", "Learn more about FUSD", "Call, Email, or visit FUSD", "Donate to FUSD", "List of District Committees", "Recent Updates and Info", "Financial Information"};
		
	    this.setListAdapter(new CustomArrayAdapter(this, districtTitles, districtDescriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Log.e("position", Integer.toString(position));
			switch (position) {
	        case 0:  
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/17");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	        case 1:          	
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/10");
	        	intent.putExtras(bundle);
	    		startActivity(intent);
	        	break;
	        case 2:  
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/29");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	        case 3:  
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/9");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	        case 4: 
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/175");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	        case 5:  
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/23");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	        case 6:  
	        	intent = new Intent(District.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/254");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	       
	        default:  break;
	    }
	}
}