package com.example.jmx_demo.jmx;

public interface ThreadsInfoMBean {
    String getLastUpdated();
    void setLastUpdated(String lastUpdated);
    String[][] getInfo();
    void setInfo(String[][] info);
    void updateInfoWith(int x, int y, String info);
}
