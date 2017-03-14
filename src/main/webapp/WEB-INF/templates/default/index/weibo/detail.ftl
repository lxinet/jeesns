<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${weibo.member.name}${WEIBO_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns-skin.css" rel="stylesheet">
    <link href="${base}/res/plugins/emoji/css/emoji.css" rel="stylesheet">
    <link href="${base}/res/plugins/gallery/css/blueimp-gallery.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.min.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/plugins/emoji/js/underscore-min.js"></script>
    <script src="${base}/res/plugins/emoji/js/editor.js"></script>
    <script src="${base}/res/plugins/emoji/js/emojis.js"></script>
    <script src="${base}/res/plugins/gallery/js/jquery.blueimp-gallery.min.js"></script>
    <script>
        var base = "${base}";
        var weiboId = ${weibo.id};
    </script>
    <script src="${base}/res/modules/weibo.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <div class="col-sm-9">
                <ol class="breadcrumb">
                    <li><a href="${base}/">首页</a></li>
                    <li><a href="${base}/weibo/list">微博</a></li>
                    <li class="active">详情</li>
                </ol>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>
                        <a href="${base}/u/${weibo.member.id}" target="_blank">${weibo.member.name}</a>
                            <small>发布于 ${weibo.createTime?string("yyyy-MM-dd HH:mm:ss")}</small>
                        </h5>
                        <div class="ibox-tools">
                            <#if loginUser?? && (loginUser.id == weibo.member.id || loginUser.isAdmin &gt; 0)>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="${base}/weibo/delete/${weibo.id}" target="_jeesnsLink">删除</a>
                                </li>
                            </ul>
                            </#if>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <p>${weibo.content}</p>
                        <div class="lightBoxGallery">
                        <#list weibo.pictures as picture>
                            <a href="${base}${picture.path}" title="${weibo.member.name}" data-gallery=""><img src="${base}${picture.thumbnailPath}"/></a>
                        </#list>
                            <div id="blueimp-gallery" class="blueimp-gallery">
                                <div class="slides"></div>
                                <h3 class="title"></h3>
                                <a class="prev">‹</a>
                                <a class="next">›</a>
                                <a class="close">×</a>
                                <a class="play-pause"></a>
                                <ol class="indicator"></ol>
                            </div>
                        </div>
                        <div class="text-right">
                        <#if weibo.isFavor==0>
                            <a class="text-primary weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                        <#else>
                            <a class="text-success weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-up"></i> ${weibo.favor}</a>
                        </#if>
                        </div>
                    </div>
                </div>
                <div class="ibox-content" id="comment">
                    <h3>评论(${weibo.commentCount})</h3>
                    <hr>
                    <div class="row">
                        <div class="col-lg-12">
                            <form class="form-horizontal m-t jeesns_form" action="${base}/weibo/comment/${weibo.id}" method="post">
                                <textarea rows="3" name="content" style="width:100%;" class="form-control weibo-comment-textarea" data-type="require" alt="评论内容" id="weibo-content" maxlength="${WEIBO_POST_MAXCONTENT}"></textarea>
                                <div class="row emoji-container" id="emoji">
                                    <i class="fa fa-smile-o emoji-tbtn"></i>
                                    <span class="pull-right mg-r-15 mg-t-10">
                                        <span id="weibo-words" class="mg-r-5">0/${WEIBO_POST_MAXCONTENT}</span>
                                        <input type="submit" value="评论" class="btn btn-primary">
                                    </span>
                                </div>
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
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox float-e-margins">
                        <div>
                            <div class="ibox-content profile-element">
                                <a href="${base}/u/${weibo.member.id}" target="_blank">
                                <img alt="image" class="img-circle mg-l-30" src="${base}${weibo.member.avatar}"/>
                                </a>
                            </div>
                            <div class="ibox-content profile-content">
                                <h4>
                                    <strong>
                                        <a href="${base}/u/${weibo.member.id}" target="_blank">${weibo.member.name!''}</a>
                                    </strong>
                                </h4>
                                <p>${weibo.member.email!''}</p>
                                <p>
                                ${weibo.member.introduce!''}
                                </p>
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
        $(".weibo-favor").click(function () {
            weibo.favor($(this),"${base}")
        });
        var pageNo = 1;
        weibo.commentList(weiboId,pageNo);
        $("#moreComment").click(function () {
            pageNo ++;
            weibo.commentList(weiboId,pageNo);
        });
        $('#emoji').emoji({
            insertAfter: function(item){
                $('#weibo-content').insertContent(':'+item.name+':')
            }
        },"${base}");
    });
</script>
</body>
</html>