package com.fremontunified.ifusdandroid;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] titles;
	private final String[] descriptions;
	

	public CustomArrayAdapter(Activity context, String[] titles, String[] descriptions) {
		super(context, R.layout.communitylistitem, titles);
		this.context = context;
		this.titles = titles;
		this.descriptions = descriptions;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.communitylistitem, null, true);
		TextView title = (TextView) rowView.findViewById(R.id.itemText);
		TextView description = (TextView) rowView.findViewById(R.id.itemDesc);
		

		title.setText(titles[position]);
		description.setText(descriptions[position]);

		// Change the icon for Windows and iPhone


		return rowView;
	}
}