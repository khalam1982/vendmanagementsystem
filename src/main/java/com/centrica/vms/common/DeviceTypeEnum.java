/**
 * 
 */
package com.centrica.vms.common;

/**
 * Device Types 
 * @author nagarajk
 *
 */

public enum DeviceTypeEnum{

	PH2B(0), PH3(1), ISU(2), CIM(3), PPK(4), PPK_ACK(5), VEND_ACK(6);

	private int deviceType;

	private DeviceTypeEnum(int deviceType){
		this.deviceType = deviceType;
	}

	public int getDeviceType(){
		return this.deviceType;
	}

}
