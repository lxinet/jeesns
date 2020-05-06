package com.lxinet.jeesns.service.picture;

import com.lxinet.jeesns.dao.picture.IPictureAlbumFavorDao;
import com.lxinet.jeesns.model.picture.PictureAlbumFavor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
@Service("pictureAlbumFavorService")
public class PictureAlbumFavorService {
    @Resource
    private IPictureAlbumFavorDao pictureAlbumFavorDao;


    public PictureAlbumFavor find(Integer pictureAlbumId, Integer memberId) {
        return pictureAlbumFavorDao.find(pictureAlbumId,memberId);
    }

    public void save(Integer pictureAlbumId, Integer memberId) {
        pictureAlbumFavorDao.save(pictureAlbumId,memberId);
    }

    public void delete(Integer pictureAlbumId, Integer memberId) {
        pictureAlbumFavorDao.delete(pictureAlbumId,memberId);
    }
}
