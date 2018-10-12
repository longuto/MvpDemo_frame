package com.longuto.androidtemplet.model.greendao;

/**
 * Author by yltang3,
 * Date on 2018/8/31.
 * PS: 入参日志格式的实体类
 */
public class ServerLogInfo {

    public ServerLogInfo() {
    }

    public ServerLogInfo(String host, String webRoot, String method, String parameter, String time) {
        this.host = host;
        this.webRoot = webRoot;
        this.method = method;
        this.parameter = parameter;
        this.time = time;
    }

    private String host;        // 主机地址
    private String webRoot;     // web工程
    private String method;      // 方法名
    private String parameter;   // 参数
    private String time;        // 时间

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ServerLogInfo{" +
                "host='" + host + '\'' +
                ", webRoot='" + webRoot + '\'' +
                ", method='" + method + '\'' +
                ", parameter='" + parameter + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
