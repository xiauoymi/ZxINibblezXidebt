/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.nibble.integration.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ralam
 *
 */
public class TransactionSummary {
	private String personLastName;
	private String personFirstName;
	private Long personId;
	private List<Transaction> trxs;
	private BigDecimal day0total = BigDecimal.ZERO;
	private BigDecimal day1total = BigDecimal.ZERO;
	private BigDecimal day2total = BigDecimal.ZERO;
	private BigDecimal day3total = BigDecimal.ZERO;
	private BigDecimal day4total = BigDecimal.ZERO;
	private BigDecimal day5total = BigDecimal.ZERO;
	private BigDecimal day6total = BigDecimal.ZERO;
	private BigDecimal weeklyTarget = BigDecimal.ZERO;
	private BigDecimal currentWeekAmount;
	private Integer currentTargetPercent;
	private BigDecimal totalAmountPaid = BigDecimal.ZERO;
	private BigDecimal totalAmountSaved = BigDecimal.ZERO;
	
	private List<TransactionSummary> contributorSummaries;
	
	/**
	 * @return the personLastName
	 */
	public String getPersonLastName() {
		return personLastName;
	}
	/**
	 * @param personLastName the personLastName to set
	 */
	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}
	/**
	 * @return the personFirstName
	 */
	public String getPersonFirstName() {
		return personFirstName;
	}
	/**
	 * @param personFirstName the personFirstName to set
	 */
	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}
	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	/**
	 * @return the trxs
	 */
	public List<Transaction> getTrxs() {
		return trxs;
	}
	/**
	 * @param trxs the trxs to set
	 */
	public void setTrxs(List<Transaction> trxs) {
		this.trxs = trxs;
	}
	/**
	 * @return the weeklyTarget
	 */
	public BigDecimal getWeeklyTarget() {
		return weeklyTarget;
	}
	/**
	 * @param weeklyTarget the weeklyTarget to set
	 */
	public void setWeeklyTarget(BigDecimal weeklyTarget) {
		this.weeklyTarget = weeklyTarget;
	}
	/**
	 * @return the currentWeekAmount
	 */
	public BigDecimal getCurrentWeekAmount() {
		return currentWeekAmount;
	}
	/**
	 * @param currentWeekAmount the currentWeekAmount to set
	 */
	public void setCurrentWeekAmount(BigDecimal currentWeekAmount) {
		this.currentWeekAmount = currentWeekAmount;
	}
	/**
	 * @return the currentTargetPercent
	 */
	public Integer getCurrentTargetPercent() {
		return currentTargetPercent;
	}
	/**
	 * @param currentTargetPercent the currentTargetPercent to set
	 */
	public void setCurrentTargetPercent(Integer currentTargetPercent) {
		this.currentTargetPercent = currentTargetPercent;
	}
	/**
	 * @return the day0total
	 */
	public BigDecimal getDay0total() {
		return day0total;
	}
	/**
	 * @param day0total the day0total to set
	 */
	public void setDay0total(BigDecimal day0total) {
		this.day0total = day0total;
	}
	/**
	 * @return the day1total
	 */
	public BigDecimal getDay1total() {
		return day1total;
	}
	/**
	 * @param day1total the day1total to set
	 */
	public void setDay1total(BigDecimal day1total) {
		this.day1total = day1total;
	}
	/**
	 * @return the day2total
	 */
	public BigDecimal getDay2total() {
		return day2total;
	}
	/**
	 * @param day2total the day2total to set
	 */
	public void setDay2total(BigDecimal day2total) {
		this.day2total = day2total;
	}
	/**
	 * @return the day3total
	 */
	public BigDecimal getDay3total() {
		return day3total;
	}
	/**
	 * @param day3total the day3total to set
	 */
	public void setDay3total(BigDecimal day3total) {
		this.day3total = day3total;
	}
	/**
	 * @return the day4total
	 */
	public BigDecimal getDay4total() {
		return day4total;
	}
	/**
	 * @param day4total the day4total to set
	 */
	public void setDay4total(BigDecimal day4total) {
		this.day4total = day4total;
	}
	/**
	 * @return the day5total
	 */
	public BigDecimal getDay5total() {
		return day5total;
	}
	/**
	 * @param day5total the day5total to set
	 */
	public void setDay5total(BigDecimal day5total) {
		this.day5total = day5total;
	}
	/**
	 * @return the day6total
	 */
	public BigDecimal getDay6total() {
		return day6total;
	}
	/**
	 * @param day6total the day6total to set
	 */
	public void setDay6total(BigDecimal day6total) {
		this.day6total = day6total;
	}
	/**
	 * @return the totalAmountPaid
	 */
	public BigDecimal getTotalAmountPaid() {
		return totalAmountPaid;
	}
	/**
	 * @param totalAmountPaid the totalAmountPaid to set
	 */
	public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
	/**
	 * @return the totalAmountSaved
	 */
	public BigDecimal getTotalAmountSaved() {
		return totalAmountSaved;
	}
	/**
	 * @param totalAmountSaved the totalAmountSaved to set
	 */
	public void setTotalAmountSaved(BigDecimal totalAmountSaved) {
		this.totalAmountSaved = totalAmountSaved;
	}
	/**
	 * @return the contributorSummaries
	 */
	public List<TransactionSummary> getContributorSummaries() {
		if(contributorSummaries == null) contributorSummaries = new ArrayList<>();
		return contributorSummaries; 
	}
	/**
	 * @param contributorSummaries the contributorSummaries to set
	 */
	public void setContributorSummaries(List<TransactionSummary> contributorSummaries) {
		this.contributorSummaries = contributorSummaries;
	}
	
}
