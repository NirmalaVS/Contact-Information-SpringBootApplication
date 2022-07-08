package com.vaudience.contactinformation.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Contact_Basic_Info")
public class ContactBasicInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "prod-generator")
	@GenericGenerator(name = "prod-generator", strategy = "com.vaudience.contactinformation.contactIdGenerator.IdGenerator")
	@Column(name="Contact_Id")
	private String contactId;
	/**
	 * To set fullName of the contactperson
	 */
	private String fullName;

	/**
	 * To set DateOfBirth of the contactperson
	 */
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	/**
	 * To set ContactAddress of the contactperson
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", unique = true)
	private ContactAddress contactAddress;

	public ContactBasicInfo() {

	}

	/**
	 * @param contactId
	 * @param fullName
	 * @param dateOfBirth
	 * @param contactAddress
	 */
	public ContactBasicInfo(String contactId, String fullName, Date dateOfBirth, ContactAddress contactAddress) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	/**
	 * Getter method of FullName
	 * 
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Setter method of FullName
	 * 
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Getter method of DateOfBirth
	 * 
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Setter method of DateOfBirth
	 * 
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Getter method of ContactAddress
	 * 
	 * @return the contactAddress
	 */
	public ContactAddress getContactAddress() {
		return contactAddress;
	}

	/**
	 * Setter method of ContactAddress
	 * 
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(ContactAddress contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * To string method
	 */
	@Override
	public String toString() {
		return "ContactBasicInfo [contactId=" + contactId + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", contactAddress=" + contactAddress + "]";
	}

}
