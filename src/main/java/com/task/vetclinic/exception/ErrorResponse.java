package com.task.vetclinic.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.task.vetclinic.customresponse.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public final class ErrorResponse extends BaseResponse {

	private String errorDetails;
	
	public ErrorResponse(String errorMsg , Integer errorCode) {
		super(Boolean.FALSE, errorMsg , errorCode);
	}

	public ErrorResponse(String errorMsg, Integer errorCode, String errorDetails) {
		super(Boolean.FALSE, errorMsg, errorCode);
		this.errorDetails = errorDetails;
	}
}