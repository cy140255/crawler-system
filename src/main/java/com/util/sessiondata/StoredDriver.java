package com.util.sessiondata;

//@Immutable
public class StoredDriver {
	public static final String KEY = "driver";

    private String id;

	private String name;

	private String mobile;

    private String token;

    public static String getKEY() {
        return KEY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
