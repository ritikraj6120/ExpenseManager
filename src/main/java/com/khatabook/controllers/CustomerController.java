package com.khatabook.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.request.customer.AddCustomerDTO;
import com.khatabook.request.customer.UpdateCustomerRequestDTO;
import com.khatabook.request.customertransaction.AddCustomerTransactionDTO;
import com.khatabook.request.customertransaction.UpdateCustomerTransactionDTO;
import com.khatabook.response.customer.CustomCustomerResponse;
import com.khatabook.response.customer.CustomCustomerResponses;
import com.khatabook.response.customernetbalance.CustomCustomerNetBalanceResponses;
import com.khatabook.response.customertransaction.CustomCustomerTransactionResponse;
import com.khatabook.response.customertransaction.CustomCustomerTransactionResponses;
import com.khatabook.services.CustomerService;

@RestController
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/getcustomers")
	public ResponseEntity<CustomCustomerResponses> getCustomers(){
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomCustomerResponses customCustomerResponses=customerService.getCustomers(userId);
			return ResponseEntity.status(customCustomerResponses.getStatus_code()).body(customCustomerResponses);
		}
		catch(Exception e) {
			CustomCustomerResponses customCustomerResponses =new CustomCustomerResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerResponses);
		}
	}
	
	// ROUTE 2: Add a new Customer using: GET "/api/customer/addcustomers". Login required
	@PostMapping("/addcustomer")
	public ResponseEntity<CustomCustomerResponse> AddCustomer(@Valid @RequestBody AddCustomerDTO addCustomerDTO){
	try {
		String name= addCustomerDTO.getName();
		String phone=addCustomerDTO.getPhone();
		// Retrieve the userId from the security context
		AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());
		CustomCustomerResponse addedCustomer=customerService.addCustomer(name,phone,userId);
		return ResponseEntity.status(addedCustomer.getStatus_code()).body(addedCustomer);
	} 
	catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
		CustomCustomerResponse customCustomerResponse =new CustomCustomerResponse(500, "Error", "Internal Server Error", null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerResponse);
	}
}
	
	@PutMapping("/updatecustomer/{id}")
	public ResponseEntity<CustomCustomerResponse> UpdateCustomer
	(@PathVariable("id") UUID id, @Valid @RequestBody UpdateCustomerRequestDTO updateCustomerRequestDTO)
	{
		try {
			CustomCustomerResponse updatedCustomer = customerService.updateCustomer(id,updateCustomerRequestDTO);
			return ResponseEntity.status(updatedCustomer.getStatus_code()).body(updatedCustomer);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerResponse customCustomerResponse =new CustomCustomerResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerResponse);
		}
	}

	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<CustomCustomerResponse> DeleteCustomer(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomCustomerResponse deletedCustomer = customerService.deleteCustomer(id,userId);
			return ResponseEntity.status(deletedCustomer.getStatus_code()).body(deletedCustomer);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerResponse customCustomerResponse =new CustomCustomerResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerResponse);
		}
	}
	
	
	// Route 5 Get single customer
	@GetMapping("/getSingleCustomerDetail/{id}")
	public ResponseEntity<CustomCustomerResponse> getSingleCustomer(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomCustomerResponse customer = customerService.getCustomer(id,userId);
			return ResponseEntity.status(customer.getStatus_code()).body(customer);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerResponse customCustomerResponse =new CustomCustomerResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerResponse);
		}
	}

	


//	 Route 6:fetch transaction  using: get "/api/customer/getCustomerTransactions/". Login required
	@GetMapping("/getCustomerTransactions/{id}")
	public ResponseEntity<CustomCustomerTransactionResponses> getCustomerTransactions(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomCustomerTransactionResponses customerTransactionResponses = customerService.getCustomerTransactions(id,userId);
			return ResponseEntity.status(customerTransactionResponses.getStatus_code()).body(customerTransactionResponses);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerTransactionResponses customerTransactionResponses =new CustomCustomerTransactionResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerTransactionResponses);
		}
	}
	


	@PostMapping("/addCustomerTransaction/{id}")
	public ResponseEntity<CustomCustomerTransactionResponse> addCustomerTransactionController(@PathVariable("id") UUID id,
			@Valid @RequestBody AddCustomerTransactionDTO addCustomerTransactionDTO)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomCustomerTransactionResponse addedCustomerTransaction = 
					customerService.addCustomerTransaction(id,userId,addCustomerTransactionDTO);
			return ResponseEntity.status(addedCustomerTransaction.getStatus_code()).body(addedCustomerTransaction);
			} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerTransactionResponse addedCustomerTransaction =new CustomCustomerTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addedCustomerTransaction);
		}
	}
	
	@PutMapping("/updateCustomerTransaction/{transactionId}/{customerId}")
	public ResponseEntity<CustomCustomerTransactionResponse> updateCustomerTransactionController(
			@PathVariable("transactionId") UUID transactionId,
			@PathVariable("customerId") UUID customerId,
			@Valid @RequestBody UpdateCustomerTransactionDTO updateCustomerTransactionDTO)
	{
		try {
				// Retrieve the userId from the security context
				AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		        UUID userId = UUID.fromString(authentication.getName());
				CustomCustomerTransactionResponse updatedCustomerTransaction = 
						customerService.updateCustomerTransaction(transactionId,customerId,userId,updateCustomerTransactionDTO);
				return ResponseEntity.status(updatedCustomerTransaction.getStatus_code()).body(updatedCustomerTransaction);
			} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerTransactionResponse updatedCustomerTransaction =new CustomCustomerTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updatedCustomerTransaction);
		}
	}
	
	@DeleteMapping("/deleteCustomerTransaction/{transactionId}/{customerId}")
	public ResponseEntity<CustomCustomerTransactionResponse> deleteCustomerTransactionController(
			@PathVariable("transactionId") UUID transactionId,
			@PathVariable("customerId") UUID customerId)
	{
		try {
				// Retrieve the userId from the security context
				AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		        UUID userId = UUID.fromString(authentication.getName());
				CustomCustomerTransactionResponse deletedCustomerTransaction = 
						customerService.deleteCustomerTransaction(transactionId,customerId,userId);
				return ResponseEntity.status(deletedCustomerTransaction.getStatus_code()).body(deletedCustomerTransaction);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerTransactionResponse deletedCustomerTransaction =new CustomCustomerTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deletedCustomerTransaction);
		}
	}
	
	@GetMapping("/getCustomerBalance")
	public ResponseEntity<CustomCustomerNetBalanceResponses> getCustomerNetBalance()
	{
		try
		{
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
	        CustomCustomerNetBalanceResponses customCustomerNetBalanceResponses=
	        		customerService.getCustomerNetBalance(userId);
	        return ResponseEntity.status(customCustomerNetBalanceResponses.getStatus_code())
	        		.body(customCustomerNetBalanceResponses);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			CustomCustomerNetBalanceResponses customCustomerNetBalanceResponses =
					new CustomCustomerNetBalanceResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customCustomerNetBalanceResponses);
		}
	}
	
}
	