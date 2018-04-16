package com.openLegacydeloitte.com.facades.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openLegacydeloitte.com.entities.Customer;
import com.openLegacydeloitte.com.exceptions.CustomerException;
import com.openLegacydeloitte.com.exceptions.IllegalArgumentCustomerException;
import com.openLegacydeloitte.com.exceptions.NotFoundException;
import com.openLegacydeloitte.com.exceptions.PaginationException;
import com.openLegacydeloitte.com.exceptions.ValidationException;
import com.openLegacydeloitte.com.facades.interfaces.UserFacade;
import com.openLegacydeloitte.com.repositories.CustomerDao;
import com.openLegacydeloitte.com.validation.ValidationChecklist;

/**
 * Service class for customer management. <br>
 * Ties in the business logic of application users.<br>
 * Commincates with DAO layer and validation logic.
 */
@Service
public class UserFacadeImpl implements UserFacade {
	private static final int PAGE_SIZE_LIMIT = 100;
	CustomerDao custDao;

	@Autowired
	public void setCustDao(CustomerDao custDao) {
		this.custDao = custDao;
	}

	/**
	 * Getter for a single Customer entity from database.<br>
	 * 
	 * @throws NotFoundException
	 *             if requested customer was not found.
	 */
	@Override
	@Transactional(readOnly = true)
	public Customer get(Long id) throws CustomerException {
		Customer cust = this.custDao.findCustomerById(id);

		if (cust == null) {
			throw new NotFoundException("Could not find a customer with id " + id + ".");
		}

		return cust;
	}

	/**
	 * Deletes a single Customer entity from database, if exists.
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		this.custDao.deleteById(id);
	}

	/**
	 * Creates a single customer entity in database.<br>
	 * Given customer must have a null ID property.<br>
	 * Delegates validation logic to checklist.
	 * 
	 * @return The same customer entity, with a database-generated ID value.
	 * @throws IllegalArgumentCustomerException
	 *             if customer already has an ID.
	 * @throws ValidationException
	 *             if validation checklist failed.
	 */
	@Override
	@Transactional
	public Customer create(Customer customer) throws CustomerException {
		if (customer.getId() != null) {
			throw new IllegalArgumentCustomerException("Cannot create new customer if customer already has ID value.");
		}
		ValidationChecklist.validateFields(customer);
		custDao.save(customer);

		return customer;
	}

	/**
	 * returns a list of customers, filtered by page parameters.
	 * 
	 * @throws PaginationException
	 *             if page exceeds limit (100).
	 * @throws IllegalArgumentException
	 *             if page size is 0 or below.
	 */
	// TODO: apply page validation.
	@Override
	@Transactional(readOnly = true)
	public List<Customer> getAll(Pageable page) throws CustomerException {
		if (page.getPageSize() > PAGE_SIZE_LIMIT) {
			throw new PaginationException("Page size too large, must not exceed " + PAGE_SIZE_LIMIT + ".");
		}
		return custDao.findAll(page).getContent();
	}

	/**
	 * Updates a single customer entity in database.<br>
	 * Given customer must not have a null ID property.<br>
	 * Delegates validation logic to checklist.
	 * 
	 * @throws CustomerException
	 *             if customer already has an ID.
	 * @throws ValidationException
	 *             if validation checklist failed.
	 */
	@Override
	@Transactional
	public void update(Customer customer) throws CustomerException {
		if (customer.getId() == null) {
			throw new IllegalArgumentCustomerException("Cannot update a customer without an ID value.");
		}
		if (this.custDao.findCustomerById(customer.getId()) == null) {
			throw new NotFoundException("Could not find customer.");

		}
		ValidationChecklist.validateFields(customer);
		this.custDao.save(customer);
	}

	/**
	 * returns the amount of pages of Customers in the database, as determined by
	 * page size.
	 * 
	 * @throws IllegalArgumentException
	 *             if page size is 0 or below.
	 * @throws PaginationException
	 *             if page size exceeds limit (100).
	 * 
	 * @see com.openLegacydeloitte.com.facades.interfaces.UserFacade#getPageCount(org.springframework.data.domain.Pageable)
	 */
	@Override
	@Transactional(readOnly = true)
	public int getPageCount(Pageable page) throws CustomerException {
		if (page.getPageSize() > PAGE_SIZE_LIMIT) {
			throw new PaginationException("Page size too large, must not exceed " + PAGE_SIZE_LIMIT + ".");
		}
		return this.custDao.findAll(page).getTotalPages();
	}

}
