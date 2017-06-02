package com.lxinet.jeesns.common.service;


import com.lxinet.jeesns.common.entity.ArchiveFavor;

/**
 * 文章点赞Service接口
 * Created by zchuanzhao on 2017/2/9.
 */
public interface IArchiveFavorService {

    ArchiveFavor find(Integer archiveId, Integer memberId);

    void save(Integer archiveId, Integer memberId);

    void delete(Integer archiveId, Integer memberId);
}