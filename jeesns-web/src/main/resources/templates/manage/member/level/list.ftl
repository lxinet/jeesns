<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员VIP等级管理 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
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
            <h1>会员VIP等级管理</h1>
            <ol class="breadcrumb">
                <li><a href="${managePath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">会员VIP等级管理</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">
                                <a href="javascript:void(0)" data-href="${managePath}/member/level/add" target="_jeesnsOpen"
                                   title="添加会员VIP等级" width="400px" height="200px">
                                    <span class="label label-info">添加</span>
                                </a>
                            </h3>
                            <div class="box-tools">

                            </div>
                        </div>
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th width="150px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list as memberLevel>
                                <tr>
                                    <td>${memberLevel.id}</td>
                                    <td>${memberLevel.name}
                                    <#if memberLevel.id != 1>
                                        <span class="label label-danger">VIP</i>
                                    </#if>
                                    </td>
                                    <td>${memberLevel.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <#if memberLevel.id != 1>
                                            <a href="javascript:void(0)" data-href="${managePath}/member/level/edit/${memberLevel.id}" target="_jeesnsOpen"
                                               title="修改广告" width="400px" height="200px">
                                                <span class="label label-info">修改</span>
                                            </a>
                                            <a class="marg-l-5" target="_jeesnsLink"
                                               href="javascript:void(0)" data-href="${managePath}/member/level/delete/${memberLevel.id}" confirm="确定要删除会员等级吗？" callback="reload">
                                                <span class="label label-danger"><i class="fa fa-trash red"></i></span>
                                            </a>
                                        </#if>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
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

