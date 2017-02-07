package com.lxinet.jeesns.core.service.impl;
import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.core.service.IBaseService;
import java.util.List;

/**
 * Service基类实现类
 * Created by zchuanzhao on 2016/11/26.
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    protected abstract IBaseDao<T> getDao();

    public T get(Integer id) {
        return getDao().findById(id);
    }

    public int save(T entity) {
        return getDao().save(entity);
    }

    public int update(T entity) {
        return getDao().update(entity);
    }

    public int delete(Integer id) {
        return getDao().delete(id);
    }

    public List allList() {
        return getDao().allList();
    }

}
