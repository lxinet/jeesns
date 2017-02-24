<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改头像 - ${SITE_NAME} - Powered By JEESNS</title>
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
    <script src="${base}/res/plugins/metisMenu/metisMenu.js"></script>
    <script src="${base}/res/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${base}/res/plugins/layer/layer.js"></script>
    <script src="${base}/res/common/js/manage.js"></script>
    <script src="${base}/res/plugins/fullAvatarEditor/scripts/fullswfobject.js"></script>
    <script src="${base}/res/plugins/fullAvatarEditor/scripts/fullAvatar.js"></script>
</head>

<body class="gray-bg">
<#include "/member/common/header.ftl"/>
<div class="wrapper wrapper-content">
<#include "/member/common/editLeft.ftl"/>
    <div class="col-sm-9">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>修改头像</h5>
            </div>
            <div class="ibox-content">
                <div style="width:632px;text-align:center">
                    <div>
                        <p id="swfContainer">
                            本组件需要安装Flash Player后才可使用，请从<a href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
                        </p>
                    </div>
                    <button type="button" id="upload" style="display:none;margin-top:8px;">
                        swf外定义的上传按钮，点击可执行上传保存操作
                    </button>
                </div>
                <script type="text/javascript">
                    swfobject.addDomLoadEvent(function () {
                        var swf = new fullAvatarEditor("${base}/res/plugins/fullAvatarEditor/fullAvatarEditor.swf", "${base}/res/plugins/fullAvatarEditor/expressInstall.swf", "swfContainer", {
                                    id: 'swf',
                                    upload_url: '${base}/member/uploadAvatar',	//上传接口
                                    method: 'post',	//传递到上传接口中的查询参数的提交方式。更改该值时，请注意更改上传接口中的查询参数的接收方式
                                    src_upload: 0,		//是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
                                    avatar_box_border_width: 0,
                                    avatar_sizes: '150*150',
                                    avatar_sizes_desc: '150*150像素'
                                }, function (msg) {
                                    console.log(msg);
                                    switch (msg.code) {
                                        case 1 :
                                            break;
                                        case 2 :
                                            document.getElementById("upload").style.display = "inline";
                                            break;
                                        case 3 :
                                            if (msg.type == 0) {

                                            }
                                            else if (msg.type == 1) {
                                                alert("摄像头已准备就绪但用户未允许使用！");
                                            }
                                            else {
                                                alert("摄像头被占用！");
                                            }
                                            break;
                                        case 5 :
                                            setTimeout(function () {
                                                window.location.href = window.location.href;
                                            }, 2000);
                                            break;
                                    }
                                }
                        );
                        document.getElementById("upload").onclick = function () {
                            swf.call("upload");
                        };
                    });
                </script>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>
<#include "/member/common/footer.ftl"/>
</body>
</html>