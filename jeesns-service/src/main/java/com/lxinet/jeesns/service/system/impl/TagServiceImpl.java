package com.lxinet.jeesns.service.system.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.system.ITagDao;
import com.lxinet.jeesns.model.system.Tag;
import com.lxinet.jeesns.service.system.ITagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-11-01.
 */
@Service("tagService")
public class TagServiceImpl implements ITagService {
    @Resource
    private ITagDao tagDao;

    @Override
    public ResponseModel save(Tag tag) {
        if (tagDao.save(tag) == 1) {
            return new ResponseModel(0, "保存成功");
        }
        return new ResponseModel(-1, "保存失败");
    }

    @Override
    public ResponseModel listByPage(Page page, int funcType) {
        List<Tag> list = tagDao.listByPage(page,funcType);
        ResponseModel model = new ResponseModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel update(Tag tag) {
        Tag findTag = this.findById(tag.getId());
        if (findTag == null){
            return new ResponseModel(-1, "标签不存在");
        }
        if (tagDao.update(tag) > 0) {
            return new ResponseModel(0, "更新成功");
        }
        return new ResponseModel(-1, "更新失败");
    }

    @Override
    public ResponseModel delete(Integer id) {
        if (tagDao.delete(id) > 0) {
            return new ResponseModel(1, "删除成功");
        }
        return new ResponseModel(-1, "删除失败");
    }

    @Override
    public Tag findById(Integer id) {
        return tagDao.findById(id);
    }
}
