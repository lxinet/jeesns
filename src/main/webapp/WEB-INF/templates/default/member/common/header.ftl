<div id="wrapper">
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse"
                                class="navbar-toggle collapsed" type="button">
                            <i class="fa fa-reorder"></i>
                        </button>
                        <a href="${base}/" class="navbar-brand"><img src="${base}${SITE_LOGO}" height="50px"/></a>
                    </div>
                    <div class="navbar-collapse collapse" id="navbar">
                        <ul class="nav navbar-nav">
                            <li>
                                <a aria-expanded="false" role="button" href="${base}/"> 首页</a>
                            </li>
                            <li>
                                <a aria-expanded="false" role="button" href="${base}/article/list"> 资讯</a>
                            </li>
                            <li>
                                <a aria-expanded="false" role="button" href="${base}/weibo/list"> ${WEIBO_ALIAS}</a>
                            </li>
                            <li>
                                <a aria-expanded="false" role="button" href="${base}/group/"> ${GROUP_ALIAS}</a>
                            </li>
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                        <#if loginUser == null>
                            <li><a href="${base}/member/login">登录</a></li>
                            <li><a href="${base}/member/register">注册</a></li>
                        <#else>
                            <li class="dropdown">
                                <a aria-expanded="false" role="button" href="${base}/member/" class="dropdown-toggle"
                                   data-toggle="dropdown"> ${loginUser.name} <span class="caret"></span></a>
                                <ul role="menu" class="dropdown-menu">
                                    <li><a href="${base}/member/">个人中心</a></li>
                                    <li><a href="${base}/member/editInfo">设置</a></li>
                                    <#if loginUser.isAdmin == 1>
                                        <li><a href="${managePath}/index" target="_blank">管理</a></li>
                                    </#if>
                                    <li><a href="${base}/member/logout">退出</a></li>
                                </ul>
                            </li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
