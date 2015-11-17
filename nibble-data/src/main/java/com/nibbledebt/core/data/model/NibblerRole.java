/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Rocky Alam
 *
 */
@NamedQueries({
	@NamedQuery(name="findNibblerRoleByName", query="from NibblerRole n where n.name = :name")
})
@Entity()
@Table(	name="nibbler_role",  
		uniqueConstraints = {
			@UniqueConstraint(columnNames = {"name"})
		})
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="nibbler_role_id"))
})
public class NibblerRole extends AbstractModel {
	
	@Column(name="name", nullable=false, length=25)
	private String name;
		
	@Column(name="description", nullable=true, length=100)
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<NibblerDirectory> nibblerDirs;

	public NibblerRole(){
		super();
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the nibblerDirs
	 */
	public Set<NibblerDirectory> getNibblerDirs() {
		return nibblerDirs;
	}

	/**
	 * @param nibblerDirs the nibblerDirs to set
	 */
	public void setNibblerDirs(Set<NibblerDirectory> nibblerDirs) {
		this.nibblerDirs = nibblerDirs;
	}
	
	
	
}
