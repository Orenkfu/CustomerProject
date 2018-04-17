package com.openLegacydeloitte.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

	private Long id;
	private String name;
	public String address;
	public String phoneNumber;

	public Customer(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Customer() {
		super();
	}

	public Customer(Long id) {
		super();
		this.id = id;
	}

	@GeneratedValue
	@Id
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "Automatically generated customer ID")
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	@ApiModelProperty(notes = "Customer Address", allowEmptyValue = false, required = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	@ApiModelProperty(notes = "Customer Name", allowEmptyValue = false, required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	@ApiModelProperty(notes = "Customer Phone Number, Accepting only valid israeli numbers.", allowEmptyValue = false, required = true)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

}
