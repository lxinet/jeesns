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
    <div class="main-content shop">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel-heading">
                    <div class="cate">
                        <div class="dropdown dropdown-hover">
                            <button class="btn" type="button" data-toggle="dropdown">全部商品分类 <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="${basePath}/shop/list">全部</a></li>
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
                    <div class="cart">
                        <a class="btn btn-success" href="${basePath}/">购物车</a>
                    </div>
                </div>
            </div>
            <div class="col-xs-12">
                <div class="items white-bg">
                    <#list goodsResultModel.data as goods>
                        <div class="col-sm-3 col-xs-6">
                            <div class="item goods">
                                <div class="item-content">
                                    <div class="media pull-left thumbnail">
                                        <#if goods.thumbnail??>
                                            <a href="${basePath}/shop/detail/${goods.id}">
                                                <img src="${basePath}${goods.thumbnail}" alt="${goods.title}">
                                            </a>
                                        </#if>
                                    </div>
                                    <div class="content">
                                        <h4><a class="title" href="${basePath}/shop/detail/${goods.id}">${goods.title}</a></h4>
                                    </div>
                                    <div class="content pull-right">
                                        <div class="price">￥${goods.price?string("#.00")}</div>
                                        <div class="buy"><a class="btn btn-success">购买</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                        <ul class="pager pagination pagination-sm no-margin pull-right"
                            url="${basePath}/shop/list?<#if goodsCate??>cid=${goodsCate.id}&</#if><#if key??>key=${key}&</#if>"
                            currentPage="${goodsResultModel.page.pageNo}"
                            pageCount="${goodsResultModel.page.totalPage}">
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