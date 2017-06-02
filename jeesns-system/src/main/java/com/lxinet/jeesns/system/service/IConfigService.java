package com.lxinet.jeesns.system.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.system.entity.Config;

import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/29.
 */
public interface IConfigService {
    List<Config> allList();

    Map<String,String> getConfigToMap();

    String getValue(String key);

    ResponseModel update(Map<String,String> params);
}
