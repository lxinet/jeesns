<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>问答管理 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src=${basePath}"/res/common/js/html5shiv.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<#include "/manage/common/header.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
                <a href="${managePath}/question/list"><span class="btn btn-info">全部</span></a>
                <a href="${managePath}/question/list-unsolved"><span class="btn btn-info">待解决</span></a>
                <a href="${managePath}/question/list-solved"><span class="btn btn-info">已解决</span></a>
                <a href="${managePath}/question/list-close"><span class="btn btn-info">已关闭</span></a>
            <ol class="breadcrumb">
                <li><a href="${managePath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">问答管理</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>标题</th>
                                    <th>会员</th>
                                    <th>奖金</th>
                                    <th>回答数</th>
                                    <th>状态</th>
                                    <th>时间</th>
                                    <th style="width: 80px;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list model.data as question>
                                <tr>
                                    <td>${question.id}</td>
                                    <td><a href="${basePath}/question/detail/${question.id}" target="_blank">${question.title}</a></td>
                                    <td>${question.member.name}</td>
                                    <td>
                                        ${question.bonus}${(question.questionType.bonusType == 0)?string('积分',"元现金")}
                                    </td>
                                    <td>
                                        <a href="javascript:void(0)" data-href="${managePath}/question/${question.id}/answer/list" target="_jeesnsOpen"
                                           width="1200px" height="600px" title="回答">
                                            <span class="label label-success">${question.answerCount}</span>
                                        </a>
                                    </td>
                                    <td>
                                        <#if question.status == -1>
                                            已关闭
                                        <#elseif question.status == 0>
                                            待解决
                                        <#else>
                                            已解决
                                        </#if>
                                    </td>
                                    <td>${question.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <a target="_jeesnsLink" href="javascript:void(0)" data-href="${managePath}/question/delete/${question.id}"
                                           confirm="确定要删除问题吗？" callback="reload">
                                            <span class="label label-danger"><i class="fa fa-trash red"></i></span>
                                        </a>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="box-footer clearfix">
                            <ul class="pagination pagination-sm no-margin pull-right"
                                url="${managePath}/question/list<#if statusName??>-${statusName}</#if>"
                                currentPage="${model.page.pageNo}"
                                pageCount="${model.page.totalPage}">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
<#include "/manage/common/footer.ftl"/>
</div>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>