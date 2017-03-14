<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的私信 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${base}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns.css" rel="stylesheet">
    <link href="${base}/res/common/css/jeesns-skin.css" rel="stylesheet">
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
        var loginMemberId = ${loginUser.id};
        var base = "${base}";
    </script>
    <script src="${base}/res/modules/message.js"></script>
</head>
<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox chat-view">
                    <div class="ibox-title">
                        <small class="pull-right text-muted" id="user-name"></small>
                        私信
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="chat-users">
                                    <div class="users-list" id="users-list">

                                    </div>
                                    <div class="load-more">
                                        <a href="javascript:void(0)" class="contacts-load-more-a" onclick="contactsLoadMore()" style="display: none;">加载更多...</a>
                                    </div>
                                </div>

                            </div>
                            <div class="col-md-9 ">
                                <div class="chat-discussion">
                                    <div class="chat-load-more">
                                        <a href="javascript:void(0)" class="message-load-more-a" onclick="messageLoadMore()" style="display: none;">加载更多...</a>
                                    </div>
                                    <div class="chat-discussion-content">

                                    </div>
                                    <div class="no-message"> 暂无聊天记录</div>
                                </div>
                                <div class="send-message-area">
                                     <textarea class="form-control message-input" name="content" id="content"></textarea>
                                    <button class="btn btn-info pull-right sendMessage">发送</button>
                                </div>
                            </div>
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