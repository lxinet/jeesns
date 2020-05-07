package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.system.ITagDao;
import com.lxinet.jeesns.model.system.Tag;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017-11-01.
 */
@Service("tagService")
public class TagService {
    @Resource
    private ITagDao tagDao;

    public boolean save(Tag tag) {
        return tagDao.saveObj(tag) == 1;
    }

    public Result listByPage(Page page, int funcType) {
        List<Tag> list = tagDao.list(page,funcType);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    public boolean update(Tag tag) {
        Tag findTag = this.findById(tag.getId());
        if (findTag == null) {
            throw new OpeErrorException("标签不存在");
        }
        return tagDao.updateObj(tag) == 1;
    }

    public boolean delete(Integer id) {
        return tagDao.deleteById(id, Tag.class) > 0;
    }

    public Tag findById(Integer id) {
        return tagDao.getById(id, Tag.class);
    }
}
