<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>授权管理员 - ${SITE_NAME} - Powered By JEESNS</title>
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
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal jeesns_form" role="form" action="${managePath}/sys/config/managerAdd" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员用户名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name" data-type="require" alt="会员用户名" placeholder="请输入已注册的会员用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-info jeesns-submit">确定授权</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>