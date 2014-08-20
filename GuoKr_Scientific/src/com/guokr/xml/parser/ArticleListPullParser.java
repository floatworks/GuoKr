package com.guokr.xml.parser;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import com.guokr.xml.model.ArticleList;

import android.util.Xml;

public class ArticleListPullParser implements IPullParser {

	@Override
	public List<ArticleList> Parse(InputStream is) throws Exception {
		List<ArticleList> articleList_list = null;
		ArticleList articleList = null;
		List<ArticleList.Subject> subjectList = null;
		ArticleList.Subject subject = null;

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				articleList_list = new ArrayList<ArticleList>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("article")) {
					articleList = new ArticleList(); 
					subjectList = new ArrayList<ArticleList.Subject>();
				} else if (parser.getName().equals("subject")) {
					subject = articleList.new Subject();
				} else if (parser.getName().equals("bgcolor")) {
					eventType = parser.next();
					subject.setBgcolor(parser.getText());
				} else if (parser.getName().equals("text")) {
					eventType = parser.next();
					subject.setText(parser.getText());
				} else if (parser.getName().equals("title")) {
					eventType = parser.next();
					articleList.setTitle(parser.getText());
				} else if (parser.getName().equals("author")) {
					eventType = parser.next();
					articleList.setAuthor(parser.getText());
				} else if (parser.getName().equals("time")) {
					eventType = parser.next();
					articleList.setTime(parser.getText());
				} else if (parser.getName().equals("comment")) {
					eventType = parser.next();
					articleList.setComment(parser.getText());
				} else if (parser.getName().equals("summary_img")) {
					eventType = parser.next();
					articleList.setSummary_img(parser.getText());
				} else if (parser.getName().equals("summary")) {
					eventType = parser.next();
					articleList.setSummary(parser.getText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("subject")) {
					subjectList.add(subject);
					subject = null;
				} 
				if (parser.getName().equals("article")) {
					articleList.setSubject(subjectList);
					articleList_list.add(articleList);
					articleList = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return articleList_list;
	}

	@Override
	public String Serialize(List<ArticleList> articlelists) throws Exception {
		// XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		// XmlSerializer serializer = factory.newSerializer();

		XmlSerializer serializer = Xml.newSerializer(); // 由android.util.Xml创建一个XmlSerializer实例
		StringWriter writer = new StringWriter();
		serializer.setOutput(writer); // 设置输出方向为writer
		serializer.startDocument("UTF-8", true);
		serializer.startTag("", "root");
		for (ArticleList articlelist : articlelists) {
			serializer.startTag("", "article");

			serializer.startTag("", "title");
			serializer.text(articlelist.getTitle());
			serializer.endTag("", "title");

			serializer.startTag("", "author");
			serializer.text(articlelist.getAuthor() + "");
			serializer.endTag("", "author");

			serializer.startTag("", "time");
			serializer.text(articlelist.getTime() + "");
			serializer.endTag("", "time");

			serializer.startTag("", "comment");
			serializer.text(articlelist.getComment() + "");
			serializer.endTag("", "comment");

			serializer.startTag("", "summary_img");
			serializer.text(articlelist.getSummary_img() + "");
			serializer.endTag("", "summary_img");

			serializer.endTag("", "article");
		}
		serializer.endTag("", "root");
		serializer.endDocument();

		return writer.toString();
	}
}
