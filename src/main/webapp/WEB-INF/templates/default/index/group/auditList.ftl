<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>审核帖子 - ${group.name} - ${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
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
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>
<div class="wrapper wrapper-content blog">
    <div class="container">
        <div class="row">
            <div class="col-sm-9">
                <ol class="breadcrumb">
                    <li><a href="${base}/">首页</a></li>
                    <li><a href="${base}/group/">群组</a></li>
                    <li><a href="${base}/group/detail/${group.id}">${group.name}</a></li>
                    <li class="active">审核帖子</li>
                </ol>
                <div class="ibox ibox-content">
                <#assign size=0/>
                <#list model.data as topic>
                    <#assign size=size+1>
                    <div class="ibox-content col-sm-12">
                        <div class="col-sm-10">
                            <h3>
                                <a href="${base}/group/topic/${topic.id}" class="btn-link" target="_blank">
                                ${topic.title}
                                </a>
                            </h3>

                            <div class="small m-b-xs">
                                <span class="text-muted"><i
                                        class="fa fa-clock-o"></i> ${topic.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <div class="text-center">
                                <a href="${base}/group/audit/${topic.id}" class="btn-link" target="_jeesnsLink">
                                    审核
                                </a>
                                <a href="${base}/group/delete/${topic.id}" class="btn-link" target="_jeesnsLink" confirm="确定要删除该帖子吗？">
                                    删除
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
                <#if size == 0>
                    <div class="ibox-content">
                        <div class="col-sm-10">
                            帖子已全部审核
                        </div>
                    </div>
                </#if>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${base}/group/auditList/${group.id}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox float-e-margins">
                        <div>
                            <div class="ibox-content profile-element">
                                <img alt="image" class="img-circle mg-l-30" src="${base}${group.logo}" width="150px"/>
                            </div>
                            <div class="ibox-content profile-content">
                                <h3>
                                    <strong>
                                        ${group.name}
                                    </strong>
                                </h3>
                                <h4>
                                    管理员:
                                    <#list managerList as member>
                                        <a href="${base}/u/${member.id}" target="_blank">${member.name}</a> ·
                                    </#list>
                                </h4>
                                <#if loginUser?? && loginUser.id == group.creator>
                                    <a class="btn btn-outline btn-primary" href="${base}/group/edit/${group.id}">编辑</a>
                                </#if>
                                <#if isfollow == true>
                                    <a class="btn btn-outline btn-primary" title="取消关注"
                                       href="${base}/group/nofollow/${group.id}" target="_jeesnsLink">取消关注</a>
                                <#else>
                                    <a class="btn btn-outline btn-primary" title="添加关注"
                                       href="${base}/group/follow/${group.id}" target="_jeesnsLink">添加关注</a>
                                </#if>
                                <a class="btn btn-outline btn-info" href="${base}/group/post/${group.id}">发帖</a>
                                <p>
                                ${group.introduce!''}
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
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>