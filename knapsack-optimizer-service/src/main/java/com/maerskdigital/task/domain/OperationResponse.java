package com.maerskdigital.task.domain;


public class OperationResponse {

	  public enum ResponseStatusEnum {SUCCESS, ERROR, WARNING, NO_ACCESS};
	  private String  operationMessage;
	  private ResponseStatusEnum  operationStatus;

	public String getOperationMessage() {
		return operationMessage;
	}

	public void setOperationMessage(String operationMessage) {
		this.operationMessage = operationMessage;
	}

	public ResponseStatusEnum getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(ResponseStatusEnum operationStatus) {
		this.operationStatus = operationStatus;
	}
	


}
