<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${groupTopic.title} - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script>
        var base = "${base}";
        var groupTopicId = ${groupTopic.id};
    </script>
    <script src="${base}/res/modules/group.js"></script>
    <script src="${base}/res/modules/archive.js"></script>
</head>

<body class="gray-bg">
<#include "/index/common/header.ftl"/>

<div class="wrapper wrapper-content article">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="text-center article-title">
                            <h2>${groupTopic.title}</h2>
                            <div >
                                <p>
                                    <i class="fa fa-user"> </i> <a href="${base}/u/${groupTopic.member.id}">${groupTopic.member.name}</a>&nbsp;&nbsp;
                                    <i class="fa fa-clock-o"> </i> ${groupTopic.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                    <i class="fa fa-eye"> </i> ${groupTopic.viewCount} 浏览
                                </p>
                            </div>

                        </div>
                        <div class="content"><p>${groupTopic.content}</p></div>
                        <#if groupTopic.isFavor == 0>
                            <a class="btn btn-danger btn-rounded btn-outline archive-favor" href="javascript:void(0)" archive-id="${groupTopic.archiveId}">
                                <i class="fa fa-heart-o"></i> 喜欢 ${groupTopic.favor}
                            </a>
                        <#else>
                            <a class="btn btn-danger btn-rounded archive-favor" href="javascript:void(0)" archive-id="${groupTopic.archiveId}">
                                <i class="fa fa-heart"></i> 喜欢 ${groupTopic.favor}
                            </a>
                        </#if>
                        <div class="ibox-tools">
                        <#if loginUser?? && ((loginUser.isAdmin == 1) || (loginUser.id == groupTopic.memberId) || isPermission==1)>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                <i class="fa fa-wrench"></i>
                            </a>
                        </#if>
                            <ul class="dropdown-menu dropdown-user">
                            <#if loginUser?? && loginUser.isAdmin == 1>
                                <#if groupTopic.isTop = 0>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=1" target="_jeesnsLink">普通置顶</a></li>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=2" target="_jeesnsLink">超级置顶</a></li>
                                <#elseif groupTopic.isTop = 1>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=0" target="_jeesnsLink">取消置顶</a></li>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=2" target="_jeesnsLink">超级置顶</a></li>
                                <#elseif groupTopic.isTop = 2>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=0" target="_jeesnsLink">取消置顶</a></li>
                                    <li><a href="${base}/group/topic/top/${groupTopic.id}?top=1" target="_jeesnsLink">普通置顶</a></li>
                                </#if>
                                <#if groupTopic.isEssence = 0>
                                    <li><a href="${base}/group/topic/essence/${groupTopic.id}?essence=1" target="_jeesnsLink">精华</a></li>
                                <#elseif groupTopic.isEssence = 1>
                                    <li><a href="${base}/group/topic/essence/${groupTopic.id}?essence=0" target="_jeesnsLink">取消精华</a></li>
                                </#if>
                            </#if>

                            <#if loginUser?? && loginUser.id == groupTopic.memberId>
                                <li><a href="${base}/group/topicEdit/${groupTopic.id}">编辑</a></li>
                            </#if>
                            <#if isPermission==1>
                                <li><a href="${base}/group/delete/${groupTopic.id}" confirm="确定要删除帖子吗？" target="_jeesnsLink">删除</a></li>
                            </#if>
                            </ul>
                        </div>
                    </div>
                    <div class="ibox-content" id="comment">
                        <h3>评论</h3>
                        <hr>
                        <div class="row">
                            <div class="col-lg-12">
                                <form class="form-horizontal m-t jeesns_form" action="${base}/group/comment/${groupTopic.id}" method="post">
                                    <textarea rows="5" name="content" style="width:100%;" class="form-control" data-type="require" alt="评论内容"></textarea>
                                    <input type="submit" value="评论" class="pull-right btn btn-primary mg-t-10 jeesns-submit">
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 mg-t-10" id="commentList">

                            </div>
                            <div class="col-lg-12 mg-t-10">
                                <button class="btn btn-primary btn-block m" id="moreComment" style="display: none"><i class="fa fa-arrow-down"></i> 加载更多</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "/index/common/footer.ftl"/>
<script>
    $(document).ready(function () {
        var pageNo = 1;
        group.commentList(groupTopicId,pageNo);
        $("#moreComment").click(function () {
            pageNo ++;
            group.commentList(groupTopicId,pageNo);
        });
        $(".archive-favor").click(function () {
            archive.favor($(this),"${base}")
        });
    });
</script>
</body>
</html>