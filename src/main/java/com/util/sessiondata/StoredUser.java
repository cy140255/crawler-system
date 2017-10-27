package com.util.sessiondata;

import java.util.Set;

//@Immutable
public class StoredUser {
	public static final String KEY = "user";

	private  String userDisplayName;
	
	private final String name;

	private  String type;

	private final Set<String> authorities;

	private String companyStatus;

	private  String companyName;

	private  Long companyId;

	private  Long organizationId;

	private Boolean isForwarder;

	private Boolean isMonthlyPayment;

	private Boolean hasContract;
	private Boolean isVip;

	private Boolean isAnsteelUser = Boolean.FALSE;

	private  String email;
	private  String mobilePhone;
	private  String phone;
	private String contact;
	private  String language;
	private String certificateStatus;

    public StoredUser(final String userDisplayName, final String name, final String type,
            final Long companyId, final Long organizationId,
            final String companyStatus, final Set<String> authorities,
            final Boolean isForwarder, Boolean isMonthlyPayment,
            final Boolean hasContract, Boolean isVip, final String companyName,
            String email, String mobilePhone, String phone, String contact,
            String language) {
        this.companyId = companyId;
        this.organizationId = organizationId;
		this.companyStatus = companyStatus;
		this.name = name;
		this.userDisplayName = userDisplayName;
		this.type = type;
		this.authorities = authorities;
		this.isForwarder = null != isForwarder ? isForwarder : false;
		this.isMonthlyPayment = null != isMonthlyPayment ? isMonthlyPayment
				: false;
		this.companyName = companyName;
		this.hasContract = null != hasContract ? hasContract : false;
		this.isVip = null != isVip ? isVip : false;
		this.contact = contact;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.email = email;
		this.language = language;
	}

    public StoredUser(final String userDisplayName, final String name, final String type,
            final Long companyId, final Long organizationId,
            final Set<String> authorities, final String companyName,
            String email, String mobilePhone, String phone, String language) {
        this.companyId = companyId;
        this.name = name;
        this.userDisplayName = userDisplayName;
        this.type = type;
        this.organizationId = organizationId;
        this.authorities = authorities;
        this.companyName = companyName;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.language = language;
    }
    public StoredUser(final String userDisplayName, final String name, final String type,
    		final Long companyId, final Long organizationId,
    		final Set<String> authorities, final String companyName,
    		String email, String mobilePhone, String phone, String language ,String certificateStatus) {
    	this.companyId = companyId;
    	this.name = name;
    	this.userDisplayName = userDisplayName;
    	this.type = type;
    	this.organizationId = organizationId;
    	this.authorities = authorities;
    	this.companyName = companyName;
    	this.phone = phone;
    	this.mobilePhone = mobilePhone;
    	this.email = email;
    	this.language = language;
    	this.certificateStatus = certificateStatus;
    }

	// 中巴快线
	public StoredUser( final String name, final String type,
			final Set<String> authorities) {
		this.name = name;
		this.type = type;
		this.authorities = authorities;
	}

	// release container
	public StoredUser( final String userId, final String name, final Long companyId, final String companyName,
			final Set<String> authorities,String email, String mobilePhone, String phone) {
		this.name = userId;
		this.userDisplayName = name;
		this.companyId = companyId;
		this.authorities = authorities;
		this.companyName = companyName;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}

	public Boolean getIsAnsteelUser() {
		return isAnsteelUser;
	}

	public void setIsAnsteelUser(Boolean isAnsteelUser) {
		this.isAnsteelUser = isAnsteelUser;
	}

	public String getLanguage() {
		return language;
	}

	public String getEmail() {
		return email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public String getContact() {
		return contact;
	}

	public Boolean isMonthlyPayment() {
		return isMonthlyPayment;
	}

	public Boolean hasContract() {
		return hasContract;
	}

	public Boolean isForwarder() {
		return isForwarder;
	}

	public Long getCompanyId() {
		return companyId;
	}

    public Long getOrganizationId() {
        return companyId;
    }

    public String getCompanyStatus() {
		return companyStatus;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean hasRight(String authority) {
		return authorities.contains(authority);
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public String getCertificateStatus() {
		return certificateStatus;
	}

	public void setCertificateStatus(String certificateStatus) {
		this.certificateStatus = certificateStatus;
	}
	
	
}
