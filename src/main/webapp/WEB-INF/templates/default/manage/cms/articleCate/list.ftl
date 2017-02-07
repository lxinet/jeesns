<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CMS栏目管理 - ${SITE_NAME} - Powered By JEESNS</title>
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
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>
                        栏目管理
                        <a class="right" href="${managePath}/cms/articleCate/add" target="_jeesnsOpen" title="添加栏目">
                            <i class="fa fa-plus-circle green"></i>
                        </a>
                    </h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>栏目名称</th>
                                <th>序号</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list list as mainMenu>
                            <tr>
                                <td>${mainMenu.name}</td>
                                <td>${mainMenu.sort}</td>
                                <td>
                                    <a href="${managePath}/cms/articleCate/edit/${mainMenu.id}" target="_jeesnsOpen"
                                       title="编辑栏目"><i class="fa fa-edit green"></i></a>
                                    <a target="_jeesnsLink" href="${managePath}/cms/articleCate/delete/${mainMenu.id}"
                                       confirm="确定要删除栏目吗？删除后栏目下的文章将会移到未分类中！">
                                        <i class="fa fa-trash red"></i></a>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
