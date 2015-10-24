package com.nibbledebt.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * @author alam_home
 *
 */
public class Institutions {
    private String moreAvailable;

    private String displaying;

    private String found;

    private Institution[] institution;

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
	 * @return the institution
	 */
	public Institution[] getInstitution() {
		return institution;
	}



	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution[] institution) {
		this.institution = institution;
	}



	@Override
    public String toString()  {
        return ToStringBuilder.reflectionToString(this);
    }
}
	