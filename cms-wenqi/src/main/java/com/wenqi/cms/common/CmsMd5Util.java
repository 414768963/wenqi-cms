package com.wenqi.cms.common;

import com.bawei.wenqi.utils.Md5Util;

public class CmsMd5Util {
	/**
	 * @Title: stringToMd5   
	 * @Description: TODO(鎻忚堪杩欎釜鏂规硶鐨勪綔鐢�)   
	 * @param: @param str
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String string2MD5(String str) {
		return Md5Util.string2MD5(str+"_cmsAdmin");
	}
}
