package com.lxinet.jeesns.service.picture.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.dao.picture.IPictureAlbumDao;
import com.lxinet.jeesns.model.picture.PictureAlbum;
import com.lxinet.jeesns.service.picture.IPictureAlbumService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
@Service
public class PictureAlbumServiceImpl implements IPictureAlbumService {
    @Resource
    private IPictureAlbumDao pictureAlbumDao;

    @Override
    public ResultModel listByMember(Integer memberId) {
        List<PictureAlbum> list = pictureAlbumDao.listByMember(memberId);
        ResultModel model = new ResultModel(0);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel<PictureAlbum> listByPage(Page page) {
        List<PictureAlbum> list = pictureAlbumDao.listByPage(page);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel delete(Integer id) {
        if (pictureAlbumDao.delete(id) > 0) {
            return new ResultModel(0, "删除成功");
        }
        return new ResultModel(-1, "删除失败");
    }

    @Override
    public ResultModel save(PictureAlbum pictureAlbum) {
        if (pictureAlbum.getType() == null){
            pictureAlbum.setType(0);
        }
        if (StringUtils.isEmpty(pictureAlbum.getCover())){
            pictureAlbum.setCover(Const.DEFAULT_PICTURE_COVER);
        }
        if (pictureAlbumDao.save(pictureAlbum) > 0) {
            return new ResultModel(0, "添加成功");
        }
        return new ResultModel(-1, "添加失败");
    }

    @Override
    public ResultModel update(PictureAlbum pictureAlbum) {
        if (pictureAlbumDao.update(pictureAlbum) > 0) {
            return new ResultModel(0, "更新成功");
        }
        return new ResultModel(-1, "更新失败");
    }

    @Override
    public PictureAlbum findWeiboAlbum(Integer memberId) {
        return pictureAlbumDao.findWeiboAlbum(memberId);
    }

    @Override
    public PictureAlbum findById(Integer id) {
        return pictureAlbumDao.findById(id);
    }
}
