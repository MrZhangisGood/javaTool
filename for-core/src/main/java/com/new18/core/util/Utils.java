package com.new18.core.util;


import javax.servlet.http.HttpServletRequest;

public class Utils {

    private static String ssoLogin = PropsUtil.getProp("server.ssologin");

    public static String getServer(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    public static String getSsoLogin() {
        return ssoLogin;
    }
}
