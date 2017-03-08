<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>动态 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/common/js/extendPagination.js"></script>
    <script src="${base}/res/modules/mem.js"></script>
    <script src="${base}/res/plugins/emoji/js/emojis.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
    <div class="container">
        <div class="row">
            <div class="ibox">
                <div class="ibox-content float-left">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>动态</h5>
                            </div>
                            <div>
                                <div class="feed-activity-list">
                                <#list model.data as actionLog>
                                    <div class="feed-element">
                                        <a href="${base}/u/${actionLog.member.id}" class="pull-left">
                                            <img alt="image" class="img-circle"
                                                 src="${base}${actionLog.member.avatar!''}">
                                        </a>
                                        <div class="media-body ">
                                            <strong><a href="${base}/u/${actionLog.member.id}">${actionLog.member.name}</a> </strong>于${actionLog.createTime?string('yyyy-MM-dd HH:mm')}${actionLog.action.log}：<br/>
                                            <#if actionLog.type==1>
                                                <a href="${base}/article/detail/${actionLog.foreignId}"
                                                   target="_blank">${actionLog.remark}</a>
                                            <#elseif actionLog.type==2>
                                                <p>${actionLog.remark}</p>
                                                <a href="${base}/weibo/detail/${actionLog.foreignId}"
                                                   target="_blank">查看</a>
                                            <#elseif actionLog.type==4>
                                                <a href="${base}/group/topic/${actionLog.foreignId}"
                                                   target="_blank">${actionLog.remark}</a>
                                            </#if>
                                            <br>
                                            <div class="actions">
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                                    <div class="box-footer clearfix">
                                        <ul class="pagination pagination-sm no-margin pull-right"
                                            url="${base}/action/list"
                                            currentPage="${model.page.pageNo}"
                                            pageCount="${model.page.totalPage}">
                                        </ul>
                                    </div>
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
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>