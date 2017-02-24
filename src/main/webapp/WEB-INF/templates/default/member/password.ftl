<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改密码 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${base}/res/common/css/jeesns.css">
    <link rel="stylesheet" href="${base}/res/common/css/jeesns-skin.css">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.min.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
<#include "/member/common/editLeft.ftl"/>
    <div class="col-sm-9">
        <div class="ibox-title">
            <h5>修改密码</h5>
        </div>
        <div class="ibox-content">
            <form class="form-horizontal m-t jeesns_form" action="${base}/member/password" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">旧密码：</label>
                    <div class="col-sm-8">
                        <input id="oldPassword" name="oldPassword" class="form-control" type="password"
                               data-type="require" alt="密码">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">新密码：</label>
                    <div class="col-sm-8">
                        <input id="newPassword" name="newPassword" class="form-control" type="password"
                               data-type="require" alt="新密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认新密码：</label>
                    <div class="col-sm-8">
                        <input id="renewPassword" name="renewPassword" class="form-control" type="password"
                               data-type="require" data-rule="equal[newPassword]" alt="两次密码必须一致">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8 col-sm-offset-2">
                        <input type="submit" class="btn btn-primary block full-width m-b jeesns-submit" value="修改密码">
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
<#include "/member/common/footer.ftl"/>
</body>

</html>
