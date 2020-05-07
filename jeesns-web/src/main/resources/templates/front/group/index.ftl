<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${GROUP_ALIAS} - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
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

<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-6 col-xs-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        最新帖子
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="col-sm-12 col-xs-12">
                                <div class="article-hot-list">
                                    <ul>
                                        <@group_topic_list cid=0 num=15 day=0; groupTopic>
                                            <#list groupTopicList as groupTopic>
                                                <li>
                                                    <a href="${groupPath}/topic/${groupTopic.id}">
                                                        <#if groupTopic.title?length &gt; 40>
                                                            ${groupTopic.title?substring(0,40)}...
                                                        <#else>
                                                            ${groupTopic.title}
                                                        </#if>
                                                    </a>
                                                    <#if groupTopic.isTop==1>
                                                        <span class="label label-badge label-primary">置顶</span>
                                                    <#elseif groupTopic.isTop==2>
                                                        <span class="label label-badge label-success">超级置顶</span>
                                                    </#if>
                                                    <#if groupTopic.isEssence==1>
                                                        <span class="label label-badge label-danger">精华</span>
                                                    </#if>
                                                </li>
                                            </#list>
                                        </@group_topic_list>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-xs-12">
                <div class="panel group-topic-list no-border">
                    <div class="panel-heading">
                        热门帖子
                    </div>
                    <div class="panel-body">
                        <div class="items">
                            <div class="col-sm-12 col-xs-12">
                                <div class="article-hot-list">
                                    <ul>
                                        <@group_topic_list gid=0 num=15 sort='view_count' day=0; groupTopic>
                                            <#list groupTopicList as groupTopic>
                                                     <li>
                                                         <a href="${groupPath}/topic/${groupTopic.id}">
                                                            <#if groupTopic.title?length &gt; 40>
                                                                ${groupTopic.title?substring(0,40)}...
                                                            <#else>
                                                                ${groupTopic.title}
                                                            </#if>
                                                        </a>
                                                         <#if groupTopic.isTop==1>
                                                            <span class="label label-badge label-primary">置顶</span>
                                                         <#elseif groupTopic.isTop==2>
                                                            <span class="label label-badge label-success">超级置顶</span>
                                                         </#if>
                                                         <#if groupTopic.isEssence==1>
                                                            <span class="label label-badge label-danger">精华</span>
                                                         </#if>
                                                     </li>
                                            </#list>
                                        </@group_topic_list>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-xs-12">
            <@group_type_list>
                <#list groupTypeList as groupType>
            <div class="row white-bg group-list">
                <div class="panel-heading" style="margin-bottom: 30px">
                    ${groupType.name}
                    <#if groupType_index == 0>
                        <span class="pull-right">
                            <a class="btn btn-primary right-btn m-t-n4" href="${groupPath}/apply">申请群组</a>
                        </span>
                    </#if>
                </div>
                <#list list as group>
                <#if group.typeId == groupType.id>
                <div class="col-sm-3 col-xs-12">
                    <div class="group-detail">
                        <div class="group-logo">
                            <a href="${groupPath}/detail/${group.id}">
                                <img class="img-rounded" src="${basePath}${group.logo}" width="100px" height="100px">
                            </a>
                        </div>
                        <div class="group-info">
                            <h4><strong><a href="${groupPath}/detail/${group.id}">
                                ${group.name} ${(group.followPay == 0)?string('','<span class="label label-danger">付费</span>')}
                            </a></strong></h4>
                            <p class="text-muted" title="${group.introduce}">
                                <#if group.introduce?length &gt; 50>
                                    ${group.introduce?substring(0,50)}...
                                <#else>
                                    ${group.introduce}
                                </#if>
                            </p>
                            <small class="text-muted">${group.topicCount}篇文章 · ${group.fansCount}人加入</small>
                        </div>
                    </div>
                </div>
                </#if>
                </#list>
            </div>
            </#list>
            </@group_type_list>
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