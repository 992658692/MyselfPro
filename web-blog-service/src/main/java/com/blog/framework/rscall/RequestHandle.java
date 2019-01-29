package com.blog.framework.rscall;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * request 构建处理扩展定义， 常见与数据加密，时间戳生成等
 *
 * @param <T>
 */
public interface RequestHandle<T> {

	/**
	 * 对构建request，参数进行扩张，将其插入到队列。
	 * @param newParms
	 */
	void buildExtraParam(LinkedHashMap<String, Object> newParms);
	
	/**
	 * 对于同功能多接口情况下，相同入口，但是不同的入参参数。
	 * @param parms
	 * @return
	 */
	Map<String, Object> buildBaseParam(T param);
}
