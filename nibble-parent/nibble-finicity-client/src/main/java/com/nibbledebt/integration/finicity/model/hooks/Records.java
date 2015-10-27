/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.finicity.model.hooks;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author ralam
 *
 */
@JsonRootName("records")
public interface Records<T> {
    public T[] getRecord();
    public void setRecord(T[] record);
}
