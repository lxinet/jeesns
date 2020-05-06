package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.dao.picture.IPictureFavorDao;
import com.lxinet.jeesns.model.picture.PictureFavor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/11/16.
 */
@Service("pictureFavorService")
public class PictureFavorService {
    @Resource
    private IPictureFavorDao pictureFavorDao;


    public PictureFavor find(Integer pictureId, Integer memberId) {
        return pictureFavorDao.find(pictureId,memberId);
    }

    public void save(Integer pictureId, Integer memberId) {
        pictureFavorDao.save(pictureId,memberId);
    }

    public void delete(Integer pictureId, Integer memberId) {
        pictureFavorDao.delete(pictureId,memberId);
    }
}
