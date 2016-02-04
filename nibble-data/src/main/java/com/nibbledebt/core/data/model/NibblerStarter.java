/**
 * 
 */
package com.nibbledebt.core.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@DiscriminatorValue("starter")
public class NibblerStarter extends Nibbler{

	
}