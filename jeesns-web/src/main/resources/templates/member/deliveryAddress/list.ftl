<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>收货地址 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico">
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
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <#include "/member/common/settingMenu.ftl"/>
            <div class="col-sm-10 col-xs-12">
                <div class="col-xs-12 white-bg">
                    <div class="list list-condensed">
                        <header>
                            <h3>
                                <i class="icon-list-ul"></i> 收货地址
                                <span class="pull-right">
                                    <a class="btn btn-primary right-btn m-t-n4" href="${basePath}/member/deliveryAddress/add">添加</a>
                                </span>
                            </h3>
                        </header>
                        <div class="items items-hover">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>收件人</th>
                                        <th>手机号</th>
                                        <th>邮政编码</th>
                                        <th width="50%">地址</th>
                                        <th width="80px">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list deliveryAddressList as deliveryAddress>
                                    <tr>
                                        <td>${deliveryAddress.name}</td>
                                        <td>${deliveryAddress.phone}</td>
                                        <td>${deliveryAddress.zip}</td>
                                        <td>
                                            ${deliveryAddress.province}
                                            ${deliveryAddress.city}
                                            ${deliveryAddress.area}
                                            ${deliveryAddress.address}
                                            ${(deliveryAddress.isDefault == 1)?string("<span class='label label-success'>默认</span>","")}
                                        </td>
                                        <td>
                                            <a href="${basePath}/member/deliveryAddress/edit/${deliveryAddress.id}">修改</a>
                                            <a target="_jeesnsLink" href="javascript:void(0)" data-href="${basePath}/member/deliveryAddress/delete/${deliveryAddress.id}" confirm="确定要删除该地址吗？" callback="reload">删除</a>
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
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
