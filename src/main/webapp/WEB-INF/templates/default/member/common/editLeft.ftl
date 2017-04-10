<div class="member-banner" style="background-image: url(${base}/res/common/images/member_banner.png);">
    <div class="attempts"></div>
    <div class="container">
        <div class="container content">
            <div class="left">
                <div class="avatar">
                    <img src="${base}${loginUser.avatar}" class="img-circle" width="80px" height="80px"/>
                </div>
                <div class="info">
                    <div class="name">
                    ${loginUser.name}
                    <#if loginUser.sex=='女'>
                        <span class="sex"><i class="fa fa-venus"></i></span>
                    <#elseif loginUser.sex=='男'>
                        <span class="sex"><i class="fa fa-mars"></i></span>
                    <#else>
                        <span class="sex"><i class="fa fa-intersex"></i></span>
                    </#if>
                    </div>
                    <p>${loginUser.website}</p>
                    <p>${loginUser.introduce}</p>
                    <p class="operator">
                        <a class="btn btn-info btn-outline member-follows" href="${base}/member/">
                            <i class="fa fa-home"></i> 个人中心
                        </a>
                    </p>
                </div>
            </div>
            <div class="right">
                <div class="follows">
                    <span>关注</span>
                    <a href="${base}/u/${loginUser.id}/home/follows">${loginUser.follows}</a>
                </div>
                <div class="fans">
                    <span>粉丝</span>
                    <a href="${base}/u/${loginUser.id}/home/fans">${loginUser.fans}</a>
                </div>
                <div class="follows">
                    <span>积分</span>
                    <a href="${base}/member/scoreDetail/list">${loginUser.score}</a>
                </div>
                <div class="login-info">
                    加入时间:${loginUser.createTime?string('yyyy-MM-dd')}
                    最近登录:<#if loginUser.currLoginTime??>${loginUser.currLoginTime?string('yyyy-MM-dd')}<#else>未登陆过</#if>

                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="ibox">
            <div class="ibox-content float-left">
                <div class="col-sm-2">
                    <div class="float-e-margins">
                        <div class="feed-activity-list">
                            <a href="${base}/member/avatar">
                                <div class="feed-element">
                                    <div class="media-body">
                                        修改头像
                                    </div>
                                </div>
                            </a>
                            <a href="${base}/member/editInfo">
                                <div class="feed-element">
                                    <div class="media-body">
                                        修改信息
                                    </div>
                                </div>
                            </a>
                            <a href="${base}/member/password">
                                <div class="feed-element">
                                    <div class="media-body">
                                        修改密码
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>