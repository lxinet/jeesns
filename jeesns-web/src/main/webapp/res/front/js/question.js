var question = {
    changeBonusType : function (bonusType) {
        console.log(bonusType)
        if (bonusType == 0){
            $("#bonus-type").html("积分");
        } else {
            $("#bonus-type").html("元金额");
        }
    },
    favor : function (_this,base) {
        var topicId = _this.attr("topic-id");
        $.ajax({
            url: groupPath + "/topic/favor/" + topicId,
            type: "get",
            dataType: "json",
            timeout: 5000,
            success: function (res) {
                if (res.code < 0) {
                    jeesnsDialog.errorTips(res.message);
                } else {
                    if (res.code == 0) {
                        _this.html("<i class='icon-heart'></i> 喜欢 | " + res.data);
                        _this.removeClass("btn-article-unfavor")
                    } else {
                        _this.html("<i class='icon-heart-empty'></i> 喜欢 | " + res.data);
                        _this.addClass("btn-article-unfavor");
                    }
                }
            }
        });
    },
    commentReply: function (id) {
        $('#comment-form-'+id).toggle();
        $('#'+id).focus();
    }
}
