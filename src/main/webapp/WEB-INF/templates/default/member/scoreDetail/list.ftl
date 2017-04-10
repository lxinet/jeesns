<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>积分明细 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/jquery.form.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/common/js/extendPagination.js"></script>

</head>
<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
<#include "/member/common/editLeft.ftl"/>
    <div class="col-sm-10">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>积分明细</h5>
            </div>
            <div class="ibox-content">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>变动积分</th>
                        <th>备注</th>
                        <th>时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list model.data as scoreDetail>
                    <tr>
                        <td>${scoreDetail.id}</td>
                        <td>${scoreDetail.score}</td>
                        <td>${scoreDetail.remark}</td>
                        <td>${scoreDetail.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right"
                        url="${base}/member/scoreDetail/list"
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
<#include "/member/common/footer.ftl"/>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>
