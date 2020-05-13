<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改收货地址 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script>
        var basePath = "${basePath}";
        var _success = function () {
            window.location.href = basePath + "/member/deliveryAddress/list";
        }
    </script>
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script src="${basePath}/res/common/js/jeesns.area.js"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
    <script src="${basePath}/res/plugins/My97DatePicker/WdatePicker.js"></script>
</head>

<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <#include "/member/common/settingMenu.ftl"/>
            <div class="col-sm-10 col-xs-12">
                <div class="col-xs-12 white-bg">
                    <div class="list list-condensed ">
                        <header>
                            <h3><i class="icon-list-ul"></i> 修改收货地址</h3>
                        </header>
                        <div class="tab-content m-t-10">
                            <form class="form-horizontal m-t jeesns_form" action="${basePath}/member/deliveryAddress/update" method="post" callback="_success">
                                <input name="id" class="form-control" type="hidden" data-type="require" value="${deliveryAddress.id}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">收件人姓名：</label>
                                    <div class="col-sm-8">
                                        <input id="name" name="name" class="form-control required" type="text" data-type="require" alt="收件人姓名" value="${deliveryAddress.name}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">手机号：</label>
                                    <div class="col-sm-8">
                                        <input id="phone" name="phone" class="form-control" type="text" data-type="require" alt="手机号" value="${deliveryAddress.phone}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">所在地区：</label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="js-province" js-default="${deliveryAddress.province}" name="province">
                                        </select>
                                    </div>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="js-city" js-default="${deliveryAddress.city}" name="city">
                                        </select>
                                    </div>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="js-area" js-default="${deliveryAddress.area}" name="area">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">详细地址：</label>
                                    <div class="col-sm-8">
                                        <input id="address" name="address" class="form-control required" type="text" data-type="require" alt="详细地址" value="${deliveryAddress.address}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">邮政编码：</label>
                                    <div class="col-sm-8">
                                        <input id="zip" name="zip" class="form-control required" type="text" data-type="require" alt="邮政编码" value="${deliveryAddress.zip}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label required">设为默认：</label>
                                    <div class="col-sm-8">
                                        <div class="switch">
                                            <input type="checkbox" name="isDefault" value="1" ${(deliveryAddress.isDefault == 1)?string("checked","")}>
                                            <label>默认</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-8 col-sm-offset-2">
                                        <input type="submit" class="btn btn-primary block full-width m-b" value="保存">
                                    </div>
                                </div>
                            </form>
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