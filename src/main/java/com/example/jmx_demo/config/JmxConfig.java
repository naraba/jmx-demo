package com.example.jmx_demo.config;

import java.util.Map;
import java.util.HashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import com.example.jmx_demo.jmx.ThreadsInfoMBeanImpl;

@Configuration
public class JmxConfig {

    @Bean
    public ThreadsInfoMBeanImpl threadsInfoMBeanImpl() {
        return new ThreadsInfoMBeanImpl();
    }

    @Bean
    public MBeanExporter mBeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("com.example:type=JMX_Demo", threadsInfoMBeanImpl());
        exporter.setBeans(beans);
        return exporter;
    }
}
