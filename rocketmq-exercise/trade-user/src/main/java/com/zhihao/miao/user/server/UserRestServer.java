package com.zhihao.miao.user.server;

import com.zhihao.miao.common.constants.TradeEnums;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class UserRestServer {
    public static void main(String[] args) throws Exception {
        Server server=new Server(TradeEnums.RestServerEnum.USER.getServerPort()); //8084
        ServletContextHandler springMvcHandler = new ServletContextHandler();
        springMvcHandler.setContextPath("/"+TradeEnums.RestServerEnum.USER.getContextPath());  //设置上下文
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:spring-web-user.xml");
        springMvcHandler.addEventListener(new ContextLoaderListener(context)); //设置上下文
        springMvcHandler.addServlet(new ServletHolder(new DispatcherServlet(context)),"/*");
        server.setHandler(springMvcHandler);
        server.start();
        server.join();
    }
}

