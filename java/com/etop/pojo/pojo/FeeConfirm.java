package com.etop.pojo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author frances.xu
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_broadbandFee")
public class FeeConfirm {
		
	private Integer  id;
	
	private String  broadbandId;
	
	private String feeDate;

	private  BigDecimal fee;
	
	private  BigDecimal afterTaxFee;
	
    private BigDecimal valueAddTax;

	private String agent;
	
	private String director;
	
	private BigDecimal changedFee;
	
	private String reason;
	
	private float useRatio;
	
	private int confirmed;
	
	
	public BigDecimal getChangedFee() {
		return changedFee;
	}

	public void setChangedFee(BigDecimal changedFee) {
		this.changedFee = changedFee;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

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

	public String getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	public BigDecimal getAfterTaxFee() {
		return afterTaxFee;
	}

	public BigDecimal getValueAddTax() {
		return valueAddTax;
	}

	public void setValueAddTax(BigDecimal valueAddTax) {
		this.valueAddTax = valueAddTax;
	}

	public void setAfterTaxFee(BigDecimal afterTaxFee) {
		this.afterTaxFee = afterTaxFee;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public float getUseRatio() {
		return useRatio;
	}

	public void setUseRatio(float useRatio) {
		this.useRatio = useRatio;
	}


	
	
	

}
