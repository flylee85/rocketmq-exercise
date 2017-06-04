package com.zhihao.miao.pay.server;

import com.zhihao.miao.common.constants.TradeEnums;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class PayRestServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(TradeEnums.RestServerEnum.PAY.getServerPort());
        ServletContextHandler spingMvcHandler = new ServletContextHandler();
        spingMvcHandler.setContextPath("/"+TradeEnums.RestServerEnum.PAY.getContextPath());
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations(new String[]{"classpath:spring-web-pay.xml"});
        spingMvcHandler.addEventListener(new ContextLoaderListener(context));
        spingMvcHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");

        server.setHandler(spingMvcHandler);
        server.start();
        server.join();
    }
}
