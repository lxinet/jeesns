/**
 * Created by zchuanzhao on 16/9/27.
 */
var reg_rule = {
    'require'    :    /.+/,
    'email'      :    /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
    'url'        :    /^http|https:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
    'currency'   :    /^\d+(\.\d+)?$/,
    'number'     :    /^\d+$/,
    'zip'        :    /^\d{6}$/,
    'integer'    :    /^[-\+]?\d+$/,
    'double'     :    /^[-\+]?\d+(\.\d+)?$/,
    'english'    :    /^[A-Za-z]+$/
};


function getOptions(form){
    var options = {
        dataType : 'json',
        timeout : 20000,
        beforeSubmit : function (){
            jeeLoading();
            form.find('.ajax_btn').attr("disabled","true");
        },
        error:function(){
            jeeTips('请求失败 ！');
        },
        success:function(res){
            if(res.code==1){
                jeeSuccessTips(res.msg)
            }else if(res.code==2){
                jeeSuccessTips(res.msg)
                setTimeout(function(){
                    window.location.href=res.url;
                },2000);
            }else if(res.code==-1){
                jeeErrorTips(res.msg);
            }else if(res.type=='reset'){
                jeeTips(res.msg);
                form[0].reset();
            }else{
                jeeTips(res.msg);
            }
        }
    };
    return options;
}

function checkForm(form){
    if(form.hasClass('loading')) return false;
    var check = true;
    form.find('input,textarea,select,checkbox,radio[dataType]').each(function(){
        var val = $.trim( $(this).val() );
        var type = $(this).attr('dataType');
        if(type != "" && type != undefined){
            var alt = $(this).attr('alt');
            if(!reg_rule[type].test(val)){
                jeeTips(alt);
                $(this).focus();
                check = false;
                return false;
            }
        }
    });
    return check;
}

function submitForm(form){
    if(arguments[0]){//如果传入了form，马上对form进行ajax提交
        var form = typeof(form)=='object' ? $(form) : $('#'+form);
        if(checkForm(form)==false) return false;
        var options = getOptions(form);
        form.ajaxSubmit(options);
    }else{//否则，对标志有class="ajax_form"的表单进行ajax提交的绑定操作
        $('.ajax_form').bind('submit',function(){
            var form = $(this);
            if(checkForm(form)==false) return false;
            var options = getOptions(form);
            form.ajaxSubmit(options);
            return false;
        });
    }
}

function jeeAjax(myurl,mytype,mydata){
    $.ajax({
        url: myurl,             // 要提交到的地址
        type: mytype,           // 提交的方式，GET或POST
        data: mydata,           // 提交的数据
        dataType: "json",       // 这里是返回数据的方式，可以是xml，text,html格式
        timeout: 20000,         // 超时时间
        beforeSend: function(){ // 提交之前
            jeeLoading()
        },
        error: function(){      // 出错
            jeeTips('请求失败 ！');
        },
        success:function(res){  // 成功
            jeeTips(res.msg);
            if(res.type=='refresh'){
                setTimeout(function(){
                    window.location.href=window.location.href;
                },2000);
            }
        }
    });
}

//a链接以ajax方式提交
function jeeLinkAjax(){
    $('a[target="_ajax"]').on('click',function(){
        var url = $(this).attr('href');
        var confirm = $(this).attr('confirm'); //有confirm属性，则弹出确认
        if(confirm){
            if(typeof(art)!='undefined'){
                jeeConfirm(jeeAjax,title);
            }else{
                var check = confirm(title);
                if(check == true) jeeAjax(url,'GET',null);
            }
        }else{
            jeeAjax(url,'GET',null);
        }
        return false;
    });
}


function jeeAlert(msg) {
    layer.closeAll()
    layer.alert(msg);
}

function jeeConfirm(confirmFun,msg) {
    layer.closeAll()
    layer.confirm(msg
        , function(){
            confirmFun();
    }, function(){

    });
}

function jeeLoading() {
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    //loading层
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
}

function jeeTips(msg,type){
    layer.closeAll()
    if(type == "error"){
        layer.msg(msg, {icon: 5});
    }else if(type == "success"){
        layer.msg(msg, {icon: 6});
    }else{
        layer.msg(msg);
    }
}

function jeeErrorTips(msg) {
    layer.closeAll()
    layer.msg(msg, {icon: 5});
}

function jeeSuccessTips(msg) {
    layer.closeAll()
    layer.msg(msg, {icon: 6});
}

function jeeTips(msg){
    layer.closeAll();
    layer.msg(msg);
}

$(document).ready(function(){
    submitForm();
    jeeLinkAjax();
});
