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

import com.guokr.adapter.ArticleListViewAdapter;
import com.guokr.adapter.SubjectListViewAdapter;
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
	public ArticleListViewAdapter articleListViewAdapter = null;

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
	public class PlaceholderFragment extends Fragment {

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
				articleListViewAdapter = new ArticleListViewAdapter(
						getActivity(), null);
				InputStream is = getActivity().getAssets()
						.open("test_data.xml");
				ArticleListPullParser alpp = new ArticleListPullParser();
				List<ArticleList> articleList_list = alpp.Parse(is);
				getData(articleList_list);

				mDrawerListView.setAdapter(articleListViewAdapter);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
			}
			return rootView;
		}

		private void getData(List<ArticleList> articleList_list) {
			for (ArticleList al : articleList_list) {
				String title = al.getTitle();
				String author = al.getAuthor();
				String time = al.getTime();
				String comment = al.getComment();
				String summary_image = al.getSummary_img();
				String summary = al.getSummary();
				String url = al.getUrl();
				List<Subject> subjectList = al.getSubject();
				SubjectListViewAdapter sgva = new SubjectListViewAdapter(
						getActivity(), null);
				for (Subject sj : subjectList) {
					sgva.Add(sj.getText(), sj.getBgcolor());
				}
				articleListViewAdapter.Add(title, author, time, comment,
						summary_image, summary, url, sgva);
			}
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
