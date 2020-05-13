<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加分类 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
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
    <script src="${basePath}/res/common/js/html5shiv.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/plugins/iCheck/icheck.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form method="post" action="${managePath}/shop/goodsCate/save" class="jeesns_form" callback="parentReload">
                    <div class="form-group">
                        <label class="control-label">分类名字:</label>
                        <input type="text" class="form-control" id="name" name="name" data-type="require" alt="分类名字">
                    </div>
                    <div class="form-group">
                        <label class="control-label">上级分类</label>
                        <select class="form-control" name="fid" data-type="selected">
                            <option value="0" selected>顶级分类</option>
                            <#list topList as goodsCate>
                                <option value="${goodsCate.id}">${goodsCate.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">状态</label>
                        <select class="form-control" name="status" data-type="selected">
                            <option value="0" selected>启用</option>
                            <option value="1">禁用</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">分类序号:</label>
                        <input type="text" class="form-control" id="sort" name="sort" data-type="require,integer"
                               alt="分类序号" value="50">越大越靠前
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
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