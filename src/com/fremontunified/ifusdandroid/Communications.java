package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class Communications extends ListActivity {
	
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
		
		String[] communicationsTitles = {"Schoolloop", "iParent"};
		String[] communicationsDescriptions = {"Access Schoolloop from your mobile device!", "Learn more and access iParent on the go!"};
		
	    this.setListAdapter(new CustomArrayAdapter(this, communicationsTitles, communicationsDescriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
			switch (position) {
	        case 0: 
	        	intent = new Intent(Communications.this, SchoolloopList.class);
	    		startActivity(intent);
	    		break;
	        case 1: 
	        	intent = new Intent(Communications.this, Website.class);
	        	bundle.putString("url", "http://iparent.fmtusd.org/iparent/");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	       
	        default:  break;
	    }
	}
}