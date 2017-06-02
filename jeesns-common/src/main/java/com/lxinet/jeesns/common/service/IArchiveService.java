package com.lxinet.jeesns.common.service;

import com.lxinet.jeesns.common.entity.Archive;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.member.entity.Member;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArchiveService {

    Archive findByArchiveId(int id);

    boolean save(Member member, Archive archive);

    boolean update(Member member, Archive archive);

    boolean delete(int id);

    void updateViewCount(int id);

    ResponseModel favor(Member loginMember, int archiveId);
}
