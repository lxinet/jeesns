<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CMS模块首页 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/plugins/metisMenu/metisMenu.js"></script>
    <script src="${base}/res/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/manage.js"></script>
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-md-2">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>文章栏目</h5>
                </div>
                <div class="ibox-content">
                    <ul class="folder-list">
                        <li <#if cateid=="">class="active"</#if>>
                            <a href="${managePath}/cms/index"><i class="fa fa-folder-open"></i>全部</a>
                        </li>
                    <#list cateList as mainMenu>
                        <li <#if cateid==mainMenu.id>class="active"</#if>>
                            <a href="${managePath}/cms/index?cateid=${mainMenu.id}">
                                <i class="fa fa-folder-open"></i>
                            ${mainMenu.name}
                            </a>
                        </li>
                    </#list>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-10">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>
                        文章列表(${model.page.totalCount})
                        <a class="marg-l-5" href="${managePath}/cms/article/add" target="_jeesnsOpen"
                           title="添加文章" width="1000px" height="680px">
                            <i class="fa fa-plus-circle green"></i>
                        </a>
                        <div class="col-sm-3 pull-right list-search">
                            <form method="post" action="${managePath}/cms/index">
                                <div class="input-group">
                                    <input type="text" name="key" placeholder="请输入关键词" class="input-sm form-control">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"> <i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>标题</th>
                                <th>所在栏目</th>
                                <th>创建时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list model.data as article>
                            <tr>
                                <td>${article.id}</td>
                                <td>${article.title}</td>
                                <td>${article.articleCate.name}</td>
                                <td>${article.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td>
                                    <#if article.status==0>
                                        <a class="marg-l-5" target="_jeesnsLink" href="${managePath}/cms/article/audit/${article.id}">未审核</a>
                                    <#else>
                                        <a class="marg-l-5" target="_jeesnsLink" href="${managePath}/cms/article/audit/${article.id}">已审核</a>
                                    </#if>
                                </td>
                                <td>
                                    <a href="${managePath}/cms/article/edit/${article.id}" target="_jeesnsOpen"
                                       title="编辑文章" width="1000px" height="680px">
                                        <i class="fa fa-edit green"></i>
                                    </a>
                                    <a class="marg-l-5" target="_jeesnsLink"
                                       href="${managePath}/cms/article/delete/${article.id}" confirm="确定要删除文章吗？">
                                        <i class="fa fa-trash red"></i>
                                    </a>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="box-footer clearfix">
                        <ul class="pagination pagination-sm no-margin pull-right"
                            url="${managePath}/cms/index?key=${key}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>
