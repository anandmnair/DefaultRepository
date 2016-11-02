package com.es.demo.bean;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Sbu {

	@Field(type=FieldType.Long, index=FieldIndex.not_analyzed)
	private Long sbuId;
	
	@Field(type=FieldType.String, index=FieldIndex.not_analyzed)
	private String sbuCode;
	
	@Field(type=FieldType.String)
	private String sbuName;
	
	@Field(type=FieldType.Object)
	private Country country;

	/**
	 * @return the sbuId
	 */
	public Long getSbuId() {
		return sbuId;
	}

	/**
	 * @param sbuId the sbuId to set
	 */
	public void setSbuId(Long sbuId) {
		this.sbuId = sbuId;
	}

	/**
	 * @return the sbuCode
	 */
	public String getSbuCode() {
		return sbuCode;
	}

	/**
	 * @param sbuCode the sbuCode to set
	 */
	public void setSbuCode(String sbuCode) {
		this.sbuCode = sbuCode;
	}

	/**
	 * @return the sbuName
	 */
	public String getSbuName() {
		return sbuName;
	}

	/**
	 * @param sbuName the sbuName to set
	 */
	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
