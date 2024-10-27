package com.example.jmx_demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.springframework.stereotype.Service;

import com.example.jmx_demo.jmx.ThreadsInfoMBeanImpl;

@Service
public class ChildService {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    private final Random rand = new Random();

    private int epoch = 0;

    public void execTask(String[] info) {
        info[ThreadsInfoMBeanImpl.IDX_THREAD_NAME] = Thread.currentThread().getName();
        
        while (true) {
            try {
                epoch++;
                info[ThreadsInfoMBeanImpl.IDX_EPOCH] = String.valueOf(epoch);
                info[ThreadsInfoMBeanImpl.IDX_LAST_PROC_DATETIME] = sdf.format(new Date());
                info[ThreadsInfoMBeanImpl.IDX_LAST_PROC_RESULT] = doHeavyTask();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private String doHeavyTask() {
        return String.valueOf(fibonacci(rand.nextInt(25) + 25));
    }

    // O(n^2) time complexity
    private int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
