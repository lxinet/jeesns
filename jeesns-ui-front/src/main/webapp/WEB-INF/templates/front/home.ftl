<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${member.name}主页 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/res/common/css/jeesns.css">
    <link rel="stylesheet" href="${basePath}/res/common/css/jeesns-skin.css">
    <link href="${basePath}/res/plugins/gallery/css/blueimp-gallery.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
    <script src="${basePath}/res/modules/mem.js"></script>
    <script src="${basePath}/res/plugins/emoji/js/emojis.js"></script>
    <script src="${basePath}/res/plugins/gallery/js/jquery.blueimp-gallery.min.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="member-banner" style="background-image: url(${basePath}/res/common/images/member_banner.png);">
        <div class="attempts"></div>
        <div class="container">
            <div class="container content">
                <div class="left">
                    <div class="avatar">
                        <img src="${basePath}${member.avatar}" class="img-circle" width="80px" height="80px"/>
                    </div>
                    <div class="info">
                        <div class="name">
                        ${member.name}
                        <#if member.sex=='女'>
                            <span class="sex"><i class="fa fa-venus"></i></span>
                        <#elseif member.sex=='男'>
                            <span class="sex"><i class="fa fa-mars"></i></span>
                        <#else>
                            <span class="sex"><i class="fa fa-intersex"></i></span>
                        </#if>
                        </div>
                        <p>${member.website}</p>
                        <p>${member.introduce}</p>
                        <p class="operator">
                            <a class="label label-primary member-follows" member-id="${member.id}">
                                <i class="fa fa-heart-o"></i> 关注
                            </a>
                        </p>
                    </div>
                </div>
                <div class="right">
                    <div class="follows">
                        <span>关注</span>
                        <a href="${basePath}/u/${member.id}/home/follows">${member.follows}</a>
                    </div>
                    <div class="fans">
                        <span>粉丝</span>
                        <a href="${basePath}/u/${member.id}/home/fans">${member.fans}</a>
                    </div>
                    <div class="follows">
                        <span>积分</span>
                        <a href="${basePath}/member/scoreDetail/list">${member.score}</a>
                    </div>
                    <div class="login-info">
                        加入时间:${member.createTime?string('yyyy-MM-dd')}
                        最近登录:<#if member.currLoginTime??>${member.currLoginTime?string('yyyy-MM-dd')}<#else>未登陆过</#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="ibox">
                <div class="ibox-content float-left">
                    <div class="col-sm-2">
                        <div class="float-e-margins">
                            <div class="feed-activity-list">
                                <a href="${basePath}/u/${member.id}">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            动态
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/fans">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            粉丝
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/follows">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            关注
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/article">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            文章
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/groupTopic">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            群帖
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/weibo">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            微博
                                        </div>
                                    </div>
                                </a>
                                <a href="${basePath}/u/${member.id}/home/group">
                                    <div class="feed-element">
                                        <div class="media-body">
                                            关注群组
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-10">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>
                                    <#if type=="article">
                                        文章
                                    <#elseif type=="groupTopic">
                                        群贴
                                    <#elseif type=="weibo">
                                        微博
                                    <#elseif type=="groupTopic">
                                        群贴
                                    <#elseif type=="fans">
                                        粉丝
                                    <#elseif type=="follows">
                                        关注
                                    <#elseif type=="group">
                                        群贴
                                    <#else>
                                        关注群组
                                    </#if>
                                </h5>
                            </div>
                            <div>
                                <div class="feed-activity-list">
                                <#if type=="article">
                                    <#list model.data as article>
                                        <div class="ibox-content col-sm-12 bottom-line">
                                            <div class="col-sm-10">
                                                <#if article.thumbnail??>
                                                    <div class="small m-b-xs">
                                                        <span class="text-muted">&nbsp;</span>
                                                    </div>
                                                </#if>
                                                <h3>
                                                    <a href="${basePath}/article/detail/${article.id}" class="btn-link">
                                                    ${article.title}
                                                    </a>
                                                </h3>

                                                <div class="small m-b-xs">
                                                            <span class="text-muted"><i
                                                                    class="fa fa-clock-o"></i> ${article.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                                            <i class="fa fa-heart"> </i> ${article.favor}</span>
                                                    <i class="fa fa-eye"> </i> ${article.viewCount} 浏览</span>
                                                </div>
                                            </div>
                                            <#if article.thumbnail??>
                                                <div class="col-sm-2">
                                                    <div class="text-center list-thumbnail">
                                                        <a href="${basePath}/article/detail/${article.id}" class="btn-link">
                                                            <img alt="${article.title}"
                                                                 class="m-t-xs img-responsive list-thumbnail"
                                                                 src="${article.thumbnail}">
                                                        </a>
                                                    </div>
                                                </div>
                                            </#if>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/article"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                <#elseif type=="groupTopic">
                                    <#list model.data as groupTopic>
                                        <div class="ibox-content col-sm-12 bottom-line">
                                            <div class="col-sm-10">
                                                <#if groupTopic.thumbnail??>
                                                    <div class="small m-b-xs">
                                                        <span class="text-muted">&nbsp;</span>
                                                    </div>
                                                </#if>
                                                <h3>
                                                    <a href="${basePath}/group/topic/${groupTopic.id}" class="btn-link">
                                                    ${groupTopic.title}
                                                    </a>
                                                </h3>

                                                <div class="small m-b-xs">
                                                            <span class="text-muted"><i
                                                                    class="fa fa-clock-o"></i> ${groupTopic.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                                            <i class="fa fa-heart"> </i> ${groupTopic.favor}</span>
                                                    <i class="fa fa-eye"> </i> ${groupTopic.viewCount} 浏览</span>
                                                </div>
                                            </div>
                                            <#if groupTopic.thumbnail??>
                                                <div class="col-sm-2">
                                                    <div class="text-center list-thumbnail">
                                                        <a href="${basePath}/article/detail/${groupTopic.id}" class="btn-link">
                                                            <img alt="${groupTopic.title}"
                                                                 class="m-t-xs img-responsive list-thumbnail"
                                                                 src="${groupTopic.thumbnail}">
                                                        </a>
                                                    </div>
                                                </div>
                                            </#if>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/groupTopic"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                <#elseif type=="weibo">
                                    <#list model.data as weibo>
                                        <div class="feed-element">
                                            <a href="${basePath}/u/${weibo.member.id}" target="_blank" class="pull-left">
                                                <img alt="image" class="img-circle" src="${basePath}${weibo.member.avatar!''}">
                                            </a>
                                            <div class="media-body ">
                                                <strong>
                                                    <a href="${basePath}/u/${weibo.member.id}" target="_blank">${weibo.member.name}</a></strong><br/>
                                                <div class="mg-t-10 mg-b-10">
                                                    <p>${weibo.content}</p>
                                                    <div class="lightBoxGallery">
                                                        <#list weibo.pictures as picture>
                                                            <a href="${basePath}${picture.path}" title="${weibo.member.name}" data-gallery=""><img src="${basePath}${picture.thumbnailPath}"/></a>
                                                        </#list>
                                                        <div id="blueimp-gallery" class="blueimp-gallery">
                                                            <div class="slides"></div>
                                                            <h3 class="title"></h3>
                                                            <a class="prev">‹</a>
                                                            <a class="next">›</a>
                                                            <a class="close">×</a>
                                                            <a class="play-pause"></a>
                                                            <ol class="indicator"></ol>
                                                        </div>
                                                    </div>
                                                </div>
                                                <small>${weibo.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                                                (<#if weibo.isFavor==0>
                                                <a class="text-primary weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                                            <#else>
                                                <a class="text-success weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-up"></i> ${weibo.favor}</a>
                                            </#if>
                                                <a href="${basePath}/weibo/detail/${weibo.id}">评论:${weibo.commentCount}</a>)
                                            </div>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/weibo"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                <#elseif type=="group">
                                    <#list model.data as groupFans>
                                        <div class="feed-element">
                                            <a href="${basePath}/group/detail/${groupFans.group.id}" class="pull-left">
                                                <img alt="image" class="img-circle" src="${groupFans.group.logo}">
                                            </a>
                                            <div class="media-body ">
                                                <h4><strong><a href="${basePath}/group/detail/${groupFans.group.id}">${groupFans.group.name}</a></strong></h4>
                                                <p>${groupFans.group.introduce}</p>
                                                <small class="text-muted">${groupFans.group.topicCount}篇文章 · ${groupFans.group.fansCount}人关注</small>
                                            </div>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/group"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                <#elseif type=="follows">
                                    <#list model.data as memberFans>
                                        <div class="feed-element">
                                            <a href="${basePath}/u/${memberFans.followWhoMember.id}" class="pull-left">
                                                <img alt="image" class="img-circle" src="${memberFans.followWhoMember.avatar}">
                                            </a>
                                            <div class="media-body ">
                                                <h4><strong><a href="${basePath}/u/${memberFans.followWhoMember.id}">${memberFans.followWhoMember.name}</a></strong></h4>
                                                <p>${memberFans.followWhoMember.introduce}</p>
                                                <small class="text-muted">${memberFans.followWhoMember.follows}关注 · ${memberFans.followWhoMember.fans}粉丝</small>
                                            </div>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/follows"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                <#elseif type=="fans">
                                    <#list model.data as memberFans>
                                        <div class="feed-element">
                                            <a href="${basePath}/u/${memberFans.whoFollowMember.id}" class="pull-left">
                                                <img alt="image" class="img-circle" src="${memberFans.whoFollowMember.avatar}">
                                            </a>
                                            <div class="media-body ">
                                                <h4><strong><a href="${basePath}/u/${memberFans.whoFollowMember.id}">${memberFans.whoFollowMember.name}</a></strong></h4>
                                                <p>${memberFans.whoFollowMember.introduce}</p>
                                                <small class="text-muted">${memberFans.whoFollowMember.follows}关注 · ${memberFans.whoFollowMember.fans}粉丝</small>
                                            </div>
                                        </div>
                                    </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${basePath}/u/${member.id}/home/fans"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
                                </#if>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<#include "/member/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
        mem.isFollowed(${member.id}, "${basePath}");
        $(".member-follows").click(function () {
            mem.follows($(this), "${basePath}")
        });

    });
</script>
</body>
</html>