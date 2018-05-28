<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员登录 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/jeesns.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/plugins/metisMenu/metisMenu.js"></script>
    <script src="${basePath}/res/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/manage.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js"></script>
    <script>
        var _success = function () {
            window.location.href = '${basePath}/member/';
        }
    </script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="animated fadeInDown">
    <div class="row login-panel">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="ibox ibox-content">
                <h2 class="font-bold">登录</h2>
                <form class="m-t jeesns_form" action="${basePath}/member/login" method="post" callback="_success">
                    <input type="hidden" name="redirectUrl" value="${redirectUrl}">
                    <div class="form-group">
                        用户名/邮箱
                        <input type="text" class="form-control" name="name" placeholder="用户名/邮箱" data-type="require" callback="_success">
                    </div>
                    <div class="form-group">
                        密码
                        <input type="password" class="form-control" name="password" placeholder="密码"
                               data-type="require">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                </form>
                <p></p>
                <p class="text-muted text-center">
                    <a href="forgetpwd">忘记密码?</a> |
                    <a href="register">我要注册</a>
                </p>
            </div>
        </div>
    </div>
</div>
<#include "/member/common/footer.ftl"/>
</body>

</html>
