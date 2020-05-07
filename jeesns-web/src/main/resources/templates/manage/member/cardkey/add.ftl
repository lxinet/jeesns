<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生成充值卡密 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/iCheck/all.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src=${basePath}"/res/common/js/html5shiv.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="${basePath}/res/plugins/iCheck/icheck.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal jeesns_form" role="form" action="${managePath}/member/cardkey/save" method="post" callback="parentReload">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">生成数量</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="num" name="num" data-type="require,number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">卡密金额</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="money" name="money" data-type="require,double">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">有效期至</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" readonly id="expireTime" name="expireTime" data-type="require" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="background-color: #fff;">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-info jeesns-submit">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>