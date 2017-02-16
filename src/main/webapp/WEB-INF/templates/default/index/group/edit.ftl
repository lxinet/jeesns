<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/animate.min.css" rel="stylesheet">
    <link href="${base}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${base}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <link href="${base}/res/plugins/awesomeBootstrapCheckbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/webuploader/webuploader.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/plugins/jquerytags/js/jquery.tags.js"></script>
    <script type="text/javascript">
        var basePath = "${base}";
        var uploadServer = "${managePath}/uploadImage";
    </script>
    <script src="${base}/res/plugins/webuploader/upload.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content article">
    <div class="container">
        <div class="row">
            <div class="col-sm-22">
                <ol class="breadcrumb">
                    <li><a href="${base}/">首页</a></li>
                    <li><a href="${base}/group/">群组</a></li>
                    <li><a href="${base}/group/detail/${group.id}">${group.name}</a></li>
                    <li class="active">修改</li>
                </ol>
                <div class="ibox">
                    <div class="ibox-content">
                        <form class="form-horizontal jeesns_form" role="form" action="${base}/group/update"
                              method="post">
                            <input type="hidden" class="form-control" id="id" name="id" value="${group.id}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="名称"
                                           data-type="require" value="${group.name}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstname" class="col-sm-2 control-label">图标</label>
                                <div class="col-sm-8">
                                    <img src="${base}${group.logo}" height="80px"/>
                                    <div id="uploader" class="wu-example">
                                        <!--用来存放文件信息-->
                                        <input type="hidden" id="thumbnail" name="logo">
                                        <div id="preview" class="uploader-list"></div>
                                        <div id="imagesList" class="uploader-list"></div>
                                        <div class="btns">
                                            <div id="picker">选择文件</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">介绍</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3" name="introduce" alt="介绍">${group.introduce}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputmanagers">输入会员昵称，每个管理员用空格结束，昵称不存在管理员不会添加
                                    <input type="hidden" class="form-control" id="managers" name="managers" value="${managerNames}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标签</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputtags">每个标签用空格结束
                                    <input type="hidden" class="form-control" id="tags" name="tags" value="${group.tags}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">是否可以发帖</label>
                                <div class="col-sm-8">
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="canPost1" value="1" name="canPost" <#if group.canPost==1>checked</#if>>
                                        <label for="canPost1">可以发帖</label>
                                    </div>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="canPost2" value="0" name="canPost" <#if group.canPost==0>checked</#if>>
                                        <label for="canPost2">不可以发帖</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">帖子是否需要审核</label>
                                <div class="col-sm-8">
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="topicReview1" value="1" name="topicReview" <#if group.topicReview==1>checked</#if>>
                                        <label for="topicReview1">需要审核</label>
                                    </div>
                                    <div class="radio radio-info radio-inline">
                                        <input type="radio" id="topicReview2" value="0" name="topicReview" <#if group.topicReview==0>checked</#if>>
                                        <label for="topicReview2">不需要审核</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-8">
                                    <button type="submit" class="btn btn-info jeesns-submit">修改</button>
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
<script>
    $(function () {
        var managerNames = "${managerNames}";
        var managerNamesArr = managerNames.split(",");
        for(var i=managerNamesArr.length-1;i>=0;i--){
            $('#inputmanagers').tags(managerNamesArr[i]);
        }
        setInterval(function () {
            $('#managers').val($('#inputmanagers').val());
        }, 500);
        var tags = "${group.tags}";
        var tagsarr = tags.split(",");
        for(var i=tagsarr.length-1;i>=0;i--){
            $('#inputtags').tags(tagsarr[i]);
        }
        setInterval(function () {
            $('#tags').val($('#inputtags').val());

        }, 500);
    });
</script>
</body>
</html>