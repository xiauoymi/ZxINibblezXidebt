/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.intuit.cad.mapper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.nibbledebt.intuit.cad.data.CurrencyCode;

/**
 * @author ralam
 *
 */
public class CurrencyCodeDeserializer extends JsonDeserializer<CurrencyCode> {
    @Override
    public CurrencyCode deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        return CurrencyCode.fromValue(parser.getValueAsString());
    }
}
