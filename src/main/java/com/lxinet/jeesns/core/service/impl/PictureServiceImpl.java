package com.lxinet.jeesns.core.service.impl;

import com.lxinet.jeesns.core.dao.IPictureDao;
import com.lxinet.jeesns.core.entity.Picture;
import com.lxinet.jeesns.core.service.IPictureService;
import com.lxinet.jeesns.core.utils.PictureUtil;
import com.lxinet.jeesns.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
@Service
public class PictureServiceImpl implements IPictureService {
    @Resource
    private IPictureDao pictureDao;

    @Override
    public List<Picture> find(Integer foreignId) {
        return pictureDao.find(foreignId);
    }

    @Override
    public int delete(HttpServletRequest request, Integer foreignId) {
        List<Picture> pictures = this.find(foreignId);
        PictureUtil.delete(request,pictures);
        return pictureDao.delete(foreignId);
    }

    @Override
    public int save(Picture picture) {
        return pictureDao.save(picture);
    }

    @Override
    public int update(Integer foreignId, String ids) {
        if(StringUtils.isNotEmpty(ids)){
            String[] idsArr = ids.split(",");
            return pictureDao.update(foreignId, idsArr);
        }
        return 0;
    }
}
