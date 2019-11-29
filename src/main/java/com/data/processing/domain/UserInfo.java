package com.data.processing.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4265173931263772442L;

    @Id
    @GeneratedValue
    private Integer categoryId;//id

    private String userID;//用户账号

    private String url;//用户浏览url

    private Integer port;//用户浏览网页端口号

    private String userMAC;//用户mac

    private String userIP;//用户ip

    private String urltime;

    private Integer type;

    private String terminal;

    public UserInfo(){}

    public UserInfo(String userID, String url, Integer port, String userMAC, String userIP, String urltime, Integer type, String terminal){
        this.userID = userID;
        this.url = url;
        this.port = port;
        this.userMAC = userMAC;
        this.userIP = userIP;
        this.urltime = urltime;
        this.type = type;
        this.terminal = terminal;
    }

}
