package cn.jeesns.service.picture;

import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.utils.Const;
import cn.jeesns.core.utils.StringUtils;
import cn.jeesns.dao.picture.IPictureAlbumDao;
import cn.jeesns.model.picture.PictureAlbum;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
@Service
public class PictureAlbumService{
    @Resource
    private IPictureAlbumDao pictureAlbumDao;

    public Result listByMember(Integer memberId) {
        List<PictureAlbum> list = pictureAlbumDao.listByMember(memberId);
        Result model = new Result(0);
        model.setData(list);
        return model;
    }

    public Result<PictureAlbum> listByPage(Page page) {
        List<PictureAlbum> list = pictureAlbumDao.list(page);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    public boolean delete(Integer id) {
        return pictureAlbumDao.delete(id) == 1;
    }

    public boolean save(PictureAlbum pictureAlbum) {
        if (pictureAlbum.getType() == null){
            pictureAlbum.setType(0);
        }
        if (StringUtils.isEmpty(pictureAlbum.getCover())){
            pictureAlbum.setCover(Const.DEFAULT_PICTURE_COVER);
        }
        return pictureAlbumDao.saveObj(pictureAlbum) == 1;
    }

    public boolean update(PictureAlbum pictureAlbum) {
        return pictureAlbumDao.updateObj(pictureAlbum) == 1;
    }

    public PictureAlbum findWeiboAlbum(Integer memberId) {
        return pictureAlbumDao.findWeiboAlbum(memberId);
    }

    public PictureAlbum findById(Integer id) {
        return pictureAlbumDao.findById(id);
    }
}
