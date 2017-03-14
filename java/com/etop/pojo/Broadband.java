package com.etop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @类名：
 * @description:
 * @date: 2016-03-29
 * @author frances.xu
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_broadband")
public class Broadband implements Serializable {
	
	private Integer  id;
	
	private String  broadbandId;
	
	private Date stopDate;
	
	private String city;
	
	private String status;
	
	private String payOrganization;
	
	private String address;
	
	private String lineType;
	
	private String linkAddress;
	
	private String operator;
	
	private String  accessWay;
	
	private String bandwidth;
	
	private  BigDecimal fee;
	
	private BigDecimal valueAddTax;
	
	private String agent;
	
	private String director;
	
	private String paymentMethod;
	
	private String settlementCycle;
	
	private String   paymentMonth;
	
	private String feeCollection;
	
	private float useRatio;
	
	private String networkUsage;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBroadbandId() {
		return broadbandId;
	}

	public void setBroadbandId(String broadbandId) {
		this.broadbandId = broadbandId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayOrganization() {
		return payOrganization;
	}

	public void setPayOrganization(String payOrganization) {
		this.payOrganization = payOrganization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAccessWay() {
		return accessWay;
	}

	public void setAccessWay(String accessWay) {
		this.accessWay = accessWay;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getValueAddTax() {
		return valueAddTax;
	}

	public void setValueAddTax(BigDecimal valueAddTax) {
		this.valueAddTax = valueAddTax;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSettlementCycle() {
		return settlementCycle;
	}

	public void setSettlementCycle(String settlementCycle) {
		this.settlementCycle = settlementCycle;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String  paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public String getFeeCollection() {
		return feeCollection;
	}

	public void setFeeCollection(String feeCollection) {
		this.feeCollection = feeCollection;
	}

	public float getUseRatio() {
		return useRatio;
	}

	public void setUseRatio(float useRatio) {
		this.useRatio = useRatio;
	}

	public String getNetworkUsage() {
		return networkUsage;
	}

	public void setNetworkUsage(String networkUsage) {
		this.networkUsage = networkUsage;
	}


	

}
