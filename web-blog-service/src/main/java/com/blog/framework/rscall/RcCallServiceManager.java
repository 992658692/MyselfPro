package com.blog.framework.rscall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;

import com.blog.entity.ServiceEntity;

public class RcCallServiceManager implements InitializingBean{
	
	private  ConcurrentHashMap<String, Delegate> delegates = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		List<ServiceEntity> list = new ArrayList<ServiceEntity>();
		for (ServiceEntity s : list) {
			String name = s.getName();
			Delegate delegate = delegates.get(name);
			if (null != delegate) {
				continue;
			}
			
			delegate = new Delegate(s);
			delegate.init();
			delegates.put(name, delegate);
		}
	}

}
