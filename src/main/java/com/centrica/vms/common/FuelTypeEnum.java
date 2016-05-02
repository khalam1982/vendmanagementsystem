/**
 * 
 */
package com.centrica.vms.common;

public enum FuelTypeEnum{
	   
	ELECTRIC(1),GAS(2);
	   
	private int fuelType;
	   
    private FuelTypeEnum(int fuelType){
	   this.fuelType = fuelType;
    }
	   
   public int getFuelType(){
	   return this.fuelType;
   }
}
