package com.blog.mian;

import java.util.Collections;

import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author GilesChu
 *
 */
public class RScheduledExecutorServiceFactory implements FactoryBean<RScheduledExecutorService> {

	private String hostName;

	private String port;

	private Integer dbIndex;

	private String password;

	private RedissonClient redissonClient;

	public RScheduledExecutorService getObject() throws Exception {
		Config config = new Config();
		config.useSingleServer().setAddress(String.format("redis://%s:%s", hostName, port));
		config.useSingleServer().setDatabase(dbIndex);
		RedissonClient redisson = Redisson.create(config);

		RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
		nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("executor-service", 1));
		RedissonNode node = RedissonNode.create(nodeConfig);
		node.start();
		RScheduledExecutorService e = redisson.getExecutorService("executor-service");
		return e;
	}

	public Class<?> getObjectType() {
		return RScheduledExecutorService.class;
	}
	public boolean isSingleton() {
		return true;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RedissonClient getRedissonClient() {
		return redissonClient;
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

}
