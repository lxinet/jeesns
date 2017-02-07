package com.lxinet.jeesns.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zchuanzhao on 16/9/27.
 */
@Component("jeesnsConfig")
public class JeesnsConfig {
    @Value("${managePath}")
    private String managePath;

    public String getManagePath() {
        return managePath;
    }



}
