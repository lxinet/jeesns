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
<div class="loginpage">
    <div class="bg-img">
        <div class="content">
            <div class="login-bg">
                <div class="login-box">
                    <h2>会员登录 <a href="register">没账号？立即注册</a></h2>
                    <form  class="jeesns_form login-form" action="${base}/member/login" method="post">
                        <div class="input-box">
                            <input name="name" type="text" class="input-style" placeholder="用户名/邮箱" data-type="require"/>
                        </div>
                        <div class="input-box">
                            <input name="password" type="password" class="input-style" placeholder="密码" data-type="require"/>
                        </div>
                        <div class="input-box">
                            <input id="login-submit" type="submit" class="submit-btn" value="登录">
                            <p><a href="forgetpwd">忘记密码？</a></p>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/member/common/footer.ftl"/>
</body>

</html>
