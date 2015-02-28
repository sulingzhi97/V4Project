package acon.android.v4.component.overview;

import java.util.List;

import acon.android.v4.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopWindowAdapter extends BaseAdapter {
	private Context context;
	private List<String> strList;
	private LayoutInflater mInflater;

	public PopWindowAdapter(Context context, List<String> strList) {
		this.context = context;
		this.strList = strList;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.popwindow_adapter_item_layout, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.popwindow_adapter_item_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(strList.get(position));
		return convertView;
	}

	public class ViewHolder {
		TextView textView;
	}

}
