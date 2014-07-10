package com.benpoor;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author shensw
 * @version 1.0.0
 * @Description
 * @date 2014/7/10.16:28
 */
@ConfigurationProperties(value="jdbc")
public class ConnectionSettings {
    private String driver;
    private String url;
    private String username;
    private String password;

    public ConnectionSettings() {}

    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
