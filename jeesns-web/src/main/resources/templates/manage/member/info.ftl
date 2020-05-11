<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会员详情 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${basePath}/res/plugins/iCheck/all.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src=${basePath}"/res/common/js/html5shiv.js"></script>
    <script src=${basePath}"/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/res/manage/js/app.js"></script>
    <script src="${basePath}/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/res/common/js/jeesns.js?v1.4"></script>
    <script src="${basePath}/res/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="${basePath}/res/plugins/iCheck/icheck.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content form-horizontal">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-8">
                        <img src="${basePath}${member.avatar}" width="80px">
                    </div>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="name" data-type="require" value="${member.name}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">邮箱</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" name="name" data-type="require" value="${member.email}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号码</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.phone}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">性别</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.sex?default('0')}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">余额</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.money}" disabled>
                        <a href="${managePath}/member/financial/list?memberId=${member.id}" target="_parent">
                            <span class="label label-info">财务明细</span>
                        </a>
                        <a href="javascript:void(0)" data-href="${managePath}/member/increaseMoney/${member.id}" target="_jeesnsOpen"
                           title="加/扣款" width="600px" height="400px">
                            <span class="label label-info">加/扣款</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">积分</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.score}" disabled>
                        <a href="${managePath}/member/scoreDetail/list?memberId=${member.id}" target="_parent">
                            <span class="label label-info">积分明细</span>
                        </a>
                        <a href="javascript:void(0)" data-href="${managePath}/member/increaseScore/${member.id}" target="_jeesnsOpen"
                           title="加/减积分" width="600px" height="400px">
                            <span class="label label-info">加/减积分</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">VIP</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${(member.isVip==0)?string("否","是")}" disabled>
                        <a href="javascript:void(0)" data-href="${managePath}/member/level/${member.id}" target="_jeesnsOpen"
                           title="设置VIP会员" width="400px" height="300px">
                            <span class="label label-info">设置</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">会员等级</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.memberLevel.name}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">关注会员数</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.follows}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">粉丝数</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.fans}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">激活</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${(member.isActive==0)?string("未激活","已激活")}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${(member.status==-1)?string("禁用","正常")}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">QQ</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.qq}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">微信</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.wechat}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">联系手机号</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.contactPhone}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">联系邮箱</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.contactEmail}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">居住地址</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.addprovince} ${member.addcity} ${member.addarea} ${member.address}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">生日</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.birthday?default('')}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">个人网站</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.website}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">个人介绍</label>
                    <div class="col-sm-8">
                        <textarea rows="3" cols="50" disabled>${member.introduce}</textarea>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">注册时间</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.createTime?string("yyyy-MM-dd HH:mm:ss")}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">注册IP</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.regip}" disabled>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">最后登录时间</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.currLoginTime?string("yyyy-MM-dd HH:mm:ss")}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">最后登录IP</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.currLoginIp}" disabled>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label class="col-sm-3 control-label">登录次数</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" data-type="require" value="${member.loginCount}" disabled>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>