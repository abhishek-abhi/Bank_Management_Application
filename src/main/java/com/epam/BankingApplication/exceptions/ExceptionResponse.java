package com.epam.BankingApplication.exceptions;

import java.util.Date;

/**
 * @author Abhishek_Kumar_Mandal
 *
 */
public class ExceptionResponse {

	private Date timeStamp;
	private String message;
	private String details;

	/**
	 * @param timeStamp Date
	 * @param message   String
	 * @param details   String
	 */
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
