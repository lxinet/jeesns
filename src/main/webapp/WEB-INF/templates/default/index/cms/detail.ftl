<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${article.title} - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script>
        var base = "${base}";
        var articleId = ${article.id};
    </script>
    <script src="${base}/res/modules/cms.js"></script>
    <script src="${base}/res/modules/archive.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content article">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="text-center article-title">
                            <h2>${article.title}</h2>
                            <div class="text-center">
                                <i class="fa fa-clock-o"></i> ${article.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                <i class="fa fa-eye"> </i> ${article.viewCount} 浏览
                            </div>
                        </div>
                        <div class="content">
                            <p>${article.content}</p>
                        </div>
                        <div class="text-right">
                            <#if loginUser?? && loginUser.id==article.memberId>
                                <a href="${base}/article/edit/${article.id}">编辑</a>
                            </#if>
                            <#if loginUser?? && loginUser.isAdmin==1>
                            <a href="${base}/article/delete/${article.id}" confirm="确定要删除文章吗？" target="_jeesnsLink">删除</a>
                            </#if>
                        </div>
                        <#if article.isFavor == 0>
                            <a class="btn btn-danger btn-rounded btn-outline archive-favor" href="javascript:void(0)" archive-id="${article.archiveId}">
                                <i class="fa fa-heart-o"></i> 喜欢 ${article.favor}
                            </a>
                        <#else>
                            <a class="btn btn-danger btn-rounded archive-favor" href="javascript:void(0)" archive-id="${article.archiveId}">
                                <i class="fa fa-heart"></i> 喜欢 ${article.favor}
                            </a>
                        </#if>
                    </div>
                    <div class="ibox-content" id="comment">
                        <h3>评论</h3>
                        <hr>
                        <div class="row">
                            <div class="col-lg-12">
                                <form class="form-horizontal m-t jeesns_form" action="${base}/article/comment/${article.id}" method="post">
                                    <textarea rows="5" name="content" style="width:100%;" class="form-control" data-type="require" alt="评论内容"></textarea>
                                    <input type="submit" value="评论" class="pull-right btn btn-primary mg-t-10 jeesns-submit">
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mg-t-10" id="commentList">

                            </div>
                            <div class="col-lg-12 mg-t-10">
                                <button class="btn btn-primary btn-block m" id="moreComment" style="display: none"><i class="fa fa-arrow-down"></i> 加载更多</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/index/common/footer.ftl"/>
<script>
    $(document).ready(function () {
        var pageNo = 1;
        cms.commentList(articleId,pageNo);
        $("#moreComment").click(function () {
            pageNo ++;
            cms.commentList(articleId,pageNo);
        });
        $(".archive-favor").click(function () {
            archive.favor($(this),"${base}")
        });
    });
</script>
</body>
</html>