<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${goods.title} - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${basePath}/res/front/js/jeesns.js?v1.4"></script>
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.13.1/styles/default.min.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/9.13.1/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <script src="${basePath}/res/front/js/cms.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-xs-12 shop">
                <div class="goods-detail">
                    <div class="head">
                        <div class="col-sm-4">
                            <div class="thumbnail">
                                <img src="${basePath}${goods.thumbnail}" alt="${goods.title}">
                            </div>
                        </div>
                        <div class="col-sm-8 info">
                            <h1>${goods.title}</h1>
                            <div class="detail-info">
                                <div class="price">
                                    价格：<span>￥${goods.price?string("#.00")}</span>
                                </div>
                                <div class="stock">
                                    库存：<span>${goods.stock}</span>
                                </div>
                                <div class="buy">
                                    <a href="${basePath}/article/list" class="btn btn-info jeesns-submit">立即购买</a>
                                    <a href="javascript:void(0)" data-href="${basePath}/member/shopCart/save" data-method="post" data-data="{goodsId:'${goods.id}',num:'1'}" target="_jeesnsLink" class="btn btn-default">加入购物车</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <div class="panel-heading">
                           详细介绍
                        </div>
                        <div class="panel-body content">
                            ${goods.content}
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
</body>
</html>