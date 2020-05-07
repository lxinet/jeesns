<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>问答 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
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
            <div class="col-sm-8 col-xs-12">
                <div class="items white-bg">
                    <div class="panel-heading">
                        <a href="${basePath}/question/list"><span class="btn btn-info">全部</span></a>
                        <a href="${basePath}/question/list-unsolved"><span class="btn btn-info">待解决</span></a>
                        <a href="${basePath}/question/list-solved"><span class="btn btn-info">已解决</span></a>
                        <a href="${basePath}/question/list-close"><span class="btn btn-info">已关闭</span></a>
                    </div>
                    <#list model.data as question>
                    <div class="item">
                        <div class="item-content article">
                            <div class="item-heading">
                                <h3><a class="title" href="${basePath}/question/detail/${question.id}">${question.title}</a></h3>
                            </div>
                            <div class="text word-break">
                                ${question.description}
                            </div>
                            <div class="item-footer">
                                <i class="icon-eye-open"></i> ${question.viewCount} &nbsp;
                                <#if question.status == 0>
                                <span class="label label-info">待解决</span>
                                <#elseif question.status == 1>
                                <span class="label label-success">已解决</span>
                                <#elseif question.status == -1>
                                <span class="label label-danger">已关闭</span>
                                </#if>
                                <span class="text-muted">${question.createTime?string('yyyy-MM-dd HH:mm')}</span>
                                <#if question.bonus &gt; 0>
                                    <div class="pull-right label label-success">
                                        悬赏：${question.bonus}${(question.questionType.bonusType==0)?string('积分','元现金')}
                                    </div>
                                </#if>
                                <a href="${basePath}/question/list?tid=${question.questionType.id}">

                                </a>
                            </div>
                        </div>
                    </div>
                    </#list>
                        <ul class="pager pagination pagination-sm no-margin pull-right"
                            url="${basePath}/question/list<#if questionType??>?tid=${questionType.id}</#if>"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                </div>
            </div>
            <div class="col-sm-4 col-xs-12">
                <div class="panel">
                    <div class="panel-heading">
                        问答分类
                        <span class="pull-right">
                            <a class="btn btn-primary right-btn m-t-n4" href="${basePath}/question/ask">我要提问</a>
                        </span>
                    </div>
                    <div class="panel-body">
                        <a href="${basePath}/question/" class="btn btn-primary">全部</a>
                        <#list questionTypeList as questionType>
                            <a href="${basePath}/question/list<#if statusName??>-${statusName}</#if>?tid=${questionType.id}" class="btn btn-primary">${questionType.name}</a>
                        </#list>
                    </div>
                </div>
                <@ads id=1>
                    <#include "/tp/ad.ftl"/>
                </@ads>
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