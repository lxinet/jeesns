<div class="col-sm-2 col-xs-12">
    <ul class="list-group">
        <li class="list-group-item"><a href="${basePath}/member/shopCart/list">购物车</a></li>
        <li class="list-group-item"><a href="${basePath}/member/financial/list">财务明细</a></li>
        <li class="list-group-item"><a href="${basePath}/member/scoreDetail/list">积分明细</a></li>
        <#if payExists == true>
            <#if ALIPAY_OPEN == 1>
                <li class="list-group-item"><a href="${basePath}/pay/alipay/recharge">支付宝充值</a></li>
            </#if>
            <#if PAYJS_OPEN == 1>
                <li class="list-group-item"><a href="${basePath}/pay/wxpay/recharge">微信充值</a></li>
            </#if>
        </#if>
        <#if extExists == true>
            <li class="list-group-item"><a href="${basePath}/member/cdkRecharge">卡密充值</a></li>
        </#if>
        <li class="list-group-item"><a href="${basePath}/member/avatar">修改头像</a></li>
        <li class="list-group-item"><a href="${basePath}/member/editInfo">修改信息</a></li>
        <li class="list-group-item"><a href="${basePath}/member/password">修改密码</a></li>
        <li class="list-group-item"><a href="${basePath}/member/deliveryAddress/list">收货地址</a></li>
    </ul>
</div>