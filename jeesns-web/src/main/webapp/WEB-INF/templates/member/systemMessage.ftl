<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统消息 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico">
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <#include "/member/common/memberInfo.ftl"/>
    <div class="container">
        <div class="row m-t-10">
            <div class="col-sm-2 col-xs-12">
                <ul class="list-group">
                    <li class="list-group-item"><a href="${basePath}/member/message">私信</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}">动态</a></li>
                    <li class="list-group-item"><a href="${basePath}/member/picture/album">相册</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/fans">粉丝</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/follows">关注</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/article">文章</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/groupTopic">群帖</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/weibo">微博</a></li>
                    <li class="list-group-item"><a href="${basePath}/u/${loginUser.id}/home/group">关注群组</a></li>
                </ul>
            </div>
            <div class="col-sm-10 col-xs-12">
                <div class="col-xs-12 white-bg">
                    <div class="list list-condensed">
                        <header>
                            <h3><i class="icon-list-ul"></i> 系统消息</h3>
                        </header>
                        <div class="items items-hover">
                            <#list messageModel.data as message>

                            <div class="item">
                                <div class="item-heading">
                                    <div class="pull-right"><span class="text-muted">${message.createTime?string('yyyy-MM-dd HH:mm:ss')}</span></div>
                                    <h4><a href="${basePath}/u/${message.member.id}"><strong>@${message.member.name} </strong></a>：${message.content}</h4>
                                </div>
                                <div class="item-content">
                                    <div class="text">
                                        ${message.description}
                                    </div>
                                </div>
                            </div>
                            </#list>
                            <ul class="pager pagination pagination-sm no-margin pull-right"
                                url="${basePath}/member/"
                                currentPage="${messageModel.page.pageNo}"
                                pageCount="${messageModel.page.totalPage}">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>