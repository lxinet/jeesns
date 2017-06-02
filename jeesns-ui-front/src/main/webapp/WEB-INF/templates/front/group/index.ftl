<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
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
        <div class="row white-bg group-list">
        <#list model.data as group>
            <div class="col-md-4">
                <div class="group-detail">
                    <div class="group-logo">
                        <a href="${basePath}/group/detail/${group.id}">
                            <img alt="image" class="img-circle" src="${basePath}${group.logo}" width="100px" height="100px">
                        </a>
                    </div>
                    <div class="group-info">
                        <h4><strong><a href="${basePath}/group/detail/${group.id}">${group.name}</a></strong></h4>
                        <p class="text-muted">${group.introduce}</p>
                        <small class="text-muted">${group.topicCount}篇文章 · ${group.fansCount}人关注</small>
                    </div>
                </div>
            </div>
        </#list>
        </div>
    </div>
</div>
<#include "/${jeesnsConfig.frontTemplate}/common/footer.ftl"/>
</body>
</html>