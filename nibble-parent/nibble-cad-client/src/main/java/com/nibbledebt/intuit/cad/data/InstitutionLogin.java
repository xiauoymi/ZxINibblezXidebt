package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "challengeResponses", "credentials" })
@XmlRootElement(name = "InstitutionLogin", namespace = "http://schema.intuit.com/platform/fdatafeed/institutionlogin/v1")
public class InstitutionLogin implements Serializable, Equals, HashCode {
	private static final long serialVersionUID = 1L;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/institutionlogin/v1")
	protected ChallengeResponses challengeResponses;

	@XmlElement(namespace = "http://schema.intuit.com/platform/fdatafeed/institutionlogin/v1")
	protected Credentials credentials;

	public ChallengeResponses getChallengeResponses() {
		return this.challengeResponses;
	}

	public void setChallengeResponses(ChallengeResponses value) {
		this.challengeResponses = value;
	}

	public Credentials getCredentials() {
		return this.credentials;
	}

	public void setCredentials(Credentials value) {
		this.credentials = value;
	}

	public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator,
			Object object, EqualsStrategy strategy) {
		if (!(object instanceof InstitutionLogin)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		InstitutionLogin that = (InstitutionLogin) object;

		ChallengeResponses lhsChallengeResponses = getChallengeResponses();

		ChallengeResponses rhsChallengeResponses = that.getChallengeResponses();
		if (!strategy.equals(LocatorUtils.property(thisLocator,
				"challengeResponses", lhsChallengeResponses), LocatorUtils
				.property(thatLocator, "challengeResponses",
						rhsChallengeResponses), lhsChallengeResponses,
				rhsChallengeResponses)) {
			return false;
		}

		Credentials lhsCredentials = getCredentials();

		Credentials rhsCredentials = that.getCredentials();
		if (!strategy.equals(LocatorUtils.property(thisLocator, "credentials",
				lhsCredentials), LocatorUtils.property(thatLocator,
				"credentials", rhsCredentials), lhsCredentials, rhsCredentials)) {
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

		ChallengeResponses theChallengeResponses = getChallengeResponses();
		currentHashCode = strategy.hashCode(LocatorUtils.property(locator,
				"challengeResponses", theChallengeResponses), currentHashCode,
				theChallengeResponses);

		Credentials theCredentials = getCredentials();
		currentHashCode = strategy.hashCode(
				LocatorUtils.property(locator, "credentials", theCredentials),
				currentHashCode, theCredentials);

		return currentHashCode;
	}

	public int hashCode() {
		HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
		return hashCode(null, strategy);
	}
}