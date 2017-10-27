package com.util.Dto;



//@XmlRootElement
public class BaseResponseDto {

	private Boolean success = true;
	private String message;
	private String stackTrace;
	// if success == false, messageTemplate should equals to True.toString
	private String messageTemplate;
	private String path;
	private String invalidValue;
	private String messageInfo;

    public BaseResponseDto() {
	}

	public BaseResponseDto(final Boolean success, final String message,
						   String stackTrace) {
		this.success = success;
		this.message = message;//test
		this.stackTrace = stackTrace;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getInvalidValue() {
		return invalidValue;
	}

	public void setInvalidValue(String invalidValue) {
		this.invalidValue = invalidValue;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

}
