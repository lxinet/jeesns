<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${group.name} - ${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${base}/res/common/css/jeesns.css">
    <link rel="stylesheet" href="${base}/res/common/css/jeesns-skin.css">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.min.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/common/js/extendPagination.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content blog">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-content profile-element group">
                        <div class="group-logo">
                            <img alt="${group.name}" src="${base}${group.logo}" width="80px" height="80px"/>
                        </div>
                        <div class="group-detail">
                            <p><strong>${group.name}</strong></p>
                            <p>${model.page.totalCount}帖子 · ${groupFansList?size}关注</p>
                            <p><a href="${base}/u/${group.creatorMember.id}">${group.creatorMember.name}</a> 创建于${group.createTime?string("yyyy-MM-dd")}</p>
                        </div>
                        <div class="text-right">
                        <#if isfollow == true>
                            <a class="btn btn-outline btn-primary" title="取消关注"
                               href="${base}/group/nofollow/${group.id}" target="_jeesnsLink">取消关注</a>
                        <#else>
                            <a class="btn btn-outline btn-primary" title="添加关注"
                               href="${base}/group/follow/${group.id}" target="_jeesnsLink">关注</a>
                        </#if>
                        <#if loginUser?? && loginUser.id == group.creator>
                            <a class="btn btn-outline btn-primary" href="${base}/group/edit/${group.id}">编辑</a>
                        </#if>
                        <#if isManager == 1>
                            <a class="btn btn-outline btn-primary" href="${base}/group/auditList/${group.id}">审核帖子</a>
                        </#if>
                        </div>
                        <div class="group-introduce">
                            ${group.introduce!''}
                        </div>
                    </div>
                </div>

                <div class="ibox ibox-content">
                    <div class="text-right">
                        <a class="btn btn-outline btn-info" href="${base}/group/post/${group.id}">发帖</a>
                    </div>
                <#list model.data as topic>
                    <div class="ibox-content col-sm-12 bottom-line">
                        <div class="col-sm-10">
                            <#if topic.thumbnail??>
                                <div class="small m-b-xs">
                                    <span class="text-muted">&nbsp;</span>
                                </div>
                            </#if>
                            <h3>
                                <a href="${base}/group/topic/${topic.id}" class="btn-link">
                                <#if topic.isTop==1>
                                    <img src="${base}/res/common/images/top.png"/>
                                <#elseif topic.isTop==2>
                                    <img src="${base}/res/common/images/supertop.png"/>
                                </#if>
                                <#if topic.isEssence==1>
                                    <img src="${base}/res/common/images/essence.gif"/>
                                </#if>
                                ${topic.title}
                                </a>
                            </h3>

                            <div class="small m-b-xs">
                                <span class="text-muted">
                                    <i class="fa fa-user"> </i> <a href="${base}/u/${topic.member.id}">${topic.member.name}</a>&nbsp;&nbsp;
                                    <i class="fa fa-clock-o"></i> ${topic.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                    <i class="fa fa-heart"> </i> ${topic.favor}</span>
                                    <i class="fa fa-eye"> </i> ${topic.viewCount} 浏览

                                </span>
                            </div>
                        </div>
                        <#if topic.thumbnail??>
                            <div class="col-sm-2">
                                <div class="text-center list-thumbnail">
                                    <a href="${base}/group/topic/${topic.id}" class="btn-link">
                                        <img alt="${topic.title}" class="m-t-xs img-responsive list-thumbnail"
                                             src="${topic.thumbnail}">
                                    </a>
                                </div>
                            </div>
                        </#if>
                    </div>
                </#list>
                <#if model.data?size == 0>
                    <div class="ibox-content">
                        <div class="col-sm-10">
                            暂无帖子
                        </div>
                    </div>
                </#if>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${base}/group/detail/${group.id}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox float-e-margins">
                        <div>
                            <div class="ibox-title">
                                <h5>粉丝</h5>&nbsp;&nbsp;<a href="${base}/group/fans/${group.id}">更多>></a>
                            </div>
                            <div class="ibox-content profile-content">
                                <#list groupFansList as groupFans>
                                    <div class="group-fans">
                                        <a href="${base}/u/${groupFans.member.id}" target="_blank">
                                            <div class="group-fans-avatar">
                                                <img class="img-circle" src="${base}${groupFans.member.avatar}" width="60px" height="60px"/>
                                            </div>
                                            <div class="group-fans-name text-center">
                                                ${groupFans.member.name}
                                            </div>
                                        </a>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/index/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>