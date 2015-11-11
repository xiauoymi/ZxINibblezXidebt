/**
 * 
 */
package com.nibbledebt.core.data.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author ralam
 *
 */
@Entity
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_id"))
})
@Table(	name="nibbler",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"invitation_code", "nibbler_id"})
	}
)
@DiscriminatorValue("receiver")
public class NibblerReceiver extends Nibbler{
	@Column(name="invitation_code", nullable=true, length=100)
	private Integer invitationCode;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="receiver", orphanRemoval=true)
	private List<NibblerContributor> contributors;


	/**
	 * @return the invitationCode
	 */
	public Integer getInvitationCode() {
		return invitationCode;
	}

	/**
	 * @param invitationCode the invitationCode to set
	 */
	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
	}

	/**
	 * @return the contributors
	 */
	public List<NibblerContributor> getContributors() {
		return contributors;
	}

	/**
	 * @param contributors the contributors to set
	 */
	public void setContributors(List<NibblerContributor> contributors) {
		this.contributors = contributors;
	}	
}
