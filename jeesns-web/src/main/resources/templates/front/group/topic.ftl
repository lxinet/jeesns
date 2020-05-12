<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${groupTopic.title} - ${groupTopic.group.name} - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${basePath}/res/front/js/jeesns.js?v1.4"></script>
    <script>
        var base = "${basePath}";
        var groupPath = "${groupPath}";
        var groupTopicId = ${groupTopic.id};

        function deleteSuccess() {
            window.location.href = "${groupPath}/detail/${groupTopic.group.id}";
        }
    </script>
    <script src="${basePath}/res/front/js/group.js"></script>
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
                        ${groupTopic.title}
                        <#if groupTopic.isTop==1>
                            <span class="label label-badge label-primary">置顶</span>
                        <#elseif groupTopic.isTop==2>
                            <span class="label label-badge label-success">超级置顶</span>
                        </#if>
                        <#if groupTopic.isEssence==1>
                            <span class="label label-badge label-danger">精华</span>
                        </#if>
                        </h1>
                        <dl class="dl-inline">
                            <dt></dt>
                            <dd>${groupTopic.createTime?string('yyyy-MM-dd HH:mm')}</dd>
                            <span class="label label-danger"><i class="icon-eye-open"></i> ${groupTopic.viewCount}</span>
                            <dt></dt>
                            <dd class="pull-right">
                            <#if loginUser?? && (loginUser.id == groupTopic.memberId || loginUser.isAdmin &gt; 0)>
                                <div class="dropdown dropdown-hover">
                                    <button class="btn" type="button" data-toggle="dropdown">操作 <span class="caret"></span></button>
                                    <ul class="dropdown-menu">

                                        <#if loginUser?? && loginUser.isAdmin &gt; 0>
                                            <#if groupTopic.isTop = 0>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=1" target="_jeesnsLink" callback="reload">普通置顶</a></li>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=2" target="_jeesnsLink" callback="reload">超级置顶</a></li>
                                            <#elseif groupTopic.isTop = 1>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=0" target="_jeesnsLink" callback="reload">取消普通置顶</a></li>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=2" target="_jeesnsLink" callback="reload">超级置顶</a></li>
                                            <#elseif groupTopic.isTop = 2>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=0" target="_jeesnsLink" callback="reload">取消超级置顶</a></li>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/top/${groupTopic.id}?top=1" target="_jeesnsLink" callback="reload">普通置顶</a></li>
                                            </#if>
                                            <#if groupTopic.isEssence = 0>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/essence/${groupTopic.id}?essence=1" target="_jeesnsLink" callback="reload">精华</a></li>
                                            <#elseif groupTopic.isEssence = 1>
                                                <li><a href="javascript:void(0)" data-href="${groupPath}/topic/essence/${groupTopic.id}?essence=0" target="_jeesnsLink" callback="reload">取消精华</a></li>
                                            </#if>
                                        </#if>
                                        <#if loginUser.id == groupTopic.memberId>
                                            <li><a href="${groupPath}/topicEdit/${groupTopic.id}">编辑</a></li>
                                        </#if>
                                        <li><a href="javascript:void(0)" data-href="${groupPath}/delete/${groupTopic.id}" confirm="确定要删除帖子吗？" target="_jeesnsLink" callback="deleteSuccess">删除</a></li>
                                    </ul>
                                </div>
                            </#if>
                            </dd>
                        </dl>
                    </header>
                    <@ads id=2>
                        <#include "/tp/ad.ftl"/>
                    </@ads>
                    <#if groupTopic.group.followPay == 0 || isManager == 1 || isfollow == true>
                        <section class="content">
                            ${groupTopic.content}
                        </section>
                        <div class="text-center">
                        <#if groupTopic.isFavor == 0>
                            <a class="btn btn-danger btn-article-favor btn-article-unfavor topic-favor" href="javascript:void(0)" topic-id="${groupTopic.id}">
                                <i class="icon-heart-empty"></i> 喜欢 | ${groupTopic.favor}
                            </a>
                        <#else>
                            <a class="btn btn-danger btn-article-favor topic-favor" href="javascript:void(0)" topic-id="${groupTopic.id}">
                                <i class="icon-heart"></i> 喜欢 | ${groupTopic.favor}
                            </a>
                        </#if>
                        </div>
                    <#else>
                     <section class="content">
                        <div class="alert alert-danger">
                            <p>该群组为<span class="label label-info">付费</span>群组，需要先加入群组才可以查看该帖子</p>
                            <p>
                                <#if loginUser == null>
                                    我还没有登录，我要先<a href="/member/login" target="_blank">登录</a>
                                    <#else>
                                    这篇帖子很重要，我要
                                        <a title="加入" href="javascript:void(0)" data-href="${groupPath}/follow/${groupTopic.group.id}"
                                           target="_jeesnsLink" callback="reload"
                                           confirm="加入该群组收费${groupTopic.group.payMoney}元，加入后自动扣除该费用，确定要加入吗？">加入</a>
                                        群组
                                </#if>
                            </p>
                        </div>
                     </section>
                    </#if>
                </article>
                <@ads id=2>
                    <#include "/tp/ad.ftl"/>
                </@ads>
               <#if groupTopic.group.followPay == 0 || isManager == 1 || isfollow == true>
                <div class="comments panel">
                    <div class="panel-heading">帖子评论</div>
                    <header>
                        <div class="reply-form">
                            <form class="form-horizontal jeesns_form"
                                  action="${groupPath}/comment/${groupTopic.id}" method="post" callback="reload">
                                <div class="form-group">
                                    <textarea name="content" class="form-control new-comment-text" rows="2"  data-type="require" placeholder="评论内容"></textarea>
                                </div>
                                <div class="form-group comment-user">
                                    <input type="submit" value="评论" class="pull-right btn btn-primary mg-t-10 jeesns-submit">
                                </div>
                            </form>
                        </div>
                    </header>
                    <section class="comments-list" id="commentList">

                    </section>
                    <button class="btn btn-primary btn-block m" id="moreComment" style="display: none"><i
                            class="fa fa-arrow-down"></i> 加载更多
                    </button>
                </div>
            </#if>
            </div>
            <div class="col-sm-4 col-xs-12">
                <a href="${groupPath}/post/${groupTopic.group.id}" class="btn btn-block btn-lg btn-info">发帖</a>
                <div class="group white-bg">
                    <div class="group-logo">
                        <img alt="${groupTopic.group.name}" src="${basePath}${groupTopic.group.logo}" width="80px"
                             height="80px"/>
                    </div>
                    <div class="group-detail">
                        <p>
                            <span>
                                <strong>
                                <a href="${groupPath}/detail/${groupTopic.group.id}">
                                    ${groupTopic.group.name}
                                </a>
                                </strong>
                            </span>
                            <span class="text-right">
                                <#if isfollow == true>
                                    <a title="退出" href="javascript:void(0)" data-href="${groupPath}/nofollow/${groupTopic.group.id}"
                                       target="_jeesnsLink" callback="reload"
                                        <#if groupTopic.group.followPay == 1>confirm="该群组是收费群，退出后重新加入需要重新付费，确定要退出吗？"</#if>>
                                        <i class="icon-minus"></i> 退出
                                    </a>
                                <#else>
                                    <a title="加入" href="javascript:void(0)" data-href="${groupPath}/follow/${groupTopic.group.id}"
                                       target="_jeesnsLink" callback="reload"
                                       <#if groupTopic.group.followPay == 1>confirm="加入该群组收费${groupTopic.group.payMoney}元，加入后自动扣除该费用，确定要加入吗？"</#if>>
                                        <i class="icon-plus"></i> 加入</a>
                                </#if>
                                <#if loginUser?? && loginUser.id == groupTopic.group.creator>
                                    . <a href="${groupPath}/edit/${groupTopic.group.id}">编辑</a>
                                </#if>
                                <#if isManager == 1>
                                    . <a href="${groupPath}/auditList/${groupTopic.group.id}">审核帖子</a>
                                </#if>
                            </span>
                        </p>
                    </div>
                    <div class="group-introduce">
                    ${groupTopic.group.introduce!''}
                    </div>
                </div>
                <div class="panel">
                    <div class="panel-body weibo-author">
                        <div class="avatar">
                            <a href="${basePath}/u/${groupTopic.member.id}" target="_blank">
                                <img alt="image" class="img-circle mg-l-30" src="${basePath}${groupTopic.member.avatar}"/></a>
                        </div>
                        <div class="name">
                            <a href="${basePath}/u/${groupTopic.member.id}"
                               target="_blank">${groupTopic.member.name}</a>
                            <p><span class="label label-danger">${groupTopic.member.memberLevel.name}</span></p>
                        </div>
                        <div class="info">
                            <p>
                                <a href="${basePath}/u/${groupTopic.member.id}/home/follows">${groupTopic.member.follows}
                                    关注</a> /
                                <a href="${basePath}/u/${groupTopic.member.id}/home/fans">${groupTopic.member.fans}
                                    粉丝</a>
                            </p>
                            <p>
                            ${groupTopic.member.introduce}
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
        <#if isManager == 1 || isfollow == true>
        group.commentList(groupTopicId, pageNo);
        $("#moreComment").click(function () {
            pageNo++;
            group.commentList(articleId, pageNo);
        });
        $(".topic-favor").click(function () {
            group.favor($(this), "${basePath}")
        });
        </#if>
    });
</script>
</body>
</html>