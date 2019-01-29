package org.web.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 并不是所有的web 容器都是一次请求只走一次filter
 * 所以为了代码兼容不同的web容器，自定义的Filter默认继承OncePerRequestFilter是比较安全的做法
 * OncePerRequestFilter作用是让一次请求只经过一次拦截器
 * @author xin
 *
 */
public class EncodingConvertFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("EncodingConvertFilter");
		
		
		
		//doFilter
		//他的作用是将请求转发给过滤器链上下一个对象。这里的下一个指的是下一个filter，如果没有filter那就是你请求的资源。 
		//一般filter都是一个链,web.xml 里面配置了几个就有几个。一个一个的连在一起 
		//request -> filter1 -> filter2 ->filter3 -> .... -> request resource.
		filterChain.doFilter(request, response);
	}

}
