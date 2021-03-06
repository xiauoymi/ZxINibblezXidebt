/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.core.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * @author ralam1
 *
 */
@NamedQueries({
		@NamedQuery(name = "findNibblerByUsername", query = "from Nibbler n where n.nibblerDir.username = :username"),
		@NamedQuery(name = "findNibblerByInvitationCode", query = "from Nibbler n where n.invitationCode = :invitation_code"),
		@NamedQuery(name = "findNibblerByReferralCode", query = "from Nibbler n where n.referral = :referral and n.receiver is null"),
		@NamedQuery(name = "findContributorsByReceiver", query = "from Nibbler n where n.receiver.id = :receiver_id"),
		@NamedQuery(name = "findReceiverByUsername", query = "from Nibbler n where n.nibblerDir.username = :username"),
		@NamedQuery(name = "findByStatus", query = "from Nibbler n where n.nibblerDir.status in :status")

})

@Entity()
@Table(name = "nibbler"
// uniqueConstraints = {
// @UniqueConstraint(columnNames = {"activation_code", ""})
// }
)
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "nibbler_id")) })
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class Nibbler extends AbstractModel {

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "phone", nullable = true, length = 11)
	private String phone;

	@Column(name = "type", nullable = false, length = 50)
	private String type;

	@Column(name = "address_line_1", nullable = true, length = 100)
	private String addressLine1;

	@Column(name = "address_line_2", nullable = true, length = 100)
	private String addressLine2;

	@Column(name = "city", nullable = true, length = 100)
	private String city;

	@Column(name = "state", nullable = true, length = 100)
	private String state;

	@Column(name = "zip", nullable = true, length = 100)
	private Integer zip;

	@Column(name = "ext_acct_access_token", nullable = true, length = 50)
	private String extAccessToken;

	@OneToOne(mappedBy = "nibbler", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private NibblerDirectory nibblerDir;

	@OneToOne(mappedBy = "nibbler", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private NibblerPreference nibblerPreferences;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nibbler", orphanRemoval = true)
	private List<NibblerAccount> accounts;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "receiver_id", updatable = true, nullable = true)
	private Nibbler receiver;

	@Column(name = "invitation_code", nullable = true, length = 100)
	private Integer invitationCode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "receiver", orphanRemoval = true)
	private List<Nibbler> contributors;

	@Column(name = "referral_code", nullable = true, unique = false, length = 10)
	private String referral;
	
	private String dateOfBirth;
	private String ssn;

	/**
	 * @return the invitationCode
	 */
	public Integer getInvitationCode() {
		return invitationCode;
	}

	/**
	 * @param invitationCode
	 *            the invitationCode to set
	 */
	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
	}

	/**
	 * @return the contributors
	 */
	public List<Nibbler> getContributors() {
		if(contributors==null){
			contributors=new ArrayList<Nibbler>();
		}
		return contributors;
	}

	/**
	 * @param contributors
	 *            the contributors to set
	 */
	public void setContributors(List<Nibbler> contributors) {
		this.contributors = contributors;
	}

	/**
	 * @return the receiver
	 */
	public Nibbler getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(Nibbler receiver) {
		this.receiver = receiver;
	}

	public Nibbler() {
		super();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nibbler other = (Nibbler) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public Integer getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	/**
	 * @return the extAccessToken
	 */
	public String getExtAccessToken() {
		return extAccessToken;
	}

	/**
	 * @param extAccessToken
	 *            the extAccessToken to set
	 */
	public void setExtAccessToken(String extAccessToken) {
		this.extAccessToken = extAccessToken;
	}

	/**
	 * @return the nibblerDir
	 */
	public NibblerDirectory getNibblerDir() {
		return nibblerDir;
	}

	/**
	 * @param nibblerDir
	 *            the nibblerDir to set
	 */
	public void setNibblerDir(NibblerDirectory nibblerDir) {
		this.nibblerDir = nibblerDir;
	}

	/**
	 * @return the nibblerPreferences
	 */
	public NibblerPreference getNibblerPreferences() {
		if(nibblerPreferences==null){
			nibblerPreferences= new NibblerPreference();
			nibblerPreferences.setCreatedTs(new Date());
			nibblerPreferences.setCreatedUser(this.getCreatedUser());
		}
		return nibblerPreferences;
	}

	/**
	 * @param nibblerPreferences
	 *            the nibblerPreferences to set
	 */
	public void setNibblerPreferences(NibblerPreference nibblerPreferences) {
		this.nibblerPreferences = nibblerPreferences;
	}

	/**
	 * @return the accounts
	 */
	public List<NibblerAccount> getAccounts() {
		if (accounts == null)
			accounts = new ArrayList<>();
		return accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<NibblerAccount> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getReferral() {
		return referral;
	}

	public void setReferral(String referral) {
		this.referral = referral;
	}

	public void addAccount(NibblerAccount account) {
		if (account != null && !getAccounts().contains(account)) {
			Optional<NibblerAccount> old=getAccounts().stream().filter(a->{return a.getUseForpayoff().equals(account.getUseForpayoff());}).findFirst();
			if(old.isPresent()){
				System.out.println("old.get().getId()="+ old.get().getId());
				System.out.println("b size="+ getAccounts().size());
				NibblerAccount oldAccount=old.get();
				oldAccount.setNibbler(null);
				getAccounts().remove(old.get());
				System.out.println("a size="+ getAccounts().size());
			}
			getAccounts().add(account);
		}
	}

	public boolean isLinkedWith(String id) {
		return getAccounts().stream().anyMatch(a -> {
			if (a != null && a.getInstitution() != null && a.getInstitution().getSupportedInstitution()!=null && a.getInstitution().getSupportedInstitution().getExternalId()!=null)
				return a.getInstitution().getSupportedInstitution().getExternalId().equals(id);
			else
				return false;
		});

	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	

}