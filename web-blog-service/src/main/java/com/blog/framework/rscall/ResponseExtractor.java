package com.blog.framework.rscall;

import java.util.LinkedHashMap;

/**
 * 
 *对接口返回数据进行加工处理类接口(非必要实现)
 *只有当默认处理无法满足需求，如{status:0 result:T}情况时，需拆分再解析。
 *
 * @param <T>
 */
public interface ResponseExtractor<T> {

	/**
	 *
	 * 接口反悔数据处理接口， 将http请求的数据转换成业务对象T
	 * @return
	 */
	T extraData(LinkedHashMap<String, String> extraParams, String result, Class<T> clazz, Object... objects);
}
