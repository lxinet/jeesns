package com.lxinet.jeesns.core.utils;

public class Const {

	public static final String SYSTEM_NAME = "JEESNS";
	public static final String SYSTEM_VERSION = "1.2.0-正式版";
	public static final String SYSTEM_UPDATE_TIME = "2017-10-31";
	public static final String LAST_SYSTEM_VERSION = "1.2.0-正式版";
	public static final String LAST_SYSTEM_UPDATE_TIME = "2017-10-31";

	/**
	 * 项目路径
	 */
	public static String PROJECT_PATH;

	/**
	 * 文件上传路径
     */
	public static final String UPLOAD_PATH = "/upload";

	/**
	 * SESSION中会员的key
     */
	public static final String SESSION_MEMBER = "loginMember";
	/**
	 * SESSION中管理员的key
     */
	public static final String SESSION_ADMIN = "LOGIN_ADMIN";

	/**
	 * 后台错误页面的ftl路径
	 */
	public static final String MANAGE_ERROR_FTL_PATH = "/manage/common/error";

	/**
	 * 前台错误页面的ftl路径
	 */
	public static final String INDEX_ERROR_FTL_PATH = "/common/error";

	/**
	 * 默认图片路径
	 */
	public static final String DEFAULT_IMG_URL = "/res/common/images/nopic.png";

	/**
	 * 默认头像
	 */
	public static final String DEFAULT_AVATAR = "/res/common/images/default-avatar.png";

}
