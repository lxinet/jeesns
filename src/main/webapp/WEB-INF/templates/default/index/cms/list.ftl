<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><#if articleCate??>${articleCate.name}<#else>文章列表</#if> - ${SITE_NAME} - Powered By JEESNS</title>
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
            <div class="col-sm-12">
                <div class="ibox ibox-content">
                    <div class="row mg-t-10 mg-b-20">
                        <div class="col-md-11">
                        <#list cateList as cate>
                            <a href="${base}/article/list?cateid=${cate.id}" class="cate-btn">${cate.name}</a>
                        </#list>
                        </div>
                    </div>
                    <div class="row m-b-sm m-t-sm">
                        <div class="col-md-11">
                            <form action="${base}/article/list" method="get">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入关键词" class="input-sm form-control" name="key">
                                    <span class="input-group-btn"><button type="submit" class="btn btn-sm btn-primary"> 搜索</button></span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <h3><strong><#if articleCate??>${articleCate.name}<#else>文章列表</#if>&nbsp;&nbsp;
                    <#if CMS_POST==1><a class="marg-l-5" href="${base}/article/add" title="发布文章">
                        <i class="fa fa-plus-circle green"></i>
                    </a></#if></strong></h3>
                <#list model.data as article>
                    <div class="ibox-content col-sm-12 bottom-line">
                        <div class="col-sm-10">
                            <#if article.thumbnail??>
                                <div class="small m-b-xs">
                                    <span class="text-muted">&nbsp;</span>
                                </div>
                            </#if>
                            <h3>
                                <a href="${base}/article/detail/${article.id}" class="btn-link">
                                ${article.title}
                                </a>
                            </h3>

                            <div class="small m-b-xs">
                                <span class="text-muted"><i
                                        class="fa fa-clock-o"></i> ${article.createTime?string('yyyy-MM-dd HH:mm')}&nbsp;&nbsp;
                                <i class="fa fa-heart"> </i> ${article.favor}</span>
                                <i class="fa fa-eye"> </i> ${article.viewCount} 浏览</span>
                            </div>
                        </div>
                        <#if article.thumbnail??>
                            <div class="col-sm-2">
                                <div class="list-thumbnail float-right">
                                    <a href="${base}/article/detail/${article.id}" class="btn-link">
                                        <img alt="${article.title}" class="m-t-xs img-responsive list-thumbnail"
                                             src="${article.thumbnail}">
                                    </a>
                                </div>
                            </div>
                        </#if>
                    </div>
                </#list>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${base}/article/list?key=${key}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
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