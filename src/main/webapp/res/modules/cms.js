/**
 * Created by zchuanzhao on 2016/12/21.
 */


var cms = {
    commentList : function (articleId,pageNo) {
        $.ajax({
            url : base+"/article/commentList/"+articleId+".json?pageNo="+pageNo,
            type : "get",
            dataType: "json",
            success : function (json) {
                var data = json.data;
                var html = "";
                for(var i=0;i<data.length;i++){
                    html += "<div class='social-feed-box'><div class='social-avatar'><a href='' class='pull-left'><img src='"+base+data[i].member.avatar+"'>";
                    html += "</a><div class='media-body'><a href=''>"+data[i].member.name+"</a><small class='text-muted'>"+data[i].createTime+"</small></div></div><div class='social-body'><p>"+data[i].content+"</p></div></div>";
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
    }
}
