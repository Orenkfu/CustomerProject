package com.openLegacydeloitte.com.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openLegacydeloitte.com.Message;
import com.openLegacydeloitte.com.entities.Customer;
import com.openLegacydeloitte.com.exceptions.CustomerException;
import com.openLegacydeloitte.com.exceptions.IllegalArgumentCustomerException;
import com.openLegacydeloitte.com.exceptions.NotFoundException;
import com.openLegacydeloitte.com.facades.interfaces.UserFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "customerresource", description = "CRUD methods for managing customers.")
@RequestMapping("/api")
public class UserResource {

	private static final String DEFAULTPG = "0";
	private static final String DEFAULTPGSIZE = "10";

	private UserFacade facade;

	@Autowired
	public void setFacade(UserFacade facade) {
		this.facade = facade;
	}

	@ApiOperation(value = "Read a single customer's properties.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully retrieved customer."),
			@ApiResponse(code = 404, message = "Could not find customer."),
			@ApiResponse(code = 500, message = "Internal error.") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "/customer/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) throws CustomerException {
		return facade.get(id);

	}

	@ApiOperation(value = "Create a new customer. Sent Customer must not have an ID value.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully created customer."),
			@ApiResponse(code = 400, message = "Could not perform, illegal request."),
			@ApiResponse(code = 500, message = "Internal error.") })
	@RequestMapping(path = "/customer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@RequestBody Customer customer) throws CustomerException {
		return facade.create(customer);

	}

	@ApiOperation(value = "Delete a customer.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully deleted customer."),
			@ApiResponse(code = 500, message = "Internal error.") })
	@RequestMapping(method = RequestMethod.DELETE, path = "/customer/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		facade.delete(id);
	}

	@ApiOperation(value = "Update an existing customer's properties. Sent Customer must have an ID value that exists in DB.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully updated customer."),
			@ApiResponse(code = 400, message = "Could not perform, illegal request."),
			@ApiResponse(code = 404, message = "Could not find customer."),
			@ApiResponse(code = 500, message = "Internal Error.") })
	@RequestMapping(path = "/customer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@RequestBody Customer customer) throws CustomerException {
		System.out.println("updating customer: " + customer);
		facade.update(customer);
	}

	@ApiOperation(value = "Get a list of all existing customers by page.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succesfully retrieved customers."),
			@ApiResponse(code = 400, message = "Could not perform, illegal request."),
			@ApiResponse(code = 500, message = "Internal Error.")

	})
	@RequestMapping(method = RequestMethod.GET, path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer[] getAllCustomers(@RequestParam(defaultValue = DEFAULTPG, value = "page") int page,
			@RequestParam(defaultValue = DEFAULTPGSIZE, value = "size") int size) throws CustomerException {
		return facade.getAll(PageRequest.of(page, size)).toArray(new Customer[0]);

	}

	@ApiOperation(value = "Get the total amount of pages based on page size.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucessfully retrieved total pages."),
			@ApiResponse(code = 400, message = "Cannot perform, illegal request."),
			@ApiResponse(code = 500, message = "Internal error.") })
	@RequestMapping(method = RequestMethod.GET, path = "/customer/pages", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getPageCount(@RequestParam(defaultValue = DEFAULTPGSIZE, value = "size") int size)
			throws CustomerException {
		return facade.getPageCount(PageRequest.of(1, size));

	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Message handleException(IllegalArgumentCustomerException e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Illegal request, could not perform.";
		}
		return new Message(message);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Message handleException(NotFoundException e) {
		String message = e.getMessage();
		if (message == null) {
			message = "Resource could not be found.";
		}
		return new Message(message);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Message handleException(Exception e) {
		// e.printStackTrace();
		String message = e.getMessage();
		if (message == null) {
			message = "A technical error has occurred.";
		}
		return new Message(message);
	}
}
