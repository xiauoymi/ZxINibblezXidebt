/**
 * 
 */
package com.nibbledebt.integration.finicity.model.trxs;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author alam_home
 *
 */
@JsonRootName("transactions")
public class Transactions
{
	@JacksonXmlProperty(isAttribute=true)
    private String moreAvailable;

	@JacksonXmlProperty(isAttribute=true)
    private String displaying;

	@JacksonXmlProperty(isAttribute=true)
    private String found;
	
	@JacksonXmlProperty(isAttribute=true)
    private String fromDate;
	
	@JacksonXmlProperty(isAttribute=true)
    private String toDate;
	
	@JacksonXmlProperty(isAttribute=true)
    private String sort;

	@JacksonXmlProperty(localName = "transaction")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Transaction[] transaction;

    /**
	 * @return the moreAvailable
	 */
	public String getMoreAvailable() {
		return moreAvailable;
	}



	/**
	 * @param moreAvailable the moreAvailable to set
	 */
	public void setMoreAvailable(String moreAvailable) {
		this.moreAvailable = moreAvailable;
	}



	/**
	 * @return the displaying
	 */
	public String getDisplaying() {
		return displaying;
	}



	/**
	 * @param displaying the displaying to set
	 */
	public void setDisplaying(String displaying) {
		this.displaying = displaying;
	}



	/**
	 * @return the found
	 */
	public String getFound() {
		return found;
	}



	/**
	 * @param found the found to set
	 */
	public void setFound(String found) {
		this.found = found;
	}


	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}



	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}



	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}



	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}



	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}



	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}



	/**
	 * @return the transaction
	 */
	public Transaction[] getTransaction() {
		return transaction;
	}



	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction[] transaction) {
		this.transaction = transaction;
	}



	@Override
    public String toString()  {
        return ToStringBuilder.reflectionToString(this);
    }
}
	