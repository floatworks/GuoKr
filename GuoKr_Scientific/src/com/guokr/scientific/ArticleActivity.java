package com.guokr.scientific;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class ArticleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);

		Intent intent = getIntent();
		String url = intent.getStringExtra("url");

		WebView wv = (WebView) this.findViewById(R.id.webView1);
		wv.loadUrl(url);
		Log.d("url", url);
	}
}
