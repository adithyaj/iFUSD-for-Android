package com.fremontunified.ifusdandroid;

import static com.fremontunified.ifusdandroid.CommonUtilities.SENDER_ID;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

public class Push extends ListActivity {
	
	boolean[] checks;
	Button registerButton; 
	
	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		
		final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	    setContentView(R.layout.push);
		
		//code to center title. Must be on every page
		 
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
       GCMRegistrar.checkDevice(this);
	   GCMRegistrar.checkManifest(this);
	   registerButton = (Button) findViewById(R.id.gcm_register_button);
       registerButton.setOnClickListener(mainListener);
    
       final String regId = GCMRegistrar.getRegistrationId(this);
       if (regId.equals("")) {
    	   registerButton.setText(R.string.gcm_register_button);
       } else {
         Log.e("Server Info", "Already registered");
         registerButton.setText(R.string.gcm_unregister_button);
       } 
            
            
       Resources res = getResources();
            
       this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, res.getStringArray(R.array.push_school_list)));
       checks = new boolean[res.getStringArray(R.array.push_school_list).length];
       	
	}
	
	void checkAll(ListView listView) {
		for(int i=0; i < listView.getChildCount(); i++){
			CheckedTextView item = (CheckedTextView)listView.getChildAt(i);
		    item.setChecked(true);
		    checks[i] = true;
		}
	}
	
	void uncheckAll(ListView listView) {
		for(int i=0; i < listView.getChildCount(); i++){
			CheckedTextView item = (CheckedTextView)listView.getChildAt(i);
		    item.setChecked(false);
		    checks[i] = false;
		}
	}
	
	private OnClickListener mainListener = new OnClickListener() {
		public void onClick(View v){
			String regId = GCMRegistrar.getRegistrationId(Push.this);
			 if (regId.equals("")) {
				 GCMRegistrar.register(Push.this, SENDER_ID);
				 registerButton.setText(R.string.gcm_unregister_button);
			 } else {
				 GCMRegistrar.unregister(Push.this);
		      	registerButton.setText(R.string.gcm_register_button);
			 } 
		}
	};
	
	@Override
	public void onListItemClick(ListView parent, View view, int position, long id) {

 	      CheckedTextView check = (CheckedTextView) view;
 	      check.setChecked(!check.isChecked());
 	      boolean click = check.isChecked();
 	      if (click) {
 	    	  // Selected
 	    	  if (position == 0) {
 	    		  checkAll(parent);
 	    	  }
 	    	  checks[position] = true;
 	    	  
// 	          Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
 	      } else {
 	    	  // not selected
 	    	  if (position == 0) {
	    		  uncheckAll(parent);
	    	  }
 	    	 checks[position] = false;
// 	          Toast.makeText(this, "Not Selected", Toast.LENGTH_SHORT).show();
 	      } 
 	} 
}