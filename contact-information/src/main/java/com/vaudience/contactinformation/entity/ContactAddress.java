package com.vaudience.contactinformation.entity;

import java.io.Serializable;

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
@Table(name = "Contact_Address")
public class ContactAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Address_Id")
	private long addressId;

	/**
	 * To set the city name can be any city name
	 */
	private String cityName;

	/**
	 * To set the zip code can be any zip code but should contain only integer value
	 */
	@Column(name = "Zip_Code")
	private int zipCode;

	public ContactAddress() {

	}

	/**
	 * @param addressId
	 * @param city
	 * @param zipCode
	 */
	public ContactAddress(long addressId, String cityName, int zipCode) {
		super();
		this.addressId = addressId;
		this.cityName = cityName;
		this.zipCode = zipCode;
	}

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
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param city Setter method to set city value
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	/**
	 * To string method
	 */

	@Override
	public String toString() {
		return "ContactAddress [addressId=" + addressId + ", cityName=" + cityName + ", zipCode=" + zipCode + "]";
	}

}
