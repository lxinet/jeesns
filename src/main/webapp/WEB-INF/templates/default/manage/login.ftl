<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>后台登录 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" >
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns1.css" rel="stylesheet">
    <link href="${base}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <script src="${base}/res/common/js/jquery-1.11.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
</head>

<body>
<div class="container">
    <form class="jeesns-signin jeesns_form" action="login" method="post">
        <h2 class="jeesns-signin-heading">JEESNS后台登录</h2>
        <input type="text" class="form-control" placeholder="用户名" name="name" data-type="require" autofocus>
        <input type="password" class="form-control" placeholder="密码" name="password" data-type="require">
        <button class="btn btn-lg jeesns-btn btn-block" type="submit">登录</button>
    </form>
</div>
</body>
</html>