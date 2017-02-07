<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员注册 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.min.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/metisMenu/metisMenu.js"></script>
    <script src="${base}/res/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/manage.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>

</head>
<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="animated fadeInDown">
    <div class="row login-panel">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="ibox-content">
                <h3>欢迎加入${SITE_NAME}</h3>
                <form class="m-t jeesns_form" action="${base}/member/register" method="post">
                    <div class="form-group">
                        用户名
                        <input type="text" name="name" class="form-control" placeholder="用户名" data-type="require">
                    </div>
                    <div class="form-group">
                        邮箱
                        <input type="email" name="email" class="form-control" placeholder="邮箱" data-type="require,email">
                    </div>
                    <div class="form-group">
                        密码
                        <input type="password" id="password" name="password" class="form-control" placeholder="密码" data-type="require">
                    </div>
                    <div class="form-group">
                        确认密码
                        <input type="password" name="repassword" class="form-control" placeholder="确认密码" data-type="require" data-rule="equal[password]">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">注册</button>
                    <p></p>
                    <p class="text-muted text-center">
                    <a href="forgetpwd">忘记密码?</a> |
                    <a href="login">我要登录</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#include "/member/common/footer.ftl"/>
</body>

</html>
