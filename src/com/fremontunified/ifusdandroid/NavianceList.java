package com.fremontunified.ifusdandroid;



import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NavianceList extends ListActivity {
	
	ListView myListView;
	Intent intent;
	Bundle bundle = new Bundle();
	
	List<String> elements = Arrays.asList("American", "Centerville", "Hopkins", "Horner", "Irvington", "Kennedy", "Mission San Jose", "Robertson", "Thornton", "Walters", "Washington");
	String[] urls = {
			"http://connection.naviance.com/americanhigh",
			"http://connection.naviance.com/centervillejh",
			"http://connection.naviance.com/hopkins",
			"http://connection.naviance.com/horner",
			"http://connection.naviance.com/irvingtonhigh",
			"http://connection.naviance.com/jfkfremont",
			"http://connection.naviance.com/msjhs",
			"http://connection.naviance.com/robertsonhigh",
			"http://connection.naviance.com/thorntonjr",
			"http://connection.naviance.com/walters",
			"http://connection.naviance.com/washingtonhi"		
	};
	
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
       
            myListView = getListView();
            myListView.setFastScrollEnabled(true);
            
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            		  android.R.layout.simple_list_item_1, android.R.id.text1, elements);
            
            myListView.setAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	        	intent = new Intent(NavianceList.this, Website.class);
	        	bundle.putString("url", urls[position]);
	    		intent.putExtras(bundle);
	    		startActivity(intent);       
	}
}