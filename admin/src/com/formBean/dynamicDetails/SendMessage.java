
package com.formBean.dynamicDetails;


public class SendMessage  {
	
	private Long id;
	private String type;
	private String destination;
	private String message;
	private Integer status;
	
	@Override
	public String toString() {
		return "CollectionBankDetailsFormBean [id=" + id + ", type=" + type + ", destination=" + destination
				+ ", message=" + message + ", status=" + status + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
