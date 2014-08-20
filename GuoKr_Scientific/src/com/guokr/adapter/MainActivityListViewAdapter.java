package com.guokr.adapter;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guokr.scientific.R;
import com.guokr.utils.ImageLoader;

public class MainActivityListViewAdapter extends BaseAdapter {

	private Context mContext = null;
	private LayoutInflater mInflater = null;
	private List<Map<String, Object>> mData = null;
	public ImageLoader imageLoader;

	public MainActivityListViewAdapter(Context context,
			List<Map<String, Object>> data) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mData = data;
		imageLoader = new ImageLoader(context);
	}

	public MainActivityListViewAdapter(LayoutInflater Inflater,
			List<Map<String, Object>> data) {
		this.mInflater = Inflater;
		mData = data;
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
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.author = (TextView) convertView.findViewById(R.id.author);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.comment = (TextView) convertView.findViewById(R.id.comment);
			holder.summary_image = (ImageView) convertView
					.findViewById(R.id.summary_image); 
			holder.summary = (TextView) convertView.findViewById(R.id.summary);
			holder.ll_item = (LinearLayout) convertView
					.findViewById(R.id.ll_item);
			 
			holder.author.setOnClickListener(new AuthorOnClickListener());
			holder.comment.setOnClickListener(new CommentOnClickListener());
			//holder.summary_image.setOnClickListener(new SummaryOnClickListener());
			holder.summary.setOnClickListener(new SummaryOnClickListener());
			
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

		imageLoader.DisplayImage(
				(String) mData.get(position).get("summary_image"),
				holder.summary_image);

		holder.ll_item.removeViewAt(0);
		holder.ll_item.addView(
				(LinearLayout) mData.get(position).get("ll_subject"), 0,
				new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));

		return convertView;
	}

	public final class ViewHolder {
		public TextView title;
		public TextView author;
		public TextView time;
		public TextView comment;
		public ImageView summary_image;
		public TextView summary;
		public LinearLayout ll_item;;
	}

	public class AuthorOnClickListener implements
			android.view.View.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			new AlertDialog.Builder(mContext).setTitle("作者")
					.setMessage(tv.getText()).setPositiveButton("确定", null)
					.show();
		}
	}

	public class CommentOnClickListener implements
			android.view.View.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			new AlertDialog.Builder(mContext).setTitle("评论")
					.setMessage(tv.getText()).setPositiveButton("确定", null)
					.show();
		}
	}

	public class SummaryOnClickListener implements
			android.view.View.OnClickListener {
		@Override
		public void onClick(View v) {
			TextView tv = (TextView) v;
			new AlertDialog.Builder(mContext).setTitle("文章")
					.setMessage(tv.getText()).setPositiveButton("确定", null)
					.show();
		}
	}
}
