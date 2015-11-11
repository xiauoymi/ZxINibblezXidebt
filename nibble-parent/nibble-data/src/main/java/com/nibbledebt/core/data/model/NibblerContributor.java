/**
 * 
 */
package com.nibbledebt.core.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ralam
 *
 */
@Entity
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_id"))
})
@Table(name="nibbler")
@DiscriminatorValue("contributor")
public class NibblerContributor extends Nibbler{
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receiver_id", updatable=true, nullable=true)
	private NibblerReceiver receiver;

	/**
	 * @return the receiver
	 */
	public Nibbler getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(NibblerReceiver receiver) {
		this.receiver = receiver;
	}
	
}