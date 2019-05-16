<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品分类管理 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
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
    <script src="${basePath}/res/common/js/html5shiv.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
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
            <h4>
                <a href="javascript:void(0)" data-href="${managePath}/shop/goodsCate/add" target="_jeesnsOpen" title="添加分类" height="500px">
                    <label class="label label-info">添加</label>
                </a>
            </h4>
            <ol class="breadcrumb">
                <li><a href="${managePath}/index"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">商品分类管理</li>
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
                                    <th>分类名称</th>
                                    <th>序号</th>
                                    <th>状态</th>
                                    <th style="width: 100px;">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list topList as topCate>
                                <tr>
                                    <td>${topCate.name}</td>
                                    <td>${topCate.sort}</td>
                                    <td>
                                        <#if topCate.status==0>
                                            启用
                                            <#else>
                                            禁用
                                        </#if>
                                    </td>
                                    <td>
                                        <a href="javascript:void(0)" data-href="${managePath}/shop/goodsCate/edit/${topCate.id}" target="_jeesnsOpen"
                                           title="编辑分类" height="500px">
                                            <span class="label label-warning"><i class="fa fa-edit green"></i></span>
                                        </a>
                                        <a target="_jeesnsLink" href="javascript:void(0)" data-href="${managePath}/shop/goodsCate/delete/${topCate.id}"
                                           confirm="确定要删除分类吗？删除后分类下的商品将会移到未分类中！" callback="reload">
                                            <span class="label label-danger"><i class="fa fa-trash red"></i></span>
                                        </a>
                                    </td>
                                </tr>
                                    <#list sonList as sonCate>
                                        <#if sonCate.fid == topCate.id>
                                            <tr>
                                                <td style="padding-left: 50px">${sonCate.name}</td>
                                                <td>${sonCate.sort}</td>
                                                <td>
                                                    <#if sonCate.status==0>
                                                        启用
                                                    <#else>
                                                        禁用
                                                    </#if>
                                                </td>
                                                <td>
                                                    <a href="javascript:void(0)" data-href="${managePath}/shop/goodsCate/edit/${sonCate.id}" target="_jeesnsOpen"
                                                       title="编辑分类" height="500px">
                                                        <span class="label label-warning"><i class="fa fa-edit green"></i></span>
                                                    </a>
                                                    <a target="_jeesnsLink" href="javascript:void(0)" data-href="${managePath}/shop/goodsCate/delete/${sonCate.id}"
                                                       confirm="确定要删除分类吗？删除后分类下的商品将会移到未分类中！" callback="reload">
                                                        <span class="label label-danger"><i class="fa fa-trash red"></i></span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </#if>
                                    </#list>
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
</body>
</html>