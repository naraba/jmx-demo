package com.example.jmx_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.jmx_demo.config.ThreadPoolConfig;
import com.example.jmx_demo.jmx.ThreadsInfoMBeanImpl;
import com.example.jmx_demo.service.ChildService;

@SpringBootApplication
@EnableScheduling
public class JmxDemoApplication {

	@Autowired
	ThreadsInfoMBeanImpl threadsInfoMBeanImpl;

	@Autowired
	ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JmxDemoApplication.class, args);
		JmxDemoApplication app = ctx.getBean(JmxDemoApplication.class);
		app.runTasks();
	}

	public void runTasks() {
		for (int i = 0; i < ThreadPoolConfig.THREAD_CHILD_POOL_SIZE; i++) {
			final int idx = i;
			final ChildService demoService = new ChildService();
			System.out.println("Thread " + i + " launching...");
			threadPoolTaskExecutor.execute(() -> demoService.execTask(threadsInfoMBeanImpl.getInfo()[idx]));
			System.out.println("Thread " + i + " launched.");
		}
	}
}
