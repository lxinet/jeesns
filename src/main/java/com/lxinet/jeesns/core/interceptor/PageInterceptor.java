package com.lxinet.jeesns.core.interceptor;

import com.lxinet.jeesns.core.entity.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 * Created by zchuanzhao on 2016/10/14.
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})})
public class PageInterceptor implements Interceptor {

    private static Page page;
    private static boolean isPage = false;

    public static void startPage(Page page){
        PageInterceptor.page = page;
        isPage = true;
    }

    public static Page endPage(){
        isPage = false;

        return page;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaObject.hasGetter("h")) {
            Object object = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(object);
        }
        // 分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            Object object = metaObject.getValue("target");
            metaObject = SystemMetaObject.forObject(object);
        }

//        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        String sqlid = mappedStatement.getId();
//        if(sqlid.matches(".+ByPage$")){
        if(isPage){
            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
            //原始的SQL语句
            String sql = boundSql.getSql();
            String countSql = "select count(*) from (" + sql + ") a";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countPrepareStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(countPrepareStatement);
//            Map<String ,Object> params = (Map<String, Object>) boundSql.getParameterObject();
//            Page page = (Page) params.get("page");
            ResultSet resultSet = countPrepareStatement.executeQuery();
            if(resultSet.next()){
                page.setTotalCount(resultSet.getInt(1));
            }
            //分页查询SQL
            String pageSql = sql + " limit " + page.getStartRow() + ","+ page.getPageSize();
            metaObject.setValue("delegate.boundSql.sql",pageSql);
        }
        return invocation.proceed();
    }



    @Override
    public Object plugin(Object target) {
        if(target instanceof StatementHandler){
            return Plugin.wrap(target,this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
