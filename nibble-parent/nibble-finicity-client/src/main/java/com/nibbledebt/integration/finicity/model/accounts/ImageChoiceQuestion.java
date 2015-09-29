package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.nibbledebt.integration.finicity.model.accounts.ImageChoice;

/**
 * @author a.salachyonok
 */

public class ImageChoiceQuestion extends Question{

    @JacksonXmlProperty(localName = "imageChoice")
    @JacksonXmlElementWrapper(useWrapping = false)
    private ImageChoice[] imageChoice;

    public ImageChoice[] getImageChoice() {
        return imageChoice;
    }

    public void setImageChoice(ImageChoice[] imageChoice) {
        this.imageChoice = imageChoice;
    }
}
