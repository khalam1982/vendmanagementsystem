/**
 * 
 */
package com.centrica.vms.common;

/**
 * Manufacturer Types 
 * @author nagarajk
 *
 */

public enum ManuTypeEnum{
	   
	LG(1);
	   
	private int manuType;
	   
 private ManuTypeEnum(int manuType){
	   this.manuType = manuType;
 }
	   
public int getManuType(){
	   return this.manuType;
}
}
