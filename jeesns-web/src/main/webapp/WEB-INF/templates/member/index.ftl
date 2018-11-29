<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>个人中心 - ${SITE_NAME} - Powered By JEESNS</title>
        <meta name="keywords" content="${SITE_KEYS}"/>
        <meta name="description" content="${SITE_DESCRIPTION}"/>
        <meta name="author" content="JEESNS"/>
        <link rel="shortcut icon" href="favicon.ico">
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
    <div class="member-banner" style="background-image: url(${basePath}/res/common/images/member_banner.png);">
        <div class="attempts"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-xs-12 left">
                    <div class="avatar">
                        <img src="${basePath}${member.avatar}" class="img-circle" width="100%" height="100%"/>
                    </div>
                    <div class="info">
                        <div class="name">
                        ${member.name}
                        <#if member.sex=='女'>
                            <span class="sex"><i class="fa fa-venus"></i></span>
                        <#elseif member.sex=='男'>
                            <span class="sex"><i class="fa fa-mars"></i></span>
                        <#else>
                            <span class="sex"><i class="fa fa-intersex"></i></span>
                        </#if>
                            <span class="label label-danger" style="font-size: 12px;">${member.memberLevel.name}</span>
                            <a class="label label-primary edit" href="${basePath}/member/editInfo">
                                <i class="fa fa-edit"></i> 编辑
                            </a>
                        </div>
                        <p>${member.website}</p>
                        <p>${member.introduce}</p>
                    </div>
                </div>
                <div class="col-sm-6 col-xs-12 right">
                    <div class="follows">
                        <span>关注</span>
                        <a href="${basePath}/u/${member.id}/home/follows">${member.follows}</a>
                    </div>
                    <div class="fans">
                        <span>粉丝</span>
                        <a href="${basePath}/u/${member.id}/home/fans">${member.fans}</a>
                    </div>
                    <div class="follows">
                        <span>积分</span>
                        <a href="${basePath}/member/scoreDetail/list">${member.score}</a>
                    </div>
                    <div class="follows">
                        <span>余额</span>
                        <a href="${basePath}/member/financial/list">${member.money}</a>
                    </div>
                    <div class="login-info">
                        加入时间:${member.createTime?string('yyyy-MM-dd')}
                        最近登录:<#if member.currLoginTime??>${member.currLoginTime?string('yyyy-MM-dd')}<#else>未登陆过</#if>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row m-t-10">
            <#include "/member/common/menu.ftl"/>
            <div class="col-sm-10 col-xs-12">
                <div class="list list-condensed white-bg p-t-5">
                    <header>
                        <h3><i class="icon-list-ul"></i> 动态</h3>
                    </header>
                    <div class="items items-hover">
                        <#list actionLogModel.data as actionLog>

                        <div class="item">
                            <div class="item-heading">
                                <div class="pull-right"><span class="text-muted">${actionLog.createTime?string('yyyy-MM-dd HH:mm:ss')}</span></div>
                                <h4><a href="${basePath}/u/${actionLog.member.id}"><strong>${actionLog.member.name}</strong></a> ${actionLog.action.log}：</h4>
                            </div>
                            <div class="item-content">
                                <div class="text">
                                     <#if actionLog.type==1>
                                         <a href="${basePath}/article/detail/${actionLog.foreignId}"
                                            target="_blank">${actionLog.remark}</a>
                                     <#elseif actionLog.type==2>
                                                <p>${actionLog.remark}</p>
                                                <a href="${weiboPath}/detail/${actionLog.foreignId}"
                                                   target="_blank">查看</a>
                                     <#elseif actionLog.type==4>
                                                <a href="${groupPath}/topic/${actionLog.foreignId}"
                                                   target="_blank">${actionLog.remark}</a>
                                     </#if>
                                </div>
                            </div>
                        </div>
                        </#list>
                        <ul class="pager pagination pagination-sm no-margin pull-right"
                            url="${basePath}/member/"
                            currentPage="${actionLogModel.page.pageNo}"
                            pageCount="${actionLogModel.page.totalPage}">
                        </ul>
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