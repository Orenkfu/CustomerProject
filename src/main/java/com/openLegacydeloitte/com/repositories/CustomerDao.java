package com.openLegacydeloitte.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openLegacydeloitte.com.entities.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

	Customer findCustomerById(Long id);

}
