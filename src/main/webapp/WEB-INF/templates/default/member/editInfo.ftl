<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改资料 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/plugins/My97DatePicker/WdatePicker.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
<#include "/member/common/editLeft.ftl"/>
    <div class="col-sm-9">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>修改资料</h5>
            </div>
            <div class="ibox-content">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li class="active"><a data-toggle="tab" href="tabs.html#tab-1">基本资料</a></li>
                        <li class=""><a data-toggle="tab" href="tabs.html#tab-2">个人资料</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <form class="form-horizontal m-t jeesns_form" action="${base}/member/editBaseInfo"
                                      method="post">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">登录账号：</label>
                                        <div class="col-sm-8">
                                        ${loginUser.email}
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">昵称：</label>
                                        <div class="col-sm-8">
                                            <input id="name" name="name" class="form-control" type="text"
                                                   data-type="require" value="${loginUser.name!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">性别：</label>
                                        <div class="col-sm-8">
                                            <label><input type="radio" value="保密" name="sex"
                                                          <#if loginUser.sex=='' || loginUser.sex==null || loginUser.sex=='保密'>checked</#if>/>
                                                <i></i> 保密</label>
                                            <label><input type="radio" value="男" name="sex"
                                                          <#if loginUser.sex=='男'>checked</#if>> <i></i>
                                                男</label>
                                            <label><input type="radio" value="女" name="sex"
                                                          <#if loginUser.sex=='女'>checked</#if>> <i></i>
                                                女</label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">个人说明：</label>
                                        <div class="col-sm-8">
                                                    <textarea id="introduce" name="introduce" class="form-control"
                                                              type="text" data-type="require"
                                                              cols="5">${loginUser.introduce!''}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-2">
                                            <input type="submit" class="btn btn-primary block full-width m-b"
                                                   value="修改">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="tab-2" class="tab-pane">
                            <div class="panel-body">
                                <form class="form-horizontal m-t jeesns_form"
                                      action="${base}/member/editOtherInfo" method="post">
                                    <input name="id" class="form-control" type="hidden" dataType=""
                                           value="${loginUser.id}">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">生日：</label>
                                        <div class="col-sm-8">
                                            <input id="birthday" name="birthday" type="text" class="form-control"
                                                   value="${loginUser.birthday}" onClick="WdatePicker()"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">QQ：</label>
                                        <div class="col-sm-8">
                                            <input id="qq" name="qq" class="form-control" type="text"
                                                   dataType="" value="${loginUser.qq!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">微信：</label>
                                        <div class="col-sm-8">
                                            <input id="wechat" name="wechat" class="form-control" type="text"
                                                   dataType="" value="${loginUser.wechat!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">联系电话：</label>
                                        <div class="col-sm-8">
                                            <input id="contactPhone" name="contactPhone" class="form-control"
                                                   type="text" dataType="" value="${loginUser.contactPhone!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">联系邮箱：</label>
                                        <div class="col-sm-8">
                                            <input id="contactEmail" name="contactEmail" class="form-control"
                                                   type="text" dataType="" value="${loginUser.contactEmail!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">个人网站：</label>
                                        <div class="col-sm-8">
                                            <input id="website" name="website" class="form-control" type="text"
                                                   dataType="" value="${loginUser.website!''}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-8 col-sm-offset-2">
                                            <input type="submit"
                                                   class="btn btn-primary block full-width m-b jeesns-submit"
                                                   value="修改">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>
<#include "/member/common/footer.ftl"/>
<script type="text/javascript">
    $('#birthday').datetimepicker();
</script>
</body>
</html>