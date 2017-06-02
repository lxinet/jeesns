<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${SITE_NAME} - ${SITE_SEO_TITLE} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/emoji/css/emoji.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
    <script src="${basePath}/res/plugins/emoji/js/emojis.js"></script>
</head>

<body class="gray-bg">
<#include "/${jeesnsConfig.frontTemplate}/common/header.ftl"/>
<div class="wrapper wrapper-content blog">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-lg-8">
                <div class="ibox ibox-content">
                    <h3>最新文章</h3>
                <#list articleModel.data as article>
                    <div class="ibox-content col-sm-12 bottom-line">
                        <div class="col-sm-10">
                            <#if article.thumbnail??>
                                <div class="small m-b-xs">
                                    <span class="text-muted">&nbsp;</span>
                                </div>
                            </#if>
                            <h3>
                                <a href="${basePath}/article/detail/${article.id}" class="btn-link" target="_blank">
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
                                <div class="list-thumbnail float-right">
                                    <a href="${basePath}/article/detail/${article.id}" class="btn-link" target="_blank">
                                        <img alt="${article.title}" class="m-t-xs img-responsive list-thumbnail"
                                             src="${article.thumbnail}">
                                    </a>
                                </div>
                            </div>
                        </#if>
                    </div>
                </#list>
                    <div class="box-footer clearfix"></div>
                </div>
                <div class="ibox ibox-content">
                    <h3>推荐${GROUP_ALIAS}</h3>
                    <div class="feed-activity-list">
                    <#list groupModel.data as group>
                        <div class="feed-element">
                            <a href="${basePath}/group/detail/${group.id}" class="pull-left">
                                <img alt="image" class="img-circle" src="${group.logo}">
                            </a>
                            <div class="media-body ">
                                <h4><strong><a href="${basePath}/group/detail/${group.id}">${group.name}</a></strong></h4>
                                <p>${group.introduce}</p>
                                <small class="text-muted">${group.topicCount}篇文章 · ${group.fansCount}人关注</small>
                            </div>
                        </div>
                    </#list>
                    </div>
                </div>
                <div class="ibox ibox-content">
                    <h3>最新帖子</h3>
                <#list groupTopicModel.data as article>
                    <div class="ibox-content col-sm-12 bottom-line">
                        <div class="col-sm-10">
                            <#if article.thumbnail??>
                                <div class="small m-b-xs">
                                    <span class="text-muted">&nbsp;</span>
                                </div>
                            </#if>
                            <h3>
                                <a href="${basePath}/group/topic/${article.id}" class="btn-link" target="_blank">
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
                                <div class="list-thumbnail float-right">
                                    <a href="${basePath}/group/topic/${article.id}" class="btn-link" target="_blank">
                                        <img alt="${article.title}" class="m-t-xs img-responsive list-thumbnail"
                                             src="${article.thumbnail}">
                                    </a>
                                </div>
                            </div>
                        </#if>
                    </div>
                </#list>
                    <div class="box-footer clearfix"></div>
                </div>
            </div>
            <div class="col-sm-12 col-lg-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${WEIBO_ALIAS}</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t jeesns_form" action="${basePath}/weibo/publish" method="post">
                            <p><textarea cols="3" class="form-control" name="content" id="weibo-content"
                                         maxlength="${WEIBO_POST_MAXCONTENT}"></textarea></p>
                            <div class="row emoji-container" id="emoji">
                                <i class="fa fa-smile-o emoji-tbtn"></i>
                                <span class="pull-right mg-r-15">
                                 <span id="weibo-words" class="mg-r-5">0/${WEIBO_POST_MAXCONTENT}</span>
                                <input type="submit" value="发布" class="btn btn-primary">
                            </span>
                            </div>
                        </form>
                        <div>
                            <div class="feed-activity-list">
                            <#list weiboModel.data as weibo>
                                <div class="feed-element">
                                    <a href="${basePath}/u/${weibo.member.id}" target="_blank" class="pull-left">
                                        <img alt="image" class="img-circle" src="${basePath}${weibo.member.avatar!''}">
                                    </a>
                                    <div class="media-body ">
                                        <strong><a href="${basePath}/u/${weibo.member.id}" target="_blank"
                                                   class="pull-left">${weibo.member.name}</a></strong><br/>
                                        <div class="mg-t-10 mg-b-10">
                                        ${weibo.content}
                                        </div>
                                        <small>${weibo.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                                        (<#if weibo.isFavor==0>
                                        <a class="text-primary weibo-favor" weibo-id="${weibo.id}"><i
                                                class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                                    <#else>
                                        <a class="text-success weibo-favor" weibo-id="${weibo.id}"><i
                                                class="fa fa-thumbs-up"></i> ${weibo.favor}</a>
                                    </#if>
                                        <a href="${basePath}/weibo/detail/${weibo.id}">
                                            <#if weibo.type==1>
                                                <i class="fa fa-image"></i>
                                            </#if>
                                            评论:${weibo.commentCount}</a>)
                                    </div>
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

<#include "/${jeesnsConfig.frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
        $(".weibo-favor").click(function () {
            weibo.favor($(this), "${basePath}")
        });
        $('#emoji').emoji({
            insertAfter: function (item) {
                $('#weibo-content').insertContent(':' + item.name + ':')
            }
        }, "${basePath}");
    });
</script>
</body>
</html>