package com.fremontunified.ifusdandroid;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class BoardOfEd extends ListActivity {
	
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
		
		String[] boardTitles = {"Board Meetings", "Agendas and Minutes", "Board Members", "Board Policies", "SURFBoardE"};
		String[] boardDescriptions = {"Dates and times of Meetings", "Read Agendas from the Archives!", "Contact Board Members", "District Policies and Regulations", "Student liaisons to the Board of Ed"};
		
	    this.setListAdapter(new CustomArrayAdapter(this, boardTitles, boardDescriptions));
	}
	
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			// Get the item that was clicked
			switch (position) {
	        case 0:  
	        	intent = new Intent(BoardOfEd.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/13520");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	        case 1:          	
	        	intent = new Intent(BoardOfEd.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.boarddocs.com/ca/fremont/Board.nsf/public");
	        	intent.putExtras(bundle);
	    		startActivity(intent);
	        	break;
	        case 2:  
	        	intent = new Intent(BoardOfEd.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/domain/14");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	        case 3:  
	        	intent = new Intent(BoardOfEd.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/71");
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	    		break;
	        case 4: 
	        	intent = new Intent(BoardOfEd.this, HtmlPage.class);
	        	bundle.putString("url", "http://www.fremont.k12.ca.us/Page/13334");
	    		intent.putExtras(bundle);
	    		startActivity(intent);
	    		break;
	       
	        default:  break;
	    }
	}
	
}