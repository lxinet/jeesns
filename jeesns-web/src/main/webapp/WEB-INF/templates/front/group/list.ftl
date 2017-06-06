<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/res/common/css/jeesns.css">
    <link rel="stylesheet" href="${basePath}/res/common/css/jeesns-skin.css">
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
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>${GROUP_ALIAS}
                        <#if GROUP_APPLY==1>
                            <a class="marg-l-5" href="${basePath}/group/apply" title="申请${GROUP_ALIAS}">
                                <i class="fa fa-plus-circle green"></i>
                            </a>
                        </#if>
                        </h5>
                    </div>
                    <div class="ibox-content">
                        <div class="feed-activity-list">
                        <#list model.data as group>
                            <div class="feed-element">
                                <a href="${basePath}/group/detail/${group.id}" class="pull-left">
                                    <img alt="image" class="img-circle" src="${basePath}${group.logo}">
                                </a>
                                <div class="media-body ">
                                    <h4><strong><a href="${basePath}/group/detail/${group.id}">${group.name}</a></strong></h4>
                                    <p>${group.introduce}</p>
                                    <small class="text-muted">${group.topicCount}篇文章 · ${group.fansCount}人关注</small>
                                </div>
                            </div>
                        </#list>
                        </div>
                        <div class="box-footer clearfix">
                            <ul class="pagination pagination-sm no-margin pull-right"
                                url="${basePath}/group/?key=${key}"
                                currentPage="${model.page.pageNo}"
                                pageCount="${model.page.totalPage}">
                            </ul>
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