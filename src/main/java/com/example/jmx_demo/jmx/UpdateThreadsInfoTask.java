package com.example.jmx_demo.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.jmx_demo.config.ThreadPoolConfig;

@Component
public class UpdateThreadsInfoTask {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    @Autowired
    ThreadsInfoMBeanImpl threadsInfoMBean;

    @Scheduled(initialDelay=10000, fixedRate=1000)
	public void execute() {
        threadsInfoMBean.setLastUpdated(sdf.format(new Date()));

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();

        for (long threadId : threadIds) {
            ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
            String threadName = threadInfo.getThreadName();

            if (threadName.startsWith(ThreadPoolConfig.THREAD_NAME_PREFIX)) {
                int id = Integer.parseInt(threadName.substring(6)) - 1;
                threadsInfoMBean.updateInfoWith(id, ThreadsInfoMBeanImpl.IDX_THREAD_STATE, threadInfo.getThreadState().toString());
            }
        }
	}
}
