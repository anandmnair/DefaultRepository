package com.es.demo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="deals" , type="deal")
public class Deal {
	
	@Id
	private Long dealId;
	
	@Field(type=FieldType.String, index=FieldIndex.not_analyzed)
	private String dealCode;
	
	@Field(type=FieldType.String)
	private String dealName;
	
	@Field(type=FieldType.Nested)
	private Sbu sbu;
	
	@Field(type=FieldType.String)
	private String primarySbu;

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

	
	/**
	 * @return the primarySbu
	 */
	public String getPrimarySbu() {
		return primarySbu;
	}

	/**
	 * @param primarySbu the primarySbu to set
	 */
	public void setPrimarySbu(String primarySbu) {
		this.primarySbu = primarySbu;
	}

	/**
	 * @return the sbu
	 */
	public Sbu getSbu() {
		return sbu;
	}

	/**
	 * @param sbu the sbu to set
	 */
	public void setSbu(Sbu sbu) {
		this.sbu = sbu;
	}
	
	
}
