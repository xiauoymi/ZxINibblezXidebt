package com.nibbledebt.intuit.cad.util;

import com.nibbledebt.intuit.cad.exception.SamlAssertionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.AttributeValue;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.core.impl.AssertionMarshaller;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.XMLObjectBuilder;
import org.opensaml.xml.XMLObjectBuilderFactory;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.Signer;
import org.opensaml.xml.signature.impl.SignatureBuilder;
import org.opensaml.xml.signature.impl.SignatureImpl;
import org.opensaml.xml.util.XMLHelper;
import org.w3c.dom.Element;

public class SAML2AssertionGenerator {
	private static XMLObjectBuilderFactory builderFactory;

	public String generateAssertion(SAMLAssertionData samlAssertionData)
			throws SamlAssertionException {
		Assertion assertion = buildAssertion(samlAssertionData);
		try {
			AssertionMarshaller assertionmarshaller = new AssertionMarshaller();
			Element element = assertionmarshaller.marshall(assertion);
			return XMLHelper.nodeToString(element);
		} catch (Exception e) {
			throw new SamlAssertionException(e);
		}
	}

	public String generateSignedAssertion(SAMLAssertionData samlAssertionData,
			SAMLCredentials samlCredentials) throws SamlAssertionException {
		Element element = null;
		try {
			Assertion assertion = buildAssertion(samlAssertionData);
			SignatureBuilder signaturebuilder = (SignatureBuilder) getSAMLBuilder()
					.getBuilder(Signature.DEFAULT_ELEMENT_NAME);
			SignatureImpl signatureimpl = signaturebuilder.buildObject();
			BasicX509Credential basicx509credential = samlCredentials
					.getX509Credential();
			signatureimpl.setSigningCredential(basicx509credential);
			signatureimpl
					.setSignatureAlgorithm("http://www.w3.org/2000/09/xmldsig#rsa-sha1");
			signatureimpl
					.setCanonicalizationAlgorithm("http://www.w3.org/2001/10/xml-exc-c14n#");
			assertion.setSignature(signatureimpl);
			ArrayList arraylist = new ArrayList();
			arraylist.add(signatureimpl);
			element = Configuration.getMarshallerFactory()
					.getMarshaller(assertion).marshall(assertion);
			Signer.signObject(signatureimpl);
		} catch (Exception e) {
			throw new SamlAssertionException(e);
		}
		return XMLHelper.nodeToString(element);
	}

	protected Assertion buildAssertion(SAMLAssertionData samlAssertionData)
			throws SamlAssertionException {
		try {
			DateTime datetime = samlAssertionData.getAssertionTime();
			String samlId = samlAssertionData.getSamlId();
			Subject subject = createSubject(datetime,
					samlAssertionData.getSubject(),
					samlAssertionData.getConfirmationMethod(),
					samlAssertionData.getTokenLifetimeMS(),
					samlAssertionData.getRecipient());

			AuthnStatement authnstatement = createAuthnStatement(
					samlAssertionData.getAuthTime(),
					samlAssertionData.getAuthenticationContext(), samlId);

			Map map = samlAssertionData.getAttributes();
			AttributeStatement attributestatement = null;
			if ((map != null) && (map.size() > 0)) {
				attributestatement = createAttributes(map);
			}
			Conditions conditions = createConditions(datetime,
					samlAssertionData.getAudienceRestriction(),
					samlAssertionData.getToleranceMS(),
					samlAssertionData.getTokenLifetimeMS());

			SAMLObjectBuilder samlobjectbuilder = (SAMLObjectBuilder) getSAMLBuilder()
					.getBuilder(Issuer.DEFAULT_ELEMENT_NAME);
			Issuer issuer = (Issuer) samlobjectbuilder.buildObject();
			issuer.setValue(samlAssertionData.getIssuer());
			SAMLObjectBuilder samlobjectbuilder1 = (SAMLObjectBuilder) getSAMLBuilder()
					.getBuilder(Assertion.DEFAULT_ELEMENT_NAME);
			Assertion assertion = (Assertion) samlobjectbuilder1.buildObject();
			assertion.setID(samlAssertionData.getSamlId());
			assertion.setIssueInstant(datetime);
			assertion.setVersion(SAMLVersion.VERSION_20);
			assertion.setIssuer(issuer);
			assertion.setSubject(subject);
			assertion.setConditions(conditions);
			assertion.getAuthnStatements().add(authnstatement);
			if (attributestatement != null) {
				assertion.getAttributeStatements().add(attributestatement);
			}
			return assertion;
		} catch (Exception e) {
			throw new SamlAssertionException(e);
		}
	}

	protected XMLObjectBuilderFactory getSAMLBuilder()
			throws SamlAssertionException {
		try {
			if (builderFactory == null) {
				DefaultBootstrap.bootstrap();
				builderFactory = Configuration.getBuilderFactory();
			}
		} catch (Exception e) {
			throw new SamlAssertionException(e);
		}
		return builderFactory;
	}

	protected Subject createSubject(DateTime dateTime, String samlSubject,
			String confirmationMethod, int tokenLifeTime, String samlRecipient)
			throws SamlAssertionException {
		Subject subject = null;
		try {
			SAMLObjectBuilder samlobjectbuilder = (SAMLObjectBuilder) getSAMLBuilder()
					.getBuilder(NameID.DEFAULT_ELEMENT_NAME);
			NameID nameid = (NameID) samlobjectbuilder.buildObject();
			nameid.setValue(samlSubject);
			nameid.setFormat("urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");
			SAMLObjectBuilder samlobjectbuilder1 = (SAMLObjectBuilder) getSAMLBuilder()
					.getBuilder(Subject.DEFAULT_ELEMENT_NAME);
			subject = (Subject) samlobjectbuilder1.buildObject();
			subject.setNameID(nameid);
			if (confirmationMethod != null) {
				SAMLObjectBuilder samlobjectbuilder2 = (SAMLObjectBuilder) getSAMLBuilder()
						.getBuilder(SubjectConfirmation.DEFAULT_ELEMENT_NAME);
				SubjectConfirmation subjectconfirmation = (SubjectConfirmation) samlobjectbuilder2
						.buildObject();
				subjectconfirmation.setMethod(confirmationMethod);
				if (samlRecipient != null) {
					SAMLObjectBuilder samlobjectbuilder3 = (SAMLObjectBuilder) getSAMLBuilder()
							.getBuilder(
									SubjectConfirmationData.DEFAULT_ELEMENT_NAME);

					SubjectConfirmationData subjectconfirmationdata = (SubjectConfirmationData) samlobjectbuilder3
							.buildObject();
					subjectconfirmationdata.setNotBefore(dateTime);
					subjectconfirmationdata.setNotOnOrAfter(dateTime
							.plusMillis(tokenLifeTime));
					subjectconfirmationdata.setRecipient(samlRecipient);
					subjectconfirmation
							.setSubjectConfirmationData(subjectconfirmationdata);
				}
				subject.getSubjectConfirmations().add(subjectconfirmation);
			}
		} catch (Exception ex) {
			throw new SamlAssertionException(ex.getMessage(), ex);
		}
		return subject;
	}

	protected Conditions createConditions(DateTime dateTime,
			String restrictionInfo, int tolerance, int lifeTime)
			throws SamlAssertionException {
		SAMLObjectBuilder samlobjectbuilder = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(AudienceRestriction.DEFAULT_ELEMENT_NAME);
		SAMLObjectBuilder samlobjectbuilder1 = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(Audience.DEFAULT_ELEMENT_NAME);
		AudienceRestriction audiencerestriction = (AudienceRestriction) samlobjectbuilder
				.buildObject();
		Audience audience = (Audience) samlobjectbuilder1.buildObject();
		audience.setAudienceURI(restrictionInfo);
		audiencerestriction.getAudiences().add(audience);
		SAMLObjectBuilder samlobjectbuilder2 = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(Conditions.DEFAULT_ELEMENT_NAME);
		Conditions conditions = (Conditions) samlobjectbuilder2.buildObject();
		conditions.setNotBefore(dateTime.minusMillis(tolerance));
		conditions.setNotOnOrAfter(dateTime.plusMillis(lifeTime));
		conditions.getConditions().add(audiencerestriction);
		return conditions;
	}

	protected AuthnStatement createAuthnStatement(DateTime dateTime,
			String authenticationContext, String samlId)
			throws SamlAssertionException {
		SAMLObjectBuilder samlobjectbuilder = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(AuthnStatement.DEFAULT_ELEMENT_NAME);
		AuthnStatement authnstatement = (AuthnStatement) samlobjectbuilder
				.buildObject();
		SAMLObjectBuilder samlobjectbuilder1 = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(AuthnContext.DEFAULT_ELEMENT_NAME);
		AuthnContext authncontext = (AuthnContext) samlobjectbuilder1
				.buildObject();
		SAMLObjectBuilder samlobjectbuilder2 = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(AuthnContextClassRef.DEFAULT_ELEMENT_NAME);
		AuthnContextClassRef authncontextclassref = (AuthnContextClassRef) samlobjectbuilder2
				.buildObject();
		authncontextclassref.setAuthnContextClassRef(authenticationContext);
		authncontext.setAuthnContextClassRef(authncontextclassref);
		authnstatement.setAuthnInstant(dateTime);
		authnstatement.setSessionIndex(samlId);
		authnstatement.setAuthnContext(authncontext);
		return authnstatement;
	}

	protected AttributeStatement createAttributes(Map<String, String> map)
			throws SamlAssertionException {
		SAMLObjectBuilder samlobjectbuilder = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(AttributeStatement.DEFAULT_ELEMENT_NAME);
		AttributeStatement attributestatement = (AttributeStatement) samlobjectbuilder
				.buildObject();
		SAMLObjectBuilder samlobjectbuilder1 = (SAMLObjectBuilder) getSAMLBuilder()
				.getBuilder(Attribute.DEFAULT_ELEMENT_NAME);
		XMLObjectBuilder xmlobjectbuilder = getSAMLBuilder().getBuilder(
				XSString.TYPE_NAME);
		Set set = map.keySet();
		Attribute attribute;
		for (Iterator iterator = set.iterator(); iterator.hasNext(); attributestatement
				.getAttributes().add(attribute)) {
			String s = (String) iterator.next();
			String s1 = (String) map.get(s);
			attribute = (Attribute) samlobjectbuilder1.buildObject();
			XSString xsstring = (XSString) xmlobjectbuilder.buildObject(
					AttributeValue.DEFAULT_ELEMENT_NAME, XSString.TYPE_NAME);
			xsstring.setValue(s1);
			attribute.setName(s);
			attribute.getAttributeValues().add(xsstring);
		}

		return attributestatement;
	}
}