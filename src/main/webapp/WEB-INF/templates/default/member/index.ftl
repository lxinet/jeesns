<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns-skin.css" rel="stylesheet">
    <link href="${base}/res/plugins/emoji/css/emoji.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${base}/res/common/js/html5shiv.min.js"></script>
    <script src="${base}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${base}/res/common/js/bootstrap.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/modules/weibo.js"></script>
    <script src="${base}/res/plugins/emoji/js/underscore-min.js"></script>
    <script src="${base}/res/plugins/emoji/js/editor.js"></script>
    <script src="${base}/res/plugins/emoji/js/emojis.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>个人资料</h5>
                    </div>
                    <div>
                        <div class="ibox-content profile-element text-center">
                            <a href="${base}/member/avatar">
                                <img alt="image" class="img-circle" src="${base}${loginUser.avatar}" width="80px" height="80px"/>
                            </a>
                            <h4>
                                <strong>
                                    ${loginUser.name!''}
                                    <a href="${base}/member/editInfo"><i class="fa fa-edit"></i></a>
                                </strong>
                            </h4>
                        </div>
                        <div class="ibox-content profile-content">
                            <h5>个人说明</h5>
                            <p>
                            ${loginUser.introduce!''}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox-content">
                    <form class="form-horizontal m-t jeesns_form" action="${base}/weibo/publish" method="post">
                        <p><textarea cols="3" class="form-control" name="content" id="weibo-content" maxlength="${WEIBO_POST_MAXCONTENT}"></textarea></p>
                        <div class="row emoji-container" id="emoji">
                            <i class="fa fa-smile-o emoji-tbtn"></i>
                            <span class="pull-right mg-r-15">
                                 <span id="weibo-words" class="mg-r-5">0/${WEIBO_POST_MAXCONTENT}</span>
                                <input type="submit" value="发布" class="btn btn-primary">
                            </span>
                        </div>
                    </form>
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>最新动态</h5>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="feed-activity-list">
                            <#list weiboModel.data as weibo>
                                <div class="feed-element">
                                    <a href="${base}/u/${weibo.member.id}" class="pull-left">
                                        <img alt="image" class="img-circle" src="${base}${weibo.member.avatar!''}">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">${weibo.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                                        <strong>${weibo.member.name}</strong>发表了${WEIBO_ALIAS}<br/>
                                        ${weibo.content}
                                        <br>
                                        <div class="actions">
                                        </div>
                                    </div>
                                </div>
                            </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/member/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $('#emoji').emoji({
            insertAfter: function(item){
                $('#weibo-content').insertContent(':'+item.name+':')
            }
        },"${base}");
    });
</script>
</body>
</html>