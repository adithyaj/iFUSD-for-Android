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

public class SchoolloopList extends ListActivity {
	
	ListView myListView;
	Intent intent;
	Bundle bundle = new Bundle();
	
	List<String> elements = Arrays.asList("American", "Centerville", "Hopkins", "Horner", "Irvington", "Kennedy", "Mission San Jose", "Robertson", "Thornton", "Walters", "Washington");
	String[] urls = {
			"http://ahs-fusd-ca.schoolloop.com/mobile/login",
			"http://centerville-fusd-ca.schoolloop.com/mobile/login",
			"http://hjh-fusd-ca.schoolloop.com/mobile/login",
			"http://horner-fusd-ca.schoolloop.com/mobile/login",
			"http://ihs-fusd-ca.schoolloop.com/mobile/login",
			"http://www.kennedy-fusd-ca.schoolloop.com/mobile/login",
			"http://mission-fusd-ca.schoolloop.com/mobile/login",
			"http://rhs-fusd-ca.schoolloop.com/mobile/login",
			"http://thornton-fusd-ca.schoolloop.com/mobile/login",
			"http://walters-fusd-ca.schoolloop.com/mobile/login",
			"http://washington-fusd-ca.schoolloop.com/mobile/login"		
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
	        	intent = new Intent(SchoolloopList.this, Website.class);
	        	bundle.putString("url", urls[position]);
	    		intent.putExtras(bundle);
	    		startActivity(intent);       
	}
}