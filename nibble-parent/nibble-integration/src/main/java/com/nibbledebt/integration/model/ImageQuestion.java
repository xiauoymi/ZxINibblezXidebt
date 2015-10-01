package com.nibbledebt.integration.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author a.salachyonok
 */

public class ImageQuestion extends Question {

    @JacksonXmlProperty(localName = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
