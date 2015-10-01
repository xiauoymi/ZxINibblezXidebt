package com.nibbledebt.integration.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Discover Customer Accounts Response with MFA (Multi image choice)
 * @author a.salachyonok
 */
public class ImageChoiceMfaChallenges extends MfaChallenges<ImageChoiceQuestion> {
}
