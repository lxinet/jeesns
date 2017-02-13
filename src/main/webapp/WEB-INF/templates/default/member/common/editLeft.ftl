<div class="col-sm-3">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>个人资料</h5>
        </div>
        <div>
            <div class="ibox-content profile-element text-center">
                <a href="${base}/member/avatar">
                    <img alt="image" class="img-circle" src="${base}${loginUser.avatar}" width="80px" height="80px"/>
                </a>
                <h4>
                    <strong>
                    ${loginUser.name!''}
                        <a href="${base}/member/editInfo"><i class="fa fa-edit"></i></a>
                    </strong>
                </h4>
            </div>
            <div class="ibox-content">
                <ul class="sortable-list connectList agile-list" id="todo">
                    <a href="${base}/member/avatar"><li class="warning-element">修改头像</li></a>
                    <a href="${base}/member/editInfo"><li class="warning-element">修改信息</li></a>
                    <a href="${base}/member/password"><li class="warning-element">修改密码</li></a>
                </ul>
            </div>
        </div>
    </div>
</div>