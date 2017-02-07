package com.lxinet.jeesns.core.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/10/18.
 */
public class JeesnsParams {
    private Map<String,Object> attrs = new HashMap<>();

    public void set(String key,Object value){
        this.attrs.put(key,value);
    }

    public Object get(String key){
        return this.attrs.get(key);
    }

    public String getString(String key){
        return (String) this.attrs.get(key);
    }

    public String getString(String key,String defaultValue){
        String value = (String) this.attrs.get(key);
        if(value == null){
            value = defaultValue;
        }
        return value;
    }

    public int getInt(String key){
        return (int) this.attrs.get(key);
    }

    public int getInt(String key,int defaultValue){
        try {
            return (int) this.attrs.get(key);
        } catch (Exception e){
            return defaultValue;
        }
    }

    public double getDouble(String key){
        return (double) this.attrs.get(key);
    }

    public double getDouble(String key,double defaultValue){
        try {
            return (double) this.attrs.get(key);
        } catch (Exception e){
            return defaultValue;
        }
    }



    public Map<String, Object> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, Object> attrs) {
        this.attrs = attrs;
    }
}
