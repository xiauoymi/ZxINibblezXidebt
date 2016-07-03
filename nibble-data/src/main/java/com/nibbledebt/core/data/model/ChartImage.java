package com.nibbledebt.core.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity()
@Table(name = "ChartImage")
public class ChartImage extends AbstractModel {
    @Lob
    @Column( name = "CONTENT" )
    private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
    
}
