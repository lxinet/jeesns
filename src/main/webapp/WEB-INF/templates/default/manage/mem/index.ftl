<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>会员模块首页 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/common/js/jeesns.js"></script>
    <script src="${base}/res/common/js/manage.js"></script>
    <script src="${base}/res/common/js/extendPagination.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>会员列表(${model.page.totalCount})</h5>
                    <div class="col-sm-3 pull-right list-search">
                        <form method="post" action="${managePath}/mem/index">
                            <div class="input-group">
                                <input type="text" name="key" placeholder="请输入关键词" class="input-sm form-control">
                                <span class="input-group-btn">
                                        <button type="submit" class="btn btn-sm btn-primary"> <i class="fa fa-search"></i></button>
                                    </span>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th style="width: 10px">#</th>
                                <th>用户名</th>
                                <th>邮箱</th>
                                <th>手机号码</th>
                                <th>登录次数</th>
                                <th>注册时间</th>
                                <th>状态</th>
                                <th width="150px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list model.data as member>
                            <tr>
                                <td>${member.id}</td>
                                <td>${member.name}</td>
                                <td>${member.email}</td>
                                <td>${member.phone}</td>
                                <td>${member.loginCount}</td>
                                <td>${member.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                <td>
                                    <#if member.status=-1>
                                        禁用
                                    <#else>
                                        启用
                                    </#if>
                                </td>
                                <td>
                                    <#if member.status=-1>
                                        <a class="marg-l-5" target="_jeesnsLink"
                                           href="${managePath}/mem/member/isenable/${member.id}" confirm="确定要启用会员吗？">
                                            启用
                                        </a>
                                    <#else>
                                        <a class="marg-l-5" target="_jeesnsLink"
                                           href="${managePath}/mem/member/isenable/${member.id}" confirm="确定要禁用会员吗？">
                                            禁用
                                        </a>
                                    </#if>
                                    <a href="${managePath}/mem/member/changepwd/${member.id}" target="_jeesnsOpen"
                                       title="修改密码" width="400px" height="300px">修改密码</a>
                                    <a class="marg-l-5" target="_jeesnsLink"
                                       href="${managePath}/mem/member/delete/${member.id}" confirm="确定要删除会员吗？">
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
                            url="${managePath}/mem/index?key=${key}"
                            currentPage="${model.page.pageNo}"
                            pageCount="${model.page.totalPage}">
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".pagination").jeesns_page("jeesnsPageForm");
    });
</script>
</body>
</html>
