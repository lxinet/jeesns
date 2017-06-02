var weibo = {
    favor : function (_this,base) {
        var weiboId = _this.attr("weibo-id");
        $.ajax({
            url: base+"/weibo/favor/"+weiboId,
            type: "get",
            dataType: "json",
            timeout: 5000,
            success:function(res){
                if(res.code < 0){
                    jeesnsDialog.errorTips(res.message);
                }else {
                    if(res.code == 0){
                        _this.html("<i class='fa fa-thumbs-up'></i> "+res.data);
                        _this.removeClass("text-primary");
                        _this.addClass("text-success")
                    }else {
                        _this.html("<i class='fa fa-thumbs-o-up'></i> "+res.data);
                        _this.removeClass("text-success");
                        _this.addClass("text-primary")
                    }
                }
            }
        });
    },
    commentList : function (weiboId,pageNo) {
        $.ajax({
            url : base+"/weibo/commentList/"+weiboId+".json?pageNo="+pageNo,
            type : "get",
            dataType: "json",
            success : function (json) {
                var data = json.data;
                var html = "";
                for(var i=0;i<data.length;i++){
                    html += "<div class=\"comment\">" +
                        "<a href=\""+base+"/u/"+data[i].member.id+"\" class=\"avatar\">" +
                        "<img src=\""+base+data[i].member.avatar+"\" class=\"icon-4x\"></a><div class=\"content\">" +
                        "<div class=\"pull-right text-muted\">"+data[i].createTime+"</div><div>" +
                        "<a href=\""+base+"/u/"+data[i].member.id+"\"><strong>"+data[i].member.name+"</strong></a></div>" +
                        "<div class=\"text\">"+data[i].content+"</div>" +
                        "<div class=\"actions\"><a href=\"##\">回复</a><a href=\"##\">删除</a></div></div></div>";
                }
                pageNo = json.page.pageNo;
                if(json.page.totalPage<=pageNo){
                    $("#moreComment").hide();
                }else {
                    $("#moreComment").show();
                }
                $("#commentList").append(html);
            }
        });
    },
    commentReply : function (name) {
        $('.weibo-comment-textarea').append("@"+name+" ");
        $('.weibo-comment-textarea').focus();
    }
}
$(document).ready(function () {
    //输入前内容
    var inputBeforeContent = "";
    $("#weibo-content").bind('input propertychange focus blur', function() {
        var $this = $(this);
        var _val = $this.val();
        var maxlength = $this.attr("maxlength");
        $("#weibo-words").text(_val.length+"/"+maxlength);
        if (_val.length > maxlength) {
            $this.val(inputBeforeContent);
        }else {
            inputBeforeContent = _val;
        }
    });
});

