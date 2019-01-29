package com.blog.mian;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.io.Files;

public class Main {
	public static void main(String[] args) throws Exception {
		Properties p = new Properties();
		
		if (args != null && args.length > 0) {
			String address = System.getenv("zookeeper_address");
			String group = System.getenv("zookeeper_group");
			
			if (null == address || null == group) {
				System.out.println("----------系统初始化异常：环境变量中未发现zookeeper_addredd和group参数");
				return ;
			}
			p.put("dubbo.service.group", group);
			p.put("dubbo.reference.group", group);
			p.put("dubbo.protocol.port", args[0]);
			p.put("dubbo.registry.address", address);
			
			File configDir = Files.createTempDir();
			
			ZooKeeper zookeeper = new ZooKeeper(address, 2000, null);
			String language = zookeeper.getData(String.format("/appConfigs/%s/language", group), false, null).toString();
			String path = String.format("/appConfigs/%s/%s/druid-db.properties", group,"service");
			File dbFile = new File(configDir, "druid-db.properties");
			
			BufferedWriter write = Files.newWriter(dbFile, Charset.forName(language));
			String content = new String(zookeeper.getData(path, false, null), Charset.forName(language));
			write.write(content);
			write.close();
			p.put("druid.config.file", dbFile.getPath());
			
			path = String.format("/appConfigs/%s/%s/config.properties", group, "service");
			write = Files.newWriter(new File(configDir, "config.properties"), Charset.forName(language));
			content = new String(zookeeper.getData(path, false, null), Charset.forName(language));
			write.write(content);
			write.close();
			
		} else {
			System.setProperty("dubbo.service.group", "xinpc");
			System.setProperty("dubbo.reference.group", "xinpc");
			System.setProperty("dubbo.protocol.port", "20885");
			System.setProperty("dubbo.application.name", "main-service");
			System.setProperty("dubbo.registry.protocol", "zookeeper");
			System.setProperty("dubbo.protocol.serialization", "java");
			System.setProperty("dubbo.registry.address", "127.0.0.1:2181");
			System.setProperty("druid.config.file", "./properties/druid-db-dev.properties");
			
		}
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"application_base.xml"});
		context.start();
		System.out.println("初始化完成");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.currentThread().sleep(24 * 60 * 60 * 1000);
					} catch (InterruptedException e) {
					}
				}
			}
		});
		t.start();
	}
}
