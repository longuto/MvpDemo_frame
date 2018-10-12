package com.longuto.androidtemplet.model.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Author by yltang3,
 * Date on 2018/8/30.
 * PS: 日志收集，包括崩溃日志和网络日志
 */
@Entity
public class LogcollectionInfo {

    public static final int TYPE_NET_INPUT = 0x1001;  // 网络日志收集入参
    public static final int TYPE_NET_OUTPUT = 0x1002;  // 网络日志收集出参
    public static final int TYPE_CRASH = 0x1003;    // 崩溃日志收集

    @Generated(hash = 773241069)
    public LogcollectionInfo() {
    }

    @Generated(hash = 661266869)
    public LogcollectionInfo(Long id, int type, long eventnumber, String deviceinfo, String content, String time) {
        this.id = id;
        this.type = type;
        this.eventnumber = eventnumber;
        this.deviceinfo = deviceinfo;
        this.content = content;
        this.time = time;
    }

    @Id(autoincrement = true)
    private Long id;    // 自增id

    private int type;   // 日志类型
    private long eventnumber;   // 事件编号，用于入参和出参匹配
    private String deviceinfo;  // 设备信息
    private String content; // 日志内容
    private String time;    // 日志时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getEventnumber() {
        return eventnumber;
    }

    public void setEventnumber(long eventnumber) {
        this.eventnumber = eventnumber;
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
