<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑文章 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${base}/res/plugins/webuploader/webuploader.css" rel="stylesheet">
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
    <script src="${base}/res/plugins/ueditor/ueditor.config.js"></script>
    <script src="${base}/res/plugins/ueditor/ueditor.all.js"></script>
    <script src="${base}/res/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="${base}/res/plugins/webuploader/webuploader.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script type="text/javascript">
        UE.delEditor('jeesns-editor');
        UE.getEditor('jeesns-editor',{
            serverUrl: '${base}/res/plugins/ueditor/jsp/controller.jsp',
            initialFrameHeight:300
        });
        var basePath = "${base}";
        var uploadServer = "${managePath}/uploadImage";
    </script>
    <script src="${base}/res/plugins/webuploader/upload.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal jeesns_form" role="form" action="${managePath}/cms/article/update" method="post">
                    <input type="hidden" class="form-control" name="id" value="${article.id}">
                    <div class="form-group">
                        <label for="lastname" class="col-sm-1 control-label">栏目</label>
                        <div class="col-sm-3">
                            <select class="form-control" name="cateId" data-type="selected" alt="栏目">
                                <option value="">=请选择栏目=</option>
                            <#list cateList as mainMenu>
                                <option value="${mainMenu.id}" <#if article.articleCate.id==mainMenu.id>selected</#if>>${mainMenu.name}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" placeholder="标题" data-type="require" value="${article.title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">缩略图</label>
                        <div class="col-sm-10">
                            <div id="uploader">
                                <!--用来存放文件信息-->
                                <input type="hidden" id="thumbnail" name="thumbnail" value="${article.thumbnail}">
                                <div id="preview" class="uploader-list">
                                <#if article.thumbnail?? && article.thumbnail!=''>
                                    <img src="${base}${article.thumbnail}" width="100px" height="100px"/>
                                </#if>
                                </div>
                                <div id="imagesList" class="uploader-list"></div>
                                <h4 class="info"></h4>
                                <p class="state"></p>
                                <div class="btns">
                                    <div id="picker">选择文件</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">描述</label>
                        <div class="col-sm-8">
                            <textarea class="form-control" rows="3" name="description">${article.description}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">内容</label>
                        <div class="col-sm-10">
                            <script type="text/plain" id="jeesns-editor" name="content" height="300px">${article.content}</script>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">来源</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="source" name="source" value="${article.source}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="writer" name="writer" value="${article.writer}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-1 control-label">浏览数</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="viewCount" name="viewCount" placeholder="浏览数" value="${article.viewCount}" data-type="integer">
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