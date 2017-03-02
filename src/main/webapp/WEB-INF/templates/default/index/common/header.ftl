<div id="wrapper">
    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation">
                <div class="container">
                    <div class="navBox">
                        <a href="/"><img class="logoImg" src="${base}${SITE_LOGO}" height="60px"></a>
                        <div class="nav">
                            <ul>
                                <li class="list"><a class="aList" href="${base}/" id="home-nav">首页</a></li>
                                <li class="list"><a class="aList" href="${base}/article/list" id="project-nav">资讯</a></li>
                                <li class="list"><a class="aList" href="${base}/weibo/list" id="article-nav">${WEIBO_ALIAS}</a></li>
                                <li class="list"><a class="aList" href="${base}/group/" id="account-nav">${GROUP_ALIAS}</a></li>
                            </ul>
                        </div>

                        <div class="navright">
                            <div class="header-action-btn">
                            <#if loginUser == null>
                                <a class="header-action-link" href="${base}/member/login">登录</a> |
                                <a class="header-action-link" href="${base}/member/register">注册</a>
                            <#else>
                                <div class="btn-group nav-username">
                                    <img src="${base}${loginUser.avatar}" class="img-circle" width="25px" height="25px" style="margin-top: 1px;margin-right:5px;"/>
                                    <a class="header-action-link" href="javascript:void(0)">${loginUser.name}</a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${base}/member/">个人中心</a>
                                        </li>
                                        <li><a href="${base}/member/editInfo">设置</a>
                                        </li>
                                        <li><a href="${managePath}/index" target="_blank">管理</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="${base}/member/logout">退出</a>
                                        </li>
                                    </ul>
                                    <script>
                                        $(function () {
                                            $(".nav-username").hover(function(){
                                                $(this).addClass("open");
                                            },function(){
                                                $(this).removeClass("open");
                                            });
                                        })
                                    </script>
                                </div>
                            </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
