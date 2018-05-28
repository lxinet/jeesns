<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员激活 - ${SITE_NAME} - Powered By JEESNS</title>
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
            window.location.href = '${basePath}/member/login';
        }
    </script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="animated fadeInDown">
    <div class="row login-panel">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <div class="ibox-content">
                <h2 class="font-bold">激活账号</h2>
                <form class="m-t jeesns_form" action="${basePath}/member/active" method="post" callback="_success">
                    <div class="form-group">
                        邮箱
                        <input type="text" class="form-control" disabled data-type="require" value="${loginUser.email}">
                    </div>
                    <div class="form-group">
                        验证码
                        <input type="text" class="form-control" name="randomCode" placeholder="验证码" data-type="require">
                    </div>
                    <button type="button" class="btn btn-primary m-b" id="getValidCodeBtn">获取验证码</button>
                    <button type="submit" class="btn btn-primary m-b">激活账号</button>
                </form>
                <p></p>

            </div>
        </div>
    </div>
</div>
<#include "/member/common/footer.ftl"/>
<script>
    $(document).ready(function () {
        $("#getValidCodeBtn").click(function () {
            var index;
            var _this = this;
            $(_this).attr("disabled","disabled");
            $.ajax({
                url:"${basePath}/member/sendEmailActiveValidCode",
                type:"get",
                dataType:"json",
                beforeSend: function(){
                    index = jeesnsDialog.loading();
                },
                error: function(){
                    jeesnsDialog.close(index);
                    jeesnsDialog.errorTips("请求失败");
                },
                success:function(res){
                    jeesnsDialog.close(index);
                    if(res.code == 0){
                        jeesnsDialog.successTips(res.message);
                    }else {
                        jeesnsDialog.errorTips(res.message);
                    }
                    window.sendSmsID;
                    window.curCount = 60;//当前剩余秒数
                    $(_this).attr("disabled", "true");
                    $(_this).html(window.curCount + "秒");
                    window.sendSmsID = window.setInterval(function() {
                        if (window.curCount == 1) {
                            window.clearInterval(window.sendSmsID);//停止计时器
                            $(_this).removeAttr("disabled");//启用按钮
                            $(_this).html("获取验证码");
                        }else {
                            window.curCount--;
                            $(_this).html(window.curCount + "秒");
                        }
                    }, 1000);
                }
            });
        });
    });
</script>
</body>
</html>
