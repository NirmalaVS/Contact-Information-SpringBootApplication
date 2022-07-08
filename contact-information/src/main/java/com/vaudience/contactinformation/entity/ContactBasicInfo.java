package com.vaudience.contactinformation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Contact_Basic_Info")
public class ContactBasicInfo {

	@Id
	@GenericGenerator(name = "sequence_contactId", strategy = "com.vaudience.contactinformation.contactIdGenerator.IdGenerator")
	@GeneratedValue(generator = "sequence_contactId")
	@Column(name = "ContactInfo_Id")
	private Long contactId;
	/**
	 * To set fullName of the contactperson
	 */
	@Column(name = "Full_Name")
	private String fullName;

	/**
	 * To set DateOfBirth of the contactperson
	 */
	@Column(name = "Date_Of_Birth")
	private Date dateOfBirth;

	/**
	 * To set ContactAddress of the contactperson
	 */
	@OneToOne
    @JoinColumn(name="Address_Id")
	private ContactAddress contactAddress;

	/**
	 * @return the contactId
	 */
	public Long getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(Long contactId) {
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

}
