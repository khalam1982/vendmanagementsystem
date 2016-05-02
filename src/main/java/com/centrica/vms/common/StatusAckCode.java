/**
 * 
 */
package com.centrica.vms.common;

/**
 * Status code for ACK 
 * @author nagarajk
 *
 */

public enum StatusAckCode {
	   
	ACCEPTED(200), INVALID(300), DUPLICATE(310), ERROR(320), MAX_CREDIT(330), KEYPAD_LOCK(340), UNKNOWN(350);
	   
	private int statusCode;
	   
	private StatusAckCode(int statusCode){
	   this.statusCode = statusCode;
	}
	   
	public int getStatusCode(){
	   return this.statusCode;
	}
}
