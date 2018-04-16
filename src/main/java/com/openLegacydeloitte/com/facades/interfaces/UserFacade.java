package com.openLegacydeloitte.com.facades.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.openLegacydeloitte.com.entities.Customer;
import com.openLegacydeloitte.com.exceptions.CustomerException;

public interface UserFacade {
	Customer get(Long id) throws CustomerException;

	void delete(Long id);

	Customer create(Customer customer) throws CustomerException;

	void update(Customer customer) throws CustomerException;

	List<Customer> getAll(Pageable page) throws CustomerException;

	int getPageCount(Pageable page) throws CustomerException;

}
