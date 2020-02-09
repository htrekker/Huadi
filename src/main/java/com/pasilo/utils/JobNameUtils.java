package com.pasilo.utils;

import java.util.HashMap;
import java.util.Map;

public class JobNameUtils {

	private static final Map<String, String> nameMap = new HashMap<>();

	static {
		nameMap.put("back","后端");
		nameMap.put("mobile","移动开发");
		nameMap.put("front","前端");
		nameMap.put("ai","人工智能");
		nameMap.put("test","测试");
		nameMap.put("op","运维");
		nameMap.put("dba","数据库管理");
		nameMap.put("hl","高端岗位");
		nameMap.put("mng","管理");
		nameMap.put("ep","企业软件开发");

	}

	public static String getJobNameInChinese(String code){
		return nameMap.get(code);
	}

}
