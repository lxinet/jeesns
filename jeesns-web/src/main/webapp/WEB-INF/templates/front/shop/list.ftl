<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商城 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel-heading">
                    <div class="dropdown">
                        <button class="btn" type="button" data-toggle="dropdown">全部商品分类 <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <#list topCateList as topCate>
                            <li class="dropdown-submenu">
                                <a href="#">${topCate.name}</a>
                                <ul class="dropdown-menu">
                                    <#list sonCateList as sonCate>
                                        <#if sonCate.fid == topCate.id>
                                            <li><a href="${basePath}/shop/list-${sonCate.id}">${sonCate.name}</a></li>
                                        </#if>
                                    </#list>
                                </ul>
                            </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-10 col-xs-12">
                <div class="items white-bg">
                    <#list model.data as article>
                    <div class="item">
                        <div class="item-content article">
                            <div class="media pull-left">
                            <#if article.thumbnail??>
                                <a href="${basePath}/article/detail/${article.id}">
                                    <img src="${basePath}${article.thumbnail}" alt="${article.title}" height="150px" width="220px">
                                </a>
                            </#if>
                            </div>
                            <div class="item-heading">
                                <h3><a class="title" href="${basePath}/article/detail/${article.id}">${article.title}</a></h3>
                            </div>
                            <div class="text word-break">
                                ${article.description}
                            </div>
                            <div class="item-footer">
                                <i class="icon-eye-open"></i> ${article.viewCount} &nbsp;
                                <span class="text-muted">${article.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                <a href="${basePath}/article/list?cid=${article.articleCate.id}">
                                    <div class="pull-right label label-success">
                                        ${article.articleCate.name}
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    </#list>
                        <ul class="pager pagination pagination-sm no-margin pull-right"
                            url="${basePath}/article/list?<#if articleCate??>cid=${articleCate.id}&</#if><#if key??>key=${key}&</#if>"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
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