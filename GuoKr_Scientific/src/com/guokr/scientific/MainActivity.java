package com.guokr.scientific;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.guokr.adapter.MainActivityListViewAdapter;
import com.guokr.xml.model.ArticleList;
import com.guokr.xml.model.ArticleList.Subject;
import com.guokr.xml.parser.IPullParser;
import com.guokr.xml.parser.ArticleListPullParser;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			ListView mDrawerListView = (ListView) rootView
					.findViewById(R.id.listview_article);

			try {
				InputStream is = getActivity().getAssets()
						.open("test_data.xml");
				ArticleListPullParser alpp = new ArticleListPullParser();
				List<ArticleList> articleList_list = alpp.Parse(is);

				MainActivityListViewAdapter adapter = new MainActivityListViewAdapter(
						getActivity(), getData(articleList_list));
				mDrawerListView.setAdapter(adapter);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
			}
			return rootView;
		}

		private List<Map<String, Object>> getData(
				List<ArticleList> articleList_list) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;

			for (ArticleList al : articleList_list) {
				map = new HashMap<String, Object>();
				map.put("title", al.getTitle());
				map.put("author", al.getAuthor());
				map.put("time", al.getTime());
				map.put("comment", al.getComment());
				map.put("summary_image", al.getSummary_img());
				map.put("summary", al.getSummary());
				map.put("ll_subject", CreateSubject(al.getSubject()));
				list.add(map);
			}
			return list;
		}

		private LinearLayout CreateSubject(List<Subject> subjectList) {
			LinearLayout layout = new LinearLayout(getActivity());
			layout.setOrientation(LinearLayout.HORIZONTAL);

			LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout_params.rightMargin = 15;

			for (Subject sj : subjectList) {
				TextView tv = new TextView(getActivity());
				tv.setText(sj.getText());
				tv.setBackgroundColor(Color.parseColor(sj.getBgcolor()));
				tv.setTextSize(16);
				tv.setTextColor(0xffffffff);
				tv.setPadding(20, 0, 20, 0);
				tv.setLayoutParams(layout_params);
				tv.setGravity(Gravity.CENTER_VERTICAL);
				layout.addView(tv);
				tv.setOnClickListener(new SubjectOnClickListener());
			}
			return layout;
		}

		public class SubjectOnClickListener implements
				android.view.View.OnClickListener {
			@Override
			public void onClick(View v) {
				TextView tv = (TextView) v;
				new AlertDialog.Builder(getActivity()).setTitle("学科")
						.setMessage(tv.getText()).setPositiveButton("确定", null)
						.show();
			}
		}

	}

}
