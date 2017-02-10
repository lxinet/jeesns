<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${WEIBO_ALIAS}中心 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/common/js/extendPagination.js"></script>
    <script src="${base}/res/modules/weibo.js"></script>
    <script src="${base}/res/plugins/emoji/js/underscore-min.js"></script>
    <script src="${base}/res/plugins/emoji/js/editor.js"></script>
    <script src="${base}/res/plugins/emoji/js/emojis.js"></script>

</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="ibox-content">
                    <form class="form-horizontal m-t jeesns_form" action="${base}/weibo/publish" method="post">
                        <p><textarea cols="3" class="form-control area" name="content" id="weibo-content" maxlength="${WEIBO_POST_MAXCONTENT}"></textarea></p>
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
                        <h5>最新${WEIBO_ALIAS}</h5>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="feed-activity-list">
                            <#list model.data as weibo>
                                <div class="feed-element">
                                    <a href="${base}/u/${weibo.member.id}" target="_blank" class="pull-left">
                                        <img alt="image" class="img-circle" src="${base}${weibo.member.avatar!''}">
                                    </a>
                                    <div class="media-body ">
                                        <strong>
                                            <a href="${base}/u/${weibo.member.id}" target="_blank">${weibo.member.name}</a></strong><br/>
                                        <div class="mg-t-10 mg-b-10">
                                            ${weibo.content}
                                        </div>
                                        <small>${weibo.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                                        (<#if weibo.isFavor==0>
                                            <a class="text-primary weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                                        <#else>
                                            <a class="text-success weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-up"></i> ${weibo.favor}</a>
                                        </#if>
                                        <a href="${base}/weibo/detail/${weibo.id}">评论:${weibo.commentCount}</a>)
                                    </div>
                                </div>
                            </#list>
                            </div>
                            <div class="box-footer clearfix">
                                <ul class="pagination pagination-sm no-margin pull-right"
                                    url="${base}/weibo/list"
                                    currentPage="${model.page.pageNo}"
                                    pageCount="${model.page.totalPage}">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>热门${WEIBO_ALIAS}</h5>
                    </div>
                    <div class="ibox-content">
                        <div>
                            <div class="feed-activity-list">
                            <#list hotList as weibo>
                                <div class="feed-element">
                                    <a href="${base}/u/${weibo.member.id}" target="_blank" class="pull-left">
                                        <img alt="image" class="img-circle" src="${base}${weibo.member.avatar!''}">
                                    </a>
                                    <div class="media-body ">
                                        <strong><a href="${base}/u/${weibo.member.id}" target="_blank" class="pull-left">${weibo.member.name}</a></strong><br/>
                                        <div class="mg-t-10 mg-b-10">
                                            ${weibo.content}
                                        </div>
                                        <#if weibo.isFavor==0>
                                            <a class="text-primary weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                                        <#else>
                                            <a class="text-success weibo-favor" weibo-id="${weibo.id}"><i class="fa fa-thumbs-o-up"></i> ${weibo.favor}</a>
                                        </#if>
                                        <small>${weibo.createTime?string('yyyy-MM-dd HH:mm:ss')}</small>
                                        (<a href="${base}/weibo/detail/${weibo.id}">评论:${weibo.commentCount}</a>)
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
<#include "/index/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
        $(".weibo-favor").click(function () {
            weibo.favor($(this),"${base}")
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