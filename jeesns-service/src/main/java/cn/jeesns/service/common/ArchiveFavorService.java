package cn.jeesns.service.common;

import cn.jeesns.dao.common.IArchiveFavorDao;
import cn.jeesns.model.common.ArchiveFavor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("archiveFavorService")
public class ArchiveFavorService {
    @Resource
    private IArchiveFavorDao archiveFavorDao;


    public ArchiveFavor find(Integer archiveId, Integer memberId) {
        return archiveFavorDao.find(archiveId,memberId);
    }

    public void save(Integer archiveId, Integer memberId) {
        archiveFavorDao.save(archiveId,memberId);
    }

    public void delete(Integer archiveId, Integer memberId) {
        archiveFavorDao.delete(archiveId,memberId);
    }
}
