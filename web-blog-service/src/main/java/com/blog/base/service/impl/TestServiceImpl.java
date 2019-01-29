package com.blog.base.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.blog.iface.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Map<String, Object> test () {
		return jdbcTemplate.queryForMap("select userName from user");
	}
}
