<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>JEESNS后台管理登录 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${base}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${base}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/manage/js/app.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
</head>
<body class="hold-transition login-page" style="background-image: url(${base}/res/common/images/loginbg.jpg);">
<div class="login-box">
    <div class="login-logo">
        <a href="http://www.jeesns.cn" target="_blank"><b>JEE</b>SNS</a>
    </div>
    <div class="login-box-body">
        <p class="login-box-msg">JEESNS后台管理登录</p>

        <form action="${managePath}/login" method="post" class="jeesns_form">
            <div class="form-group has-feedback">
                <input type="text" name="name" class="form-control" placeholder="用户名或邮箱">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
            <p>&nbsp;</p>
            <p class="login-box-msg">
                Copyright &copy; 2012-2017 <strong><a href="http://www.jeesns.cn" target="_blank">JEESNS</a>.</strong> All rights
                reserved.
            </p>
        </form>
    </div>
</div>
</body>
</html>
