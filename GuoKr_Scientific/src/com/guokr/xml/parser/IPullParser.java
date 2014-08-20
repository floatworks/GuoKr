package com.guokr.xml.parser;

import java.io.InputStream;
import java.util.List;

import com.guokr.xml.model.ArticleList;

public interface IPullParser {
	/**
	 * 解析输入流 得到Book对象集合
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public List<ArticleList> Parse(InputStream is) throws Exception;

	/**
	 * 序列化Book对象集合 得到XML形式的字符串
	 * 
	 * @param books
	 * @return
	 * @throws Exception
	 */
	public String Serialize(List<ArticleList> list) throws Exception;
}
