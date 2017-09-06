<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${SITE_NAME} - ${SITE_SEO_TITLE} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
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
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div id="banner" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#banner" data-slide-to="0" class="active"></li>
            <li data-target="#banner" data-slide-to="1"></li>
            <li data-target="#banner" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img alt="First slide" src="${basePath}/res/front/images/banner1.png">
                <div class="carousel-caption">
                    <h3></h3>
                    <p></p>
                </div>
            </div>
            <div class="item">
                <img alt="Second slide" src="${basePath}/res/front/images/banner2.png">
                <div class="carousel-caption">
                    <h3></h3>
                    <p></p>
                </div>
            </div>
            <div class="item">
                <img alt="Third slide" src="${basePath}/res/front/images/banner3.png">
                <div class="carousel-caption">
                    <h3></h3>
                    <p></p>
                </div>
            </div>
        </div>
    </div>

    <div class="main-content m-t-10">
        <div class="row">
            <div class="col-md-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        推荐阅读
                        <span class="pull-right">
                            <a class="btn btn-primary m-t-n4" href="${basePath}/article/list">阅读更多</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                        <@cms_article_list cid=0 num=16 thumbnail=1; article>
                            <#list articleList as article>
                                <div class="col-md-3">
                                    <div class="item index-article">
                                        <div class="item-content">
                                            <div class="media">
                                                <a href="${basePath}/article/detail/${article.id}">
                                                    <img src="${basePath}${article.thumbnail}" alt="${article.title}" height="150px" width="100%">
                                                </a>
                                            </div>
                                            <h4><a href="${basePath}/article/detail/${article.id}">
                                                <#if article.title?length &gt; 18>
                                                ${article.title?substring(0,18)}...
                                                <#else>
                                                ${article.title}
                                                </#if>
                                            </a></h4>
                                        </div>
                                        <div class="item-footer">
                                            <a href="${basePath}/article/detail/${article.id}" class="text-muted"><i class="icon-comments"></i> ${article.viewCount}</a> &nbsp; <span class="text-muted">${article.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                        </div>
                                    </div>
                                </div>
                            </#list>
                        </@cms_article_list>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        最新帖子
                        <span class="pull-right">
                            <a class="btn btn-primary m-t-n4" href="${basePath}/group/">阅读更多</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="col-md-4">
                                <div class="article-hot-list">
                                    <ul>
                                    <@group_topic_list cid=0 num=15 day=100; groupTopic>
                                        <#list groupTopicList as groupTopic>
                                            <li><i class="main-text-color"></i> <a href="${basePath}/group/topic/${groupTopic.id}">
                                                <#if groupTopic.title?length &gt; 18>
                                                ${groupTopic.title?substring(0,18)}...
                                                <#else>
                                                ${groupTopic.title}
                                                </#if>
                                            </a></li>
                                        </#list>
                                    </@group_topic_list>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="items">
                                <@group_topic_list gid=0 num=6 thumbnail=1; groupTopic>
                                    <#list groupTopicList as groupTopic>
                                        <div class="col-md-4">
                                            <div class="item index-article">
                                                <div class="item-content">
                                                    <div class="media">
                                                        <a href="${basePath}/group/topic/${groupTopic.id}">
                                                            <img src="${basePath}${groupTopic.thumbnail}" alt="${groupTopic.title}" height="150px" width="100%">
                                                        </a>
                                                    </div>
                                                    <h4><a href="${basePath}/group/topic/${groupTopic.id}">${groupTopic.title}</a></h4>
                                                </div>
                                                <div class="item-footer">
                                                    <a href="${basePath}/group/topic/${groupTopic.id}" class="text-muted"><i class="icon-comments"></i> ${groupTopic.viewCount}</a> &nbsp; <span class="text-muted">${groupTopic.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </#list>
                                </@group_topic_list>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        推荐群组
                        <span class="pull-right">
                            <a class="btn btn-primary m-t-n4" href="${basePath}/group/">查看更多</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="col-md-12 group-list">
                            <@group_list status=1 num=8; group>
                                <#list groupList as group>
                                    <div class="col-md-3">
                                        <div class="group-detail">
                                            <div class="group-logo">
                                                <a href="${basePaht}/group/detail/${group.id}">
                                                    <img alt="image" class="img-rounded" src="${basePath}${group.logo}" width="100px" height="100px">
                                                </a>
                                            </div>
                                            <div class="group-info">
                                                <h4><strong><a href="${basePaht}/group/detail/${group.id}">${group.name}</a></strong></h4>
                                                <p class="text-muted">
                                                    <#if group.introduce?length &gt; 50>
                                                    ${group.introduce?substring(0,50)}...
                                                    <#else>
                                                    ${group.introduce}
                                                    </#if>
                                                </p>
                                                <small class="text-muted">${group.topicCount}篇文章 · ${group.fansCount}人关注</small>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </@group_list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>