<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>卡密充值 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico">
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
                            <h3><i class="icon-list-ul"></i> 卡密充值</h3>
                        </header>
                        <div class="tab-content m-t-10">
                            <form class="form-horizontal m-t-100 m-b-50 jeesns_form" action="${basePath}/member/cdkRecharge" method="post" callback="reload">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用户名：</label>
                                    <div class="col-sm-6">
                                    ${member.name}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">邮箱：</label>
                                    <div class="col-sm-8">
                                    ${member.email}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">账户余额：</label>
                                    <div class="col-sm-6">
                                    ${member.money}
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">充值卡卡号：</label>
                                    <div class="col-sm-6">
                                        <input id="cardkeyNo" name="cardkeyNo" class="form-control" type="text" data-type="require">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-6 col-sm-offset-2">
                                        <input type="submit" class="btn btn-primary block full-width m-b" value="充值">
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