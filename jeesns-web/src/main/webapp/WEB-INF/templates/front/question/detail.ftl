<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${question.title} - 问答 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
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
    <script src="${basePath}/res/front/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/plugins/ckeditor/ckeditor.js"></script>
    <script>
        var basePath = "${basePath}";
        var questionId = ${question.id};
        var uploadServer = "${basePath}/uploadImage";
        $(function () {
            $("#content").click(function () {
                var editor = CKEDITOR.replace('content',{height:'150px'});
                CKEDITOR.on('instanceReady', function (e) {
                    editor.focus();
                })
            })
        });
        function deleteSuccess() {
            window.location.href = "${basePath}/question/";
        }
    </script>
    <script src="${basePath}/res/front/js/question.js"></script>
</head>
<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-8 col-xs-12">
                <article class="article article-detail">
                    <header>
                        <h1 class="text-center">
                        ${question.title}
                        </h1>
                        <dl class="dl-inline">
                            <dt></dt>
                            <dd>${question.createTime?string('yyyy-MM-dd HH:mm')}</dd>
                            <span class="label label-danger"><i class="icon-eye-open"></i> ${question.viewCount}</span>
                            <dt></dt>
                            <dd class="pull-right">
                            <#if loginUser?? && (loginUser.id == question.memberId || loginUser.isAdmin &gt; 0)>
                                <div class="dropdown dropdown-hover">
                                    <button class="btn" type="button" data-toggle="dropdown">操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <#if loginUser.id == question.memberId>
                                            <li><a href="${basePath}/question/edit/${question.id}">编辑</a></li>
                                        </#if>
                                        <li><a href="javascript:void(0)" data-href="${basePath}/question/delete/${question.id}" confirm="确定要删除该问答吗？" target="_jeesnsLink" callback="deleteSuccess">删除</a></li>
                                    </ul>
                                </div>
                            </#if>
                            </dd>
                        </dl>
                    </header>
                    <@ads id=2>
                        <#include "/tp/ad.ftl"/>
                    </@ads>
                    <section class="content">
                        ${question.content}
                    </section>
                </article>
                <@ads id=2>
                    <#include "/tp/ad.ftl"/>
                </@ads>
                <div class="comments panel">
                    <div class="panel-heading">${answerModel.page.totalCount}个回答</div>
                    <header>
                        <div class="reply-form">
                            <form class="form-horizontal jeesns_form" action="${basePath}/question/${question.id}/answer/commit" method="post" onsubmit="ckUpdate();" callback="reload">
                                <div class="form-group">
                                    <textarea name="content" id="content" class="form-control new-comment-text" rows="3" placeholder="我要回答"></textarea>
                                </div>
                                <div class="form-group comment-user">
                                    <input type="submit" value="回答" class="pull-right btn btn-primary mg-t-10 jeesns-submit">
                                </div>
                            </form>
                        </div>
                    </header>
                    <section class="comments-list" id="commentList">
                        <#list answerModel.data as answer>
                            <div class="comment">
                                <a href="${basePath}/u/${answer.member.id}" class="avatar">
                                    <img src="${basePath}${answer.member.avatar}" class="icon-4x"/>
                                </a>
                                <div class="content">
                                    <div class="pull-right text-muted">${answer.createTime?string("yyyy-MM-dd HH:mm")}</div>
                                    <div><a href="${basePath}/u/${answer.member.id}"><strong>${answer.member.name}</strong></a></div>
                                    <div class="text">
                                        ${answer.content}
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </section>
                </div>
            </div>
            <div class="col-sm-4 col-xs-12">
                <div class="panel">
                    <div class="panel-body weibo-author">
                        <div class="avatar">
                            <a href="${basePath}/u/${question.member.id}" target="_blank">
                                <img alt="image" class="img-circle mg-l-30" src="${basePath}${question.member.avatar}"/></a>
                        </div>
                        <div class="name">
                            <a href="${basePath}/u/${question.member.id}"
                               target="_blank">${question.member.name}</a>
                            <p><span class="label label-danger">${question.member.memberLevel.name}</span></p>
                        </div>
                        <div class="info">
                            <p>
                                <a href="${basePath}/u/${question.member.id}/home/follows">${question.member.follows}
                                    关注</a> /
                                <a href="${basePath}/u/${question.member.id}/home/fans">${question.member.fans}
                                    粉丝</a>
                            </p>
                            <p>
                            ${question.member.introduce}
                            </p>
                        </div>
                    </div>
                </div>
                <@ads id=1>
                    <#include "/tp/ad.ftl"/>
                </@ads>
            </div>
        </div>
    </div>

</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script>
    $(document).ready(function () {
        var pageNo = 1;
        $(".topic-favor").click(function () {
            group.favor($(this), "${basePath}")
        });
    });
</script>
</body>
</html>