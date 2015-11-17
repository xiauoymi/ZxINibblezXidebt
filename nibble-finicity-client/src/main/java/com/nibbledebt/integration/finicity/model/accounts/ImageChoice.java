package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * @author a.salachyonok
 */
@JsonRootName("imageChoice")
public class ImageChoice {

    @JacksonXmlProperty(isAttribute = true)
    private String value;

    @JacksonXmlText
    private String body;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
