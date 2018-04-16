package com.openLegacydeloitte.com.validation;

import com.openLegacydeloitte.com.entities.Customer;

public class ValidationData {
	private String name;
	private String address;
	private String phoneNumber;

	public ValidationData(Customer customer) {
		this.name = customer.getName();
		this.address = customer.getAddress();
		this.phoneNumber = customer.getPhoneNumber();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CustomerData [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
}
