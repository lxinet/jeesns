<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><#if articleCate??>${articleCate.name}<#else>文章列表</#if> - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<#include "/${jeesnsConfig.frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-md-8">
                <div class="items">
                    <#list model.data as article>
                    <div class="item article-list shadow">
                        <div class="item-heading">
                            <a href="${basePath}/article/list?cateid=${article.articleCate.id}"><div class="pull-right label label-success">${article.articleCate.name}</div></a>
                            <h4><a href="${basePath}/article/detail/${article.id}">${article.title}</a></h4>
                        </div>
                        <div class="item-content">
                            <#if article.thumbnail??>
                                <div class="media pull-left"><img src="${basePath}${article.thumbnail}" alt="${article.title}"></div>
                            </#if>
                            <div class="text">${article.description}</div>
                        </div>
                        <div class="item-footer">
                            <a href="#" class="text-muted"><i class="icon-comments"></i> ${article.viewCount}</a> &nbsp; <span class="text-muted">${article.createTime?string('yyyy-MM-dd HH:mm')}</span>
                        </div>
                    </div>
                    </#list>
                </div>
            </div>
            <div class="col-md-4">
                <a href="${basePath}/article/add" class="btn btn-block btn-lg btn-info">发布文章</a>
                <div class="panel">
                    <div class="panel-heading">
                        文章栏目
                    </div>
                    <div class="panel-body">
                        <a href="${basePath}/article/list" class="btn btn-primary">全部</a>
                        <#list articleCateList as articleCate>
                            <a href="${basePath}/article/list?cid=${articleCate.id}" class="btn btn-primary">${articleCate.name}</a>
                        </#list>
                    </div>
                </div>
                <div class="panel">
                    <div class="panel-heading">
                        热门文章
                    </div>
                    <div class="panel-body article-hot-list">
                        <ul>
                        <@cms_article_list cid=0 sort='view-count' num=10 day=30; article>
                            <#list articleList as article>
                                <li><i class="icon-hand-right main-text-color"></i> <a href="${basePath}/article/detail/${article.id}">${article.title}</a></li>
                            </#list>
                        </@cms_article_list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/${jeesnsConfig.frontTemplate}/common/footer.ftl"/>
</body>
</html>