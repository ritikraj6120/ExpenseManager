package com.khatabook.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khatabook.request.supplier.AddSupplierDTO;
import com.khatabook.request.supplier.UpdateSupplierRequestDTO;
import com.khatabook.request.suppliertransaction.AddSupplierTransactionDTO;
import com.khatabook.request.suppliertransaction.UpdateSupplierTransactionDTO;
import com.khatabook.response.supplier.CustomSupplierResponse;
import com.khatabook.response.supplier.CustomSupplierResponses;
import com.khatabook.response.suppliernetbalance.CustomSupplierNetBalanceResponses;
import com.khatabook.response.suppliertransaction.CustomSupplierTransactionResponse;
import com.khatabook.response.suppliertransaction.CustomSupplierTransactionResponses;
import com.khatabook.services.SupplierService;

@RestController
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/getsuppliers")
	public ResponseEntity<CustomSupplierResponses> getSuppliers(){
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
	        CustomSupplierResponses customSupplierResponses=supplierService.getSuppliers(userId);
			return ResponseEntity.status(customSupplierResponses.getStatus_code()).body(customSupplierResponses);
		}
		catch(Exception e) {
			CustomSupplierResponses customSupplierResponses =new CustomSupplierResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierResponses);
		}
	}
	
	// ROUTE 2: Add a new Supplier using: GET "/api/supplier/addsuppliers". Login required
	@PostMapping("/addsupplier")
	public ResponseEntity<CustomSupplierResponse> AddSupplier(@Valid @RequestBody AddSupplierDTO addSupplierDTO){
	try {
		String name= addSupplierDTO.getName();
		String phone=addSupplierDTO.getPhone();
		// Retrieve the userId from the security context
		AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());
		CustomSupplierResponse addedSupplier = supplierService.addSupplier(name,phone,userId);
		return ResponseEntity.status(addedSupplier.getStatus_code()).body(addedSupplier);
	} 
	catch (Exception e) {
		System.out.println(e);
		e.printStackTrace();
		CustomSupplierResponse customSupplierResponse =new CustomSupplierResponse(500, "Error", "Internal Server Error", null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierResponse);
	}
}
	
	@PutMapping("/updatesupplier/{id}")
	public ResponseEntity<CustomSupplierResponse> UpdateSupplier(@PathVariable("id") UUID id, @Valid @RequestBody UpdateSupplierRequestDTO updateSupplierRequestDTO)
	{
		try {
			CustomSupplierResponse updatedSupplier = supplierService.updateSupplier(id,updateSupplierRequestDTO);
			return ResponseEntity.status(updatedSupplier.getStatus_code()).body(updatedSupplier);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierResponse customSupplierResponse =new CustomSupplierResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierResponse);
		}
	}

	@DeleteMapping("/deletesupplier/{id}")
	public ResponseEntity<CustomSupplierResponse> DeleteSupplier(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomSupplierResponse deletedSupplier = supplierService.deleteSupplier(id,userId);
			return ResponseEntity.status(deletedSupplier.getStatus_code()).body(deletedSupplier);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierResponse customSupplierResponse =new CustomSupplierResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierResponse);
		}
	}
	
	
	// Route 5 Get single supplier
	@GetMapping("/getSingleSupplierDetail/{id}")
	public ResponseEntity<CustomSupplierResponse> getSingleSupplier(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomSupplierResponse supplier = supplierService.getSupplier(id,userId);
			return ResponseEntity.status(supplier.getStatus_code()).body(supplier);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierResponse customSupplierResponse =new CustomSupplierResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierResponse);
		}
	}


//	 Route 6:fetch transaction  using: get "/api/supplier/getSupplierTransactions/". Login required
	@GetMapping("/getSupplierTransactions/{id}")
	public ResponseEntity<CustomSupplierTransactionResponses> getSupplierTransactions(@PathVariable("id") UUID id)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomSupplierTransactionResponses supplierTransactionResponses = supplierService.getSupplierTransactions(id,userId);
			return ResponseEntity.status(supplierTransactionResponses.getStatus_code()).body(supplierTransactionResponses);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierTransactionResponses supplierTransactionResponses =new CustomSupplierTransactionResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(supplierTransactionResponses);
		}
	}
	


	@PostMapping("/addSupplierTransaction/{id}")
	public ResponseEntity<CustomSupplierTransactionResponse> addSupplierTransactionController(@PathVariable("id") UUID id,
			@Valid @RequestBody AddSupplierTransactionDTO addSupplierTransactionDTO)
	{
		try {
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
			CustomSupplierTransactionResponse addedSupplierTransaction = 
					supplierService.addSupplierTransaction(id,userId,addSupplierTransactionDTO);
			return ResponseEntity.status(addedSupplierTransaction.getStatus_code()).body(addedSupplierTransaction);
			} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierTransactionResponse addedSupplierTransaction =new CustomSupplierTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(addedSupplierTransaction);
		}
	}
	
	@PutMapping("/updateSupplierTransaction/{transactionId}/{supplierId}")
	public ResponseEntity<CustomSupplierTransactionResponse> updateSupplierTransactionController(
			@PathVariable("transactionId") UUID transactionId,
			@PathVariable("supplierId") UUID supplierId,
			@Valid @RequestBody UpdateSupplierTransactionDTO updateSupplierTransactionDTO)
	{
		try {
				// Retrieve the userId from the security context
				AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		        UUID userId = UUID.fromString(authentication.getName());
				CustomSupplierTransactionResponse updatedSupplierTransaction = 
						supplierService.updateSupplierTransaction(transactionId,supplierId,userId,updateSupplierTransactionDTO);
				return ResponseEntity.status(updatedSupplierTransaction.getStatus_code()).body(updatedSupplierTransaction);
			} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierTransactionResponse updatedSupplierTransaction =new CustomSupplierTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updatedSupplierTransaction);
		}
	}
	
	@DeleteMapping("/deleteSupplierTransaction/{transactionId}/{supplierId}")
	public ResponseEntity<CustomSupplierTransactionResponse> deleteSupplierTransactionController(
			@PathVariable("transactionId") UUID transactionId,
			@PathVariable("supplierId") UUID supplierId)
	{
		try {
				// Retrieve the userId from the security context
				AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		        UUID userId = UUID.fromString(authentication.getName());
				CustomSupplierTransactionResponse deletedSupplierTransaction = 
						supplierService.deleteSupplierTransaction(transactionId,supplierId,userId);
				return ResponseEntity.status(deletedSupplierTransaction.getStatus_code()).body(deletedSupplierTransaction);
		} 
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierTransactionResponse deletedSupplierTransaction =new CustomSupplierTransactionResponse(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deletedSupplierTransaction);
		}
	}
	
	@GetMapping("/getSupplierBalance")
	public ResponseEntity<CustomSupplierNetBalanceResponses> getSupplierNetBalance()
	{
		try
		{
			// Retrieve the userId from the security context
			AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	        UUID userId = UUID.fromString(authentication.getName());
	        CustomSupplierNetBalanceResponses customSupplierNetBalanceResponses=
	        		supplierService.getSupplierNetBalance(userId);
	        return ResponseEntity.status(customSupplierNetBalanceResponses.getStatus_code())
	        		.body(customSupplierNetBalanceResponses);
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			CustomSupplierNetBalanceResponses customSupplierNetBalanceResponses =
					new CustomSupplierNetBalanceResponses(500, "Error", "Internal Server Error", null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customSupplierNetBalanceResponses);
		}
	}
	
}
	