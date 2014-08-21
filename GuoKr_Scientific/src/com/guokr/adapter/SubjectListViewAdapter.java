package com.guokr.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guokr.scientific.R;
import com.guokr.utils.ImageLoader;

public class SubjectListViewAdapter extends BaseAdapter {

	private Activity mActivity = null;
	private LayoutInflater mInflater = null;
	private List<Map<String, Object>> mData = null;
	public ImageLoader imageLoader;

	public SubjectListViewAdapter(Activity activity,
			List<Map<String, Object>> data) {
		this.mInflater = LayoutInflater.from(activity);
		this.mActivity = activity;
		this.mData = data;
		if (data == null)
			mData = new ArrayList<Map<String, Object>>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_subject_item, null);
			holder.subject = (TextView) convertView.findViewById(R.id.subject);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.subject.setText((String) mData.get(position).get("subject"));
		holder.subject.setBackgroundColor(Color.parseColor((String) mData.get(
				position).get("color")));
		return convertView;
	}

	public final class ViewHolder {
		public TextView subject;
	}

	public void Add(String subject, String color) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("color", color);
		this.mData.add(map);
	}

}
