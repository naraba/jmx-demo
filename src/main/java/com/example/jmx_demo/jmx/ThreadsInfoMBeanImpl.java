package com.example.jmx_demo.jmx;

import org.springframework.stereotype.Component;

import com.example.jmx_demo.config.ThreadPoolConfig;

@Component
public class ThreadsInfoMBeanImpl implements ThreadsInfoMBean {
    public static final int IDX_THREAD_NAME = 0;
    public static final int IDX_EPOCH = 1;
    public static final int IDX_LAST_PROC_DATETIME = 2;
    public static final int IDX_LAST_PROC_RESULT = 3;
    public static final int IDX_THREAD_STATE = 4;

    private String lastUpdated;
    private String[][] info = new String[ThreadPoolConfig.THREAD_CHILD_POOL_SIZE][5];

    @Override
    public String getLastUpdated() {
        return this.lastUpdated;
    }

    @Override
    public void setLastUpdated(String number) {
        this.lastUpdated = number;
    }

    @Override
    public String[][] getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String[][] info) {
        this.info = info;
    }

    @Override
    public void updateInfoWith(int x, int y, String info) {
        this.info[x][y] = info;
    }
}
