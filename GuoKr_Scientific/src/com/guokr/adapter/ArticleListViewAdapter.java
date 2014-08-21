package com.guokr.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guokr.scientific.ArticleActivity;
import com.guokr.scientific.R;
import com.guokr.utils.HorizontalListView;
import com.guokr.utils.ImageLoader;
import com.guokr.xml.model.ArticleList.Subject;

public class ArticleListViewAdapter extends BaseAdapter {

	private Activity mActivity = null;
	private LayoutInflater mInflater = null;
	private List<Map<String, Object>> mData = null;
	public ImageLoader imageLoader;

	public ArticleListViewAdapter(Activity activity,
			List<Map<String, Object>> data) {
		this.mInflater = LayoutInflater.from(activity);
		this.mActivity = activity;
		this.mData = data;
		if (data == null)
			mData = new ArrayList<Map<String, Object>>();
		imageLoader = new ImageLoader(mActivity);
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
			convertView = mInflater.inflate(R.layout.list_article_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.author = (TextView) convertView.findViewById(R.id.author);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.comment = (TextView) convertView.findViewById(R.id.comment);
			holder.summary_image = (ImageView) convertView
					.findViewById(R.id.summary_image);
			holder.summary = (TextView) convertView.findViewById(R.id.summary);
			holder.subject = (HorizontalListView) convertView
					.findViewById(R.id.gridview_subject);

			holder.author.setOnClickListener(new AuthorOnClickListener());
			holder.comment.setOnClickListener(new CommentOnClickListener());

			convertView.setTag(holder);

		}

		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText((String) mData.get(position).get("title"));
		holder.author.setText((String) mData.get(position).get("author"));
		holder.time.setText((String) mData.get(position).get("time"));
		holder.comment.setText((String) mData.get(position).get("comment"));
		holder.summary.setText((String) mData.get(position).get("summary"));
		SubjectListViewAdapter sgva = (SubjectListViewAdapter) mData.get(
				position).get("subject");
		holder.subject.setAdapter(sgva);
		imageLoader.DisplayImage(
				(String) mData.get(position).get("summary_image"),
				holder.summary_image);

		String url = (String) mData.get(position).get("url");
		holder.summary.setOnClickListener(new SummaryOnClickListener(url));
		return convertView;
	}

	public final class ViewHolder {
		public TextView title;
		public TextView author;
		public TextView time;
		public TextView comment;
		public ImageView summary_image;
		public TextView summary;
		public HorizontalListView subject;;
	}

	public class AuthorOnClickListener implements
			android.view.View.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			new AlertDialog.Builder(mActivity).setTitle("作者")
					.setMessage(tv.getText()).setPositiveButton("确定", null)
					.show();
		}
	}

	public class CommentOnClickListener implements
			android.view.View.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			new AlertDialog.Builder(mActivity).setTitle("评论")
					.setMessage(tv.getText()).setPositiveButton("确定", null)
					.show();
		}
	}

	public class SummaryOnClickListener implements
			android.view.View.OnClickListener {
		String url;

		public SummaryOnClickListener(String url) {
			this.url = url;
		}

		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			// new AlertDialog.Builder(mActivity).setTitle("文章")
			// .setMessage(tv.getText()).setPositiveButton("确定", null)
			// .show();
			Intent intent = new Intent(mActivity, ArticleActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("url", url);
			intent.putExtras(bundle);
			mActivity.startActivity(intent);
		}
	}

	public void Add(String title, String author, String time, String comment,
			String summary_image, String summary, String url,
			SubjectListViewAdapter sgva) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("author", author);
		map.put("time", time);
		map.put("comment", comment);
		map.put("summary_image", summary_image);
		map.put("summary", summary);
		map.put("url", url);
		map.put("subject", sgva);
		mData.add(map);
	}
}
