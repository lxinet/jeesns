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
<#include "/${jeesnsConfig.frontTemplate}/common/header.ftl"/>
<div class="container">
    <div id="myNiceCarousel" class="carousel slide" data-ride="carousel">
        <!-- 圆点指示器 -->
        <ol class="carousel-indicators">
            <li data-target="#myNiceCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myNiceCarousel" data-slide-to="1"></li>
            <li data-target="#myNiceCarousel" data-slide-to="2"></li>
        </ol>

        <!-- 轮播项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <img alt="First slide" src="http://zui.sexy/docs/img/slide1.jpg">
                <div class="carousel-caption">
                    <h3>我是第一张幻灯片</h3>
                    <p>:)</p>
                </div>
            </div>
            <div class="item">
                <img alt="Second slide" src="http://zui.sexy/docs/img/slide2.jpg">
                <div class="carousel-caption">
                    <h3>我是第二张幻灯片</h3>
                    <p>0.0</p>
                </div>
            </div>
            <div class="item">
                <img alt="Third slide" src="http://zui.sexy/docs/img/slide3.jpg">
                <div class="carousel-caption">
                    <h3>我是第三张幻灯片</h3>
                    <p>最后一张咯~</p>
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
                            <a class="btn btn-primary m-t-n4" href="${basePath}/cms/list">阅读更多</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                        <@cms_article_list cid=0 num=8 thumbnail=1; article>
                            <#list articleList as article>
                                <div class="col-md-3">
                                    <div class="item">
                                        <div class="item-content">
                                            <div class="media"><img src="${basePath}${article.thumbnail}" alt="${article.title}"></div>
                                            <h4><a href="${basePath}/article/detail/${article.id}">${article.title}</a></h4>
                                        </div>
                                        <div class="item-footer">
                                            <a href="#" class="text-muted"><i class="icon-comments"></i> ${article.viewCount}</a> &nbsp; <span class="text-muted">${article.createTime?string('yyyy-MM-dd HH:mm')}</span>
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
                            <a class="btn btn-primary m-t-n4" href="${basePath}/cms/list">阅读更多</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="col-md-4">
                                <div class="article-hot-list">
                                    <ul>
                                    <@group_topic_list cid=0 num=20 day=30; groupTopic>
                                        <#list groupTopicList as groupTopic>
                                            <li><i class="main-text-color"></i> <a href="${basePath}/group/topic/${groupTopic.id}">${groupTopic.title}</a></li>
                                        </#list>
                                    </@group_topic_list>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-8 group-list">
                            <@group_list status=1 num=6; group>
                                <#list groupList as group>
                                    <div class="col-md-4">
                                        <div class="group-detail">
                                            <div class="group-logo">
                                                <a href="${basePaht}/group/detail/${group.id}">
                                                    <img alt="image" class="img-rounded" src="${basePath}${group.logo}" width="100px" height="100px">
                                                </a>
                                            </div>
                                            <div class="group-info">
                                                <h4><strong><a href="${basePaht}/group/detail/${group.id}">${group.name}</a></strong></h4>
                                                <p class="text-muted" title="22">
                                                    22
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
<#include "/${jeesnsConfig.frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>