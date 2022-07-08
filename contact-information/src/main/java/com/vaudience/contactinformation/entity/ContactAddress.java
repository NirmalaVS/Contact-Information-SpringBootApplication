package com.vaudience.contactinformation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for Address information
 * 
 * @author NirmalaSivakumar
 *
 */
@Entity
@Table(name="Contact_Address")
public class ContactAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Address_Id")
	private long addressId;

	/**
	 * To set the city name can be any city name
	 */
	@Column(name = "City_Name")
	private String city;

	/**
	 * To set the zip code can be any zip code but should contain only integer value
	 */
	@Column(name = "Zip_Code")
	private int zipCode;
	

	/**
	 * @return the addressId Getter method of addressId
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId Setter method of addressId
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the city Getter method to get city value
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city Setter method to set city value
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zipCode Getter method to get zip code
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set Setter method to set zip code
	 * 
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
