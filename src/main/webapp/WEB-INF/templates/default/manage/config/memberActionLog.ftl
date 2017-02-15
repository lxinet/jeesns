<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>会员动态 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
    <script src="${base}/res/plugins/metisMenu/metisMenu.js"></script>
    <script src="${base}/res/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/common/js/manage.js"></script>
    <script src="${base}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>会员日志(${model.page.totalCount})</h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>会员</th>
                                <th style="width: 75%">描述</th>
                                <th>时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list model.data as actionLog>
                            <tr>
                                <td>${actionLog.id}</td>
                                <td>${actionLog.member.name}</td>
                                <td>${actionLog.action.log}：
                                    <br/>
                                    <#if actionLog.type==1>
                                        <a href="${base}/article/detail/${actionLog.foreignId}" target="_blank">${actionLog.remark}</a>
                                    <#elseif actionLog.type==2>
                                        <a href="${base}/weibo/detail/${actionLog.foreignId}" target="_blank">${actionLog.remark}</a>
                                    <#elseif actionLog.type==4>
                                        <a href="${base}/group/topic/${actionLog.foreignId}" target="_blank">${actionLog.remark}</a>
                                    </#if>
                                </td>
                                <td>${actionLog.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${managePath}/config/actionLog/memberActionLog?key=${key}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
