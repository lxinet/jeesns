<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>我要提问 - 问答 - ${SITE_NAME} - Powered By JEESNS</title>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src=${basePath}"/res/common/js/html5shiv.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/front/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/plugins/webuploader/webuploader.min.js"></script>
    <script src="${basePath}/res/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        var basePath = "${basePath}";
        var uploadServer = "${basePath}/uploadImage";
        $(function () {
            CKEDITOR.replace('content');
        });
        function _success(data) {
            data = eval('('+data+')');
            window.location.href = "${basePath}/question/detail/"+data.data;
        }
    </script>
    <script src="${basePath}/res/plugins/webuploader/upload.js"></script>
    <script src="${basePath}/res/front/js/question.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="article-detail">
                    <form class="form-horizontal jeesns_form" role="form" action="${basePath}/question/ask" method="post" onsubmit="ckUpdate();" callback="_success">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标题</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="title" name="title" placeholder="标题" data-type="require">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">问答板块</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="typeId" id="typeId" onchange="changeBonusType()">
                                    <#list questionTypeList as type>
                                    <option value="${type.id}" bonus-type="${type.bonusType}">${type.name}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">描述</label>
                            <div class="col-sm-10">
                                <textarea class="ckeditor" cols="80" id="content" name="content" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">悬赏</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control bonus-input" id="bonus" name="bonus" placeholder="悬赏" data-type="require,integer" value="0">
                                <span id="bonus-type">积分</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-10">
                                <button type="submit" class="btn btn-info jeesns-submit">保存</button>
                                <a href="javascript:history.go(-1)" class="btn btn-default jeesns-submit">取消</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script>
    $(document).ready(function () {
        changeBonusType();
    })

    function changeBonusType() {
        question.changeBonusType($("#typeId").find('option:selected').attr('bonus-type'))
    }
</script>
</body>
</html>