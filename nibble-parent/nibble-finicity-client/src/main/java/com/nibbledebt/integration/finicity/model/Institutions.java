/**
 * 
 */
package com.nibbledebt.integration.finicity.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author alam_home
 *
 */
@JsonRootName("institutions")
public class Institutions
{
    private String moreAvailable;

    private String displaying;

    private String found;

    private List<Institution> institution;

   

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
	public List<Institution> getInstitution() {
		return institution;
	}



	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(List<Institution> institution) {
		this.institution = institution;
	}



	@Override
    public String toString()  {
        return ToStringBuilder.reflectionToString(this);
    }
}
	