package com.anand.batch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="deal", type="deal")
public class Deal {

	@Id
	private Long dealId;
	private String dealCode;
	private String dealName;
	public Deal() {
	}

	
	public Deal(Long dealId, String dealCode, String dealName) {
		super();
		this.dealId = dealId;
		this.dealCode = dealCode;
		this.dealName = dealName;
	}


	/**
	 * @return the dealId
	 */
	public Long getDealId() {
		return dealId;
	}
	/**
	 * @param dealId the dealId to set
	 */
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	/**
	 * @return the dealCode
	 */
	public String getDealCode() {
		return dealCode;
	}
	/**
	 * @param dealCode the dealCode to set
	 */
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	/**
	 * @return the dealName
	 */
	public String getDealName() {
		return dealName;
	}
	/**
	 * @param dealName the dealName to set
	 */
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	
	
}
