<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no">
    <title>图库 - ${SITE_NAME} - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/zui.min.css" rel="stylesheet">
    <link href="${basePath}/res/front/css/app.css" rel="stylesheet">
    <script>
        var basePath = "${basePath}";
    </script>
    <!--[if lt IE 9]>
    <script src="${basePath}/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/common/js/zui.min.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/jquery.masonry.min.js"></script>
    <script src="${basePath}/res/common/js/jqeasing.js"></script>
    <script src="${basePath}/res/front/js/jeesns.js"></script>
    <script src="${basePath}/res/front/js/picture.js"></script>
</head>

<body class="gray-bg">
<#include "/${frontTemplate}/common/header.ftl"/>
<div class="picture-content">
    <div class="waterfull clearfloat" id="waterfull">
        <ul id="pictures">
            <#list model.data as picture>
                <li class="item">
                    <a href="${basePath}/picture/detail/${picture.pictureId}" class="picture" target="_jeesnsOpen" title="" height="90%" width="90%">
                        <img src="${basePath}${picture.smallPath}">
                    </a>
                    <p class="description">${picture.description}</p>
                    <div class="qianm clearfloat">
                        <span class="sp1">
                            <a href="${basePath}/u/${picture.member.id}" target="_blank">${picture.member.name}</a>
                            <b>
                            <#if picture.isFavor==0>
                            <a class="text-primary picture-favor" data-id="${picture.pictureId}"><i
                                    class="icon icon-thumbs-o-up"></i> ${picture.favorCount}</a>
                            <#else>
                            <a class="text-success picture-favor" data-id="${picture.pictureId}"><i
                                    class="icon icon-thumbs-up"></i> ${picture.favorCount}</a>
                            </#if>
                            </b>
                        </span>
                        <span class="sp2">${picture.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                    </div>
                </li>
            </#list>
        </ul>
    </div>
    <div id="imloading" class="picture-load">
        加载中.....
    </div>
</div>
<#include "/${frontTemplate}/common/footer.ftl"/>
<script type="text/javascript">
    $(function(){
        picture.waterfull();
    })
</script>
</body>
</html>