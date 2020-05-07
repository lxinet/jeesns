<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>回答管理 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src=${basePath}"/res/common/js/html5shiv.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/common/js/extendPagination.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="box box-primary">
                <div class="box-body">
                    <#if bestAnswer??>
                        <div class="item alert alert-info">
                            <p class="message">
                                <a href="${basePath}/u/${bestAnswer.member.id}" class="label label-info" target="_blank">
                                    ${bestAnswer.member.name}
                                </a>
                                <span class="text-muted pull-right">
                                    <i class="fa fa-clock-o"></i> ${bestAnswer.createTime?string("yyyy-MM-dd HH:mm:ss")}
                                    <a target="_jeesnsLink" href="javascript:void(0)" data-href="${managePath}/question/${question.id}/answer/delete/${bestAnswer.id}"
                                       confirm="确定要删除该回答吗？" callback="reload">
                                        <span class="label label-danger">删除</span>
                                    </a>
                                </span>
                                ${answer.content}
                            <hr>
                            </p>
                        </div>
                    </#if>
                    <#list model.data as answer>
                        <div class="item">
                            <p class="message">
                                <a href="${basePath}/u/${answer.member.id}" class="label label-info" target="_blank">
                                    ${answer.member.name}
                                </a>
                                <span class="text-muted pull-right">
                                    <i class="fa fa-clock-o"></i> ${answer.createTime?string("yyyy-MM-dd HH:mm:ss")}
                                    <a target="_jeesnsLink" href="javascript:void(0)" data-href="${managePath}/question/${question.id}/answer/delete/${answer.id}"
                                       confirm="确定要删除该回答吗？" callback="reload">
                                        <span class="label label-danger">删除</span>
                                    </a>
                                </span>
                                ${answer.content}
                                <hr>
                            </p>
                        </div>
                    </#list>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${managePath}/question/${question.id}/answer/list"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>