/**
 * SourceDetails.java
 * Purpose: Source details model
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.io.Serializable;

/**
 * Methods for source details
 */
public class SourceDetails implements Serializable {
	
	private static final long serialVersionUID = -658426844249370951L;
	
	private Long holdingPeriod;
	private String source;
	private String sourceDescription;
	private Integer maxVend;
	private Integer vendConfigurable;

	/*
	 * Method to get the source description
	 * @param
	 * @return String
	 */
	public String getSourceDescription() {
		return sourceDescription;
	}

	/*
	 * Method to set the source description
	 * @param String - source description
	 * @return
	 */
	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	/*
	 * Constructor for source details
	 */
	public SourceDetails() {
	}

	/*
	 * Method to get the holding period
	 * @param
	 * @return Long
	 */
	public Long getHoldingPeriod() {
		return holdingPeriod;
	}

	/*
	 * Method to set the holding period
	 * @param Long - holding period
	 * @return
	 */
	public void setHoldingPeriod(Long holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}

	/*
	 * Method to get the source
	 * @param
	 * @return String
	 */
	public String getSource() {
		return source;
	}

	/*
	 * Method to set the source
	 * @param String - source
	 * @return 
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Method to get the maxVend
	 * @return integer
	 */
	public Integer getMaxVend() {
		return maxVend;
	}

	/**
	 * Method to set the maxVend
	 * @param maxVend of type integer
	 */
	public void setMaxVend(final Integer maxVend) {
		this.maxVend = maxVend;
	}

	/**
	 * Method to get the vendConfigurable
	 * @return integer
	 */
	public Integer getVendConfigurable() {
		return vendConfigurable;
	}

	/**
	 * Method to set the vendConfigurable
	 * @param vendConfigurable of type integer
	 */
	public void setVendConfigurable(final Integer vendConfigurable) {
		this.vendConfigurable = vendConfigurable;
	}

}