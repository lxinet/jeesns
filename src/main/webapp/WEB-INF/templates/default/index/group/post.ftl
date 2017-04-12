<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发帖 - ${group.name} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/animate.min.css" rel="stylesheet">
    <link href="${base}/res/plugins/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet">
    <link href="${base}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${base}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/ueditor/ueditor.config.js"></script>
    <script src="${base}/res/plugins/ueditor/ueditor.all.js"></script>
    <script src="${base}/res/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="${base}/res/plugins/webuploader/webuploader.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script type="text/javascript">
        UE.delEditor('jeesns-editor');
        UE.getEditor('jeesns-editor', {
            serverUrl: '${base}/res/plugins/ueditor/jsp/controller.jsp',
            initialFrameHeight: 300
        });
        var basePath = "${base}";
        var uploadServer = "${base}/thumbnailUploadImage";
    </script>
    <script src="${base}/res/plugins/webuploader/upload.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content article">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <ol class="breadcrumb">
                    <li><a href="${base}/">首页</a></li>
                    <li><a href="${base}/group/">群组</a></li>
                    <li><a href="${base}/group/detail/${group.id}">${group.name}</a></li>
                    <li class="active">发帖</li>
                </ol>
                <div class="ibox">
                    <div class="ibox-content">
                        <form class="form-horizontal jeesns_form" role="form" action="${base}/group/post"
                              method="post">
                            <input type="hidden" name="groupId" value="${group.id}">
                            <div class="form-group">
                                <label for="firstname" class="col-sm-1 control-label">标题</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="title" name="title" placeholder="标题"
                                           data-type="require">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-1 control-label">缩略图</label>
                                <div class="col-sm-10">
                                    <div id="uploader" class="wu-example">
                                        <!--用来存放文件信息-->
                                        <input type="hidden" id="thumbnail" name="thumbnail">
                                        <div id="preview" class="uploader-list"></div>
                                        <div id="imagesList" class="uploader-list"></div>
                                        <div class="btns">
                                            <div id="picker">选择文件</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-1 control-label">内容</label>
                                <div class="col-sm-10">
                                    <script type="text/plain" id="jeesns-editor" name="content"></script>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button type="submit" class="btn btn-info jeesns-submit">发帖</button>
                                    <a class="btn btn-default" href="${base}/group/detail/${group.id}">返回</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/index/common/footer.ftl"/>
</body>
</html>