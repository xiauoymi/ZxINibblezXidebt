//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.17 at 03:39:51 PM CDT 
//


package com.nibbledebt.intuit.cad.data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.intuit.com/platform/fdatafeed/account/v1}Account">
 *       &lt;sequence>
 *         &lt;element name="bankingAccountType" type="{http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1}BankingAccountType" minOccurs="0"/>
 *         &lt;element name="postedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="availableBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="interestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="openDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="periodInterestRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="periodDepositAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="periodInterestAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="interestAmountYtd" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="interestPriorAmountYtd" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="maturityDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="maturityAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bankingAccountType",
    "postedDate",
    "availableBalanceAmount",
    "interestType",
    "originationDate",
    "openDate",
    "periodInterestRate",
    "periodDepositAmount",
    "periodInterestAmount",
    "interestAmountYtd",
    "interestPriorAmountYtd",
    "maturityDate",
    "maturityAmount"
})
@XmlRootElement(name = "BankingAccount", namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
public class BankingAccount
    extends Account
{

    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BankingAccountType bankingAccountType;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postedDate;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal availableBalanceAmount;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected String interestType;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar originationDate;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar openDate;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal periodInterestRate;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal periodDepositAmount;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal periodInterestAmount;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal interestAmountYtd;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal interestPriorAmountYtd;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar maturityDate;
    @XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/bankingaccount/v1")
    protected BigDecimal maturityAmount;

    /**
     * Gets the value of the bankingAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link BankingAccountType }
     *     
     */
    public BankingAccountType getBankingAccountType() {
        return bankingAccountType;
    }

    /**
     * Sets the value of the bankingAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankingAccountType }
     *     
     */
    public void setBankingAccountType(BankingAccountType value) {
        this.bankingAccountType = value;
    }

    /**
     * Gets the value of the postedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostedDate() {
        return postedDate;
    }

    /**
     * Sets the value of the postedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostedDate(XMLGregorianCalendar value) {
        this.postedDate = value;
    }

    /**
     * Gets the value of the availableBalanceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAvailableBalanceAmount() {
        return availableBalanceAmount;
    }

    /**
     * Sets the value of the availableBalanceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAvailableBalanceAmount(BigDecimal value) {
        this.availableBalanceAmount = value;
    }

    /**
     * Gets the value of the interestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterestType() {
        return interestType;
    }

    /**
     * Sets the value of the interestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterestType(String value) {
        this.interestType = value;
    }

    /**
     * Gets the value of the originationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOriginationDate() {
        return originationDate;
    }

    /**
     * Sets the value of the originationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOriginationDate(XMLGregorianCalendar value) {
        this.originationDate = value;
    }

    /**
     * Gets the value of the openDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOpenDate() {
        return openDate;
    }

    /**
     * Sets the value of the openDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOpenDate(XMLGregorianCalendar value) {
        this.openDate = value;
    }

    /**
     * Gets the value of the periodInterestRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodInterestRate() {
        return periodInterestRate;
    }

    /**
     * Sets the value of the periodInterestRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodInterestRate(BigDecimal value) {
        this.periodInterestRate = value;
    }

    /**
     * Gets the value of the periodDepositAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodDepositAmount() {
        return periodDepositAmount;
    }

    /**
     * Sets the value of the periodDepositAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodDepositAmount(BigDecimal value) {
        this.periodDepositAmount = value;
    }

    /**
     * Gets the value of the periodInterestAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodInterestAmount() {
        return periodInterestAmount;
    }

    /**
     * Sets the value of the periodInterestAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodInterestAmount(BigDecimal value) {
        this.periodInterestAmount = value;
    }

    /**
     * Gets the value of the interestAmountYtd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterestAmountYtd() {
        return interestAmountYtd;
    }

    /**
     * Sets the value of the interestAmountYtd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterestAmountYtd(BigDecimal value) {
        this.interestAmountYtd = value;
    }

    /**
     * Gets the value of the interestPriorAmountYtd property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterestPriorAmountYtd() {
        return interestPriorAmountYtd;
    }

    /**
     * Sets the value of the interestPriorAmountYtd property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterestPriorAmountYtd(BigDecimal value) {
        this.interestPriorAmountYtd = value;
    }

    /**
     * Gets the value of the maturityDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMaturityDate() {
        return maturityDate;
    }

    /**
     * Sets the value of the maturityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMaturityDate(XMLGregorianCalendar value) {
        this.maturityDate = value;
    }

    /**
     * Gets the value of the maturityAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaturityAmount() {
        return maturityAmount;
    }

    /**
     * Sets the value of the maturityAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaturityAmount(BigDecimal value) {
        this.maturityAmount = value;
    }

}
