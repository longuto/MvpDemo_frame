package com.longuto.androidtemplet.request;

public class NetworkConstant {

    // 正式环境
    public static String HOST = "http://wms.wisder.com/";    // 仓库HOST地址
    public static String ROOT_HOST = "http://47.99.47.214/";    // 用户登录地址

    public static int SUCCESS_CODE = 200;    // 请求的成功码

    // 添加请求头
    public static final String BASEURL = "baseurl"; // 基本的url地址
    public static final String ROOTURL = "rooturl";  // 用户请求的baseurl

    public static final String TRUE = "true"; // 正确
    public static final String FALSE = "false"; // 错误

    public static final String MESSAGE = "message"; // 信息-业务异常返回的提示信息
}
