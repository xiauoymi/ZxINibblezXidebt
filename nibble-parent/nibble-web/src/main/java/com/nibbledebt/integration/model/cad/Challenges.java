/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.cad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

/**
 * @author ralam
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"challenges"})
@XmlRootElement(name="Challenges", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1")
public class Challenges
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;

  @XmlElement(name="challenge", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", required=true)
  protected List<Challenge> challenges;

  public List<Challenge> getChallenges()
  {
    if (this.challenges == null) {
      this.challenges = new ArrayList();
    }
    return this.challenges;
  }

  public void setChallenges(List<Challenge> challenges)
  {
    this.challenges = challenges;
  }

  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
    if (!(object instanceof Challenges)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Challenges that = (Challenges)object;

    List lhsChallenges = (this.challenges != null) && (!this.challenges.isEmpty()) ? getChallenges() : null;

    List rhsChallenges = (that.challenges != null) && (!that.challenges.isEmpty()) ? that.getChallenges() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "challenges", lhsChallenges), LocatorUtils.property(thatLocator, "challenges", rhsChallenges), lhsChallenges, rhsChallenges)) {
      return false;
    }

    return true;
  }

  public boolean equals(Object object) {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }

  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
    int currentHashCode = 1;

    List theChallenges = (this.challenges != null) && (!this.challenges.isEmpty()) ? getChallenges() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "challenges", theChallenges), currentHashCode, theChallenges);

    return currentHashCode;
  }

  public int hashCode() {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"textsAndImagesAndChoices"})
  public static class Challenge
    implements Serializable, Equals, HashCode
  {
    private static final long serialVersionUID = 1L;

    @XmlElements({@XmlElement(name="text", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", type=String.class), @XmlElement(name="image", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", type=byte[].class), @XmlElement(name="choice", namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", type=Choice.class)})
    protected List<Object> textsAndImagesAndChoices;

    public List<Object> getTextsAndImagesAndChoices()
    {
      if (this.textsAndImagesAndChoices == null) {
        this.textsAndImagesAndChoices = new ArrayList();
      }
      return this.textsAndImagesAndChoices;
    }

    public void setTextsAndImagesAndChoices(List<Object> textsAndImagesAndChoices)
    {
      this.textsAndImagesAndChoices = textsAndImagesAndChoices;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
      if (!(object instanceof Challenge)) {
        return false;
      }
      if (this == object) {
        return true;
      }
      Challenge that = (Challenge)object;

      List lhsTextsAndImagesAndChoices = (this.textsAndImagesAndChoices != null) && (!this.textsAndImagesAndChoices.isEmpty()) ? getTextsAndImagesAndChoices() : null;

      List rhsTextsAndImagesAndChoices = (that.textsAndImagesAndChoices != null) && (!that.textsAndImagesAndChoices.isEmpty()) ? that.getTextsAndImagesAndChoices() : null;
      if (!strategy.equals(LocatorUtils.property(thisLocator, "textsAndImagesAndChoices", lhsTextsAndImagesAndChoices), LocatorUtils.property(thatLocator, "textsAndImagesAndChoices", rhsTextsAndImagesAndChoices), lhsTextsAndImagesAndChoices, rhsTextsAndImagesAndChoices)) {
        return false;
      }

      return true;
    }

    public boolean equals(Object object) {
      EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
      return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
      int currentHashCode = 1;

      List theTextsAndImagesAndChoices = (this.textsAndImagesAndChoices != null) && (!this.textsAndImagesAndChoices.isEmpty()) ? getTextsAndImagesAndChoices() : null;
      currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "textsAndImagesAndChoices", theTextsAndImagesAndChoices), currentHashCode, theTextsAndImagesAndChoices);

      return currentHashCode;
    }

    public int hashCode() {
      HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
      return hashCode(null, strategy);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"text", "val"})
    public static class Choice
      implements Serializable, Equals, HashCode
    {
      private static final long serialVersionUID = 1L;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", required=true)
      protected String text;

      @XmlElement(namespace="http://schema.intuit.com/platform/fdatafeed/challenge/v1", required=true)
      protected String val;

      public String getText()
      {
        return this.text;
      }

      public void setText(String value)
      {
        this.text = value;
      }

      public String getVal()
      {
        return this.val;
      }

      public void setVal(String value)
      {
        this.val = value;
      }

      public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Choice)) {
          return false;
        }
        if (this == object) {
          return true;
        }
        Choice that = (Choice)object;

        String lhsText = getText();

        String rhsText = that.getText();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "text", lhsText), LocatorUtils.property(thatLocator, "text", rhsText), lhsText, rhsText)) {
          return false;
        }

        String lhsVal = getVal();

        String rhsVal = that.getVal();
        if (!strategy.equals(LocatorUtils.property(thisLocator, "val", lhsVal), LocatorUtils.property(thatLocator, "val", rhsVal), lhsVal, rhsVal)) {
          return false;
        }

        return true;
      }

      public boolean equals(Object object) {
        EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
      }

      public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;

        String theText = getText();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "text", theText), currentHashCode, theText);

        String theVal = getVal();
        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "val", theVal), currentHashCode, theVal);

        return currentHashCode;
      }

      public int hashCode() {
        HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return hashCode(null, strategy);
      }
    }
  }
}