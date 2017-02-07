package com.lxinet.jeesns.core.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zchuanzhao on 2016/11/26.
 */
public class HtmlUtil {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_p = "<p[^>]*?>[\\s\\S]*?<\\/p>"; // 定义p的正则表达式
	private static final String regEx_span = "<span[^>]*?>[\\s\\S]*?<\\/span>"; // 定义span的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符

	/**
	 * @param htmlStr
	 * @return 删除Html标签
	 */
	public static String delHTMLTag(String htmlStr) {
		if(StringUtils.isNotEmpty(htmlStr)){
			Pattern p_script = Pattern.compile(regEx_script,
					Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			Pattern p_style = Pattern
					.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

//		Pattern p_p = Pattern
//				.compile(regEx_p, Pattern.CASE_INSENSITIVE);
//		Matcher m_p = p_p.matcher(htmlStr);
//		htmlStr = m_p.replaceAll(""); // 过滤p标签

//		Pattern p_span = Pattern
//				.compile(regEx_span, Pattern.CASE_INSENSITIVE);
//		Matcher m_span = p_span.matcher(htmlStr);
//		htmlStr = m_span.replaceAll(""); // 过滤p标签

			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			Pattern p_space = Pattern
					.compile(regEx_space, Pattern.CASE_INSENSITIVE);
			Matcher m_space = p_space.matcher(htmlStr);
			htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
			return htmlStr.trim();
		}
		 return htmlStr;
	}
	/**
	 * @param htmlStr
	 * @return 删除Html标签  空格和换行
	 */
	public static String getTextFromHtml(String htmlStr) {
		htmlStr = delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
//		htmlStr = htmlStr.substring(0, htmlStr.indexOf("。") + 1);
		return htmlStr;
	}
}
