package com.khatabook.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khatabook.entities.Supplier;
import com.khatabook.entities.SupplierNetBalance;
import com.khatabook.entities.SupplierTransaction;
import com.khatabook.entities.User;
import com.khatabook.repository.SupplierNetBalanceRepository;
import com.khatabook.repository.SupplierRepository;
import com.khatabook.repository.SupplierTransactionRepository;
import com.khatabook.repository.UserRepository;
import com.khatabook.request.supplier.UpdateSupplierRequestDTO;
import com.khatabook.request.suppliertransaction.AddSupplierTransactionDTO;
import com.khatabook.request.suppliertransaction.UpdateSupplierTransactionDTO;
import com.khatabook.response.supplier.CustomSupplier;
import com.khatabook.response.supplier.CustomSupplierResponse;
import com.khatabook.response.supplier.CustomSupplierResponses;
import com.khatabook.response.suppliernetbalance.CustomSupplierNetBalance;
import com.khatabook.response.suppliernetbalance.CustomSupplierNetBalanceResponses;
import com.khatabook.response.suppliertransaction.CustomSupplierTransaction;
import com.khatabook.response.suppliertransaction.CustomSupplierTransactionResponse;
import com.khatabook.response.suppliertransaction.CustomSupplierTransactionResponses;

@Service
public class SupplierService {
	@Autowired
    private SupplierRepository supplierRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private SupplierTransactionRepository supplierTransactionRepository;
	
	@Autowired
	private SupplierNetBalanceRepository supplierNetBalanceRepository;
	
 	public CustomSupplierResponses getSuppliers(UUID userId){
		ArrayList<Supplier> suppliers = supplierRepository.findByUserId(userId);
		ArrayList<CustomSupplier> customSuppliers=new ArrayList<>();
		for (Supplier supplier : suppliers) {
			customSuppliers.add(new CustomSupplier(supplier.getId(),supplier.getName(),supplier.getPhone(),
					supplier.getUser().getId()));
		}
		CustomSupplierResponses customSupplierResponses = new CustomSupplierResponses(200,"Success",null,customSuppliers);
		return customSupplierResponses;
	}
	
	public boolean findByUserIdAndPhoneNo(UUID userId,String phone) {
		ArrayList<Supplier> supplier=supplierRepository.findByUserIdAndPhone(userId,phone);
		if(supplier.isEmpty())
			return false;
		return true;
	}
	
	
	public CustomSupplierResponse addSupplier(String name,String phone,UUID userId)
	{
		if(findByUserIdAndPhoneNo(userId, phone))
		{
		    return new CustomSupplierResponse(409, "Danger", "Customer already exists with give phone number", null);
		}
		Supplier supplier=new Supplier();
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
		supplier.setName(name);
		supplier.setPhone(phone);
		supplier.setUser(user);
		supplier = supplierRepository.save(supplier);
		CustomSupplier customSupplier = new CustomSupplier(supplier.getId(), supplier.getName(), 
				supplier.getPhone(),user.getId());
		return new CustomSupplierResponse(200, "Success", "Customer added", customSupplier);
	}
	
	
	public CustomSupplierResponse updateSupplier(UUID supplierId, UpdateSupplierRequestDTO updateSupplierRequestDTO)
	{
		String name=updateSupplierRequestDTO.getName();
		String phone=updateSupplierRequestDTO.getPhone();
		if(name ==null && phone==null)
		{
			return new CustomSupplierResponse(400, "Error", "Both name and phone can't be empty at a time", null);
		}
		Optional<Supplier> optionalSupplier=supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
			
			if(name!=null)
			{
				// same name exists do not proceed ahead
				if(supplier.getName()==name)
					return new CustomSupplierResponse(400, "danger", "Duplicate name not allowed", null);
				else supplier.setName(name);
			}
			if(phone!=null)
			{
				Optional<Supplier> supplierByPhoneNo=supplierRepository.findByPhone(phone);
				if(supplierByPhoneNo.isPresent()) 
				{
					// if new phone number exists with someone do not proceed ahead
					if(supplierByPhoneNo.get().getId() != supplier.getId())
					return new CustomSupplierResponse(409, "danger", "Supplier with given phone number already existed", null);
					// if id matches that means provided phone number actually exists in DB so duplicate phone number
					// provided by client.
					else return new CustomSupplierResponse(400, "danger", "Duplicate phone not allowed", null);
				}
				supplier.setPhone(phone);
			}
			supplierRepository.save(supplier);
			CustomSupplier customSupplier = new CustomSupplier(supplier.getId(), supplier.getName(), supplier.getPhone(),
			supplier.getUser().getId());
			return new CustomSupplierResponse(200, "Success", "Supplier details updated", customSupplier);
		}else {
			return new CustomSupplierResponse(404, "Error", "Supplier not found", null);
		}
	}
	
	
	
	public CustomSupplierResponse deleteSupplier(UUID supplierId, UUID userId)
	{
		Optional<Supplier> optionalSupplier=supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
			// Allow deletion only if user owns this customer
			if(!supplier.getUser().getId().equals(userId))
			{
				return new CustomSupplierResponse(403, "Error", "Not Authorized", null);
			}
			 supplierRepository.delete(supplier);
			 return new CustomSupplierResponse(200, "Success", "Supplier has been deleted", 
					 new CustomSupplier(supplier.getId(), supplier.getName(), supplier.getPhone(), userId));
		}else {
			return new CustomSupplierResponse(404, "Error", "Supplier not found", null);
		}
	}
	
	public CustomSupplierResponse getSupplier(UUID supplierId,UUID userId) {
		Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
			// Allow deletion only if user owns this customer
			if(!supplier.getUser().getId().equals(userId))
			{
				return new CustomSupplierResponse(403, "Error", "Not Authorized", null);
			}
			 return new CustomSupplierResponse(200, "Success", null,
					 new CustomSupplier(supplier.getId(), supplier.getName(), supplier.getPhone(), userId));
		}else {
			return new CustomSupplierResponse(404, "Error", "Supplier not found", null);
		}
	}
	
	public CustomSupplierTransactionResponses getSupplierTransactions(UUID supplierId,UUID userId) {
		Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
			// Allow deletion only if user owns this customer
			if(!supplier.getUser().getId().equals(userId))
			{
				return new CustomSupplierTransactionResponses(403, "Error", "Not Authorized", null);
			}
			
			List<SupplierTransaction> supplierTransactions = new ArrayList<SupplierTransaction>();
			supplierTransactions = supplierTransactionRepository.findBySupplierId(supplierId);
			ArrayList<CustomSupplierTransaction> customSupplierTransactions=new ArrayList<>();
			
			for (SupplierTransaction supplierTransaction : supplierTransactions) {
				customSupplierTransactions.add(
						new CustomSupplierTransaction(
								supplierTransaction.getId(),
								supplierTransaction.getTransaction(),
								supplierTransaction.isPayment(),
								supplierTransaction.getBillNo(),
								supplierTransaction.getBillDetails(),
								supplierTransaction.getCreatedDate(),
								supplierTransaction.getSupplier().getId()
								)
						);
			}
			CustomSupplierTransactionResponses customSupplierTransactionResponses = 
					new CustomSupplierTransactionResponses(200,"Success",null,customSupplierTransactions);
			return customSupplierTransactionResponses;
			
		}else {
			return new CustomSupplierTransactionResponses(404, "Error", "Customer not found", null);
		}
	}
	
	
	
   public CustomSupplierTransactionResponse addSupplierTransaction(UUID supplierId,UUID userId,
		   AddSupplierTransactionDTO addSupplierTransactionDTO) {
	   
		boolean isPayment=addSupplierTransactionDTO.isPayment();
		Optional<Supplier> optionalSupplier=supplierRepository.findById(supplierId);
		if(optionalSupplier.isPresent()) {
			Supplier supplier = optionalSupplier.get();
			SupplierTransaction supplierTransaction = addSupplierTransactionDTO.getSupplierTransaction();
			supplierTransaction.setSupplier(supplier);
			SupplierTransaction addedSupplierTransaction = supplierTransactionRepository.save(supplierTransaction);
			CustomSupplierTransaction customSupplierTransaction = 
					new CustomSupplierTransaction(
							addedSupplierTransaction.getId(),
							addedSupplierTransaction.getTransaction(),
							addedSupplierTransaction.isPayment(),
							addedSupplierTransaction.getBillNo(),
							addedSupplierTransaction.getBillDetails(),
							addedSupplierTransaction.getCreatedDate(),
							userId
							);
			Optional<User> user = userRepository.findById(userId);
			
			Optional<SupplierNetBalance> optionalSupplierNetBalance  = supplierNetBalanceRepository.
					findBySupplierId(supplierId);
				
			if(optionalSupplierNetBalance.isPresent())
			{
				SupplierNetBalance supplierNetBalance = optionalSupplierNetBalance.get();
				long netBalance = supplierNetBalance.getNetBalance();
				// have to check for overflow
				// agar maine supplier ko paisa diya to is payment me jayega
				// net balance batayega supplier ko aur kitna rupya dena hai
				if(isPayment)
				{
					netBalance-=addedSupplierTransaction.getTransaction();
				}
				// agar maine supplier se paisa le liya and paisa nhi diya 
				else
				{
					netBalance+=addedSupplierTransaction.getTransaction();
				}
				
				supplierNetBalance.setNetBalance(netBalance);
				supplierNetBalanceRepository.save(supplierNetBalance);
			}
			else
			{
				SupplierNetBalance supplierNetBalance  = new SupplierNetBalance();
				long netBalance=0;
				if(isPayment)
				{
					netBalance-=addedSupplierTransaction.getTransaction();
				}
				else
				{
					netBalance+=addedSupplierTransaction.getTransaction();
				}
				supplierNetBalance.setNetBalance(netBalance);
				supplierNetBalance.setSupplier(supplier);
				supplierNetBalance.setUser(user.get());
				supplierNetBalanceRepository.save(supplierNetBalance);
			}
			
			return new CustomSupplierTransactionResponse(200,"Success","Supplier Transaction added sucessfully",customSupplierTransaction);
		}
		else {
			return new CustomSupplierTransactionResponse(404, "Error", "Supplier not found", null);
		}
   }

	public CustomSupplierTransactionResponse updateSupplierTransaction(
			UUID transactionId,
			UUID supplierId,
			UUID userId, 
			UpdateSupplierTransactionDTO updateSupplierTransactionDTO) 
	{
		boolean isPayment=updateSupplierTransactionDTO.isPayment();
		Optional<Supplier> optionalSupplier=supplierRepository.findById(supplierId);
		if(optionalSupplier.isEmpty()) {
			return new CustomSupplierTransactionResponse(404, "Error", "Supplier not found", null);
		}
		Optional<SupplierTransaction> optionalSupplierTransaction = supplierTransactionRepository.findById(transactionId);  
		if(optionalSupplierTransaction.isEmpty())
		{
			return new CustomSupplierTransactionResponse(404, "Error", "Given Transaction not found", null);
		}
		SupplierTransaction supplierTransaction = optionalSupplierTransaction.get();
		Long currTranscation=supplierTransaction.getTransaction();
		Long transaction=updateSupplierTransactionDTO.getTransaction();
		String billNo=updateSupplierTransactionDTO.getBillNo();
		String billDetails=updateSupplierTransactionDTO.getBillDetails();
	    LocalDateTime transactionDate=updateSupplierTransactionDTO.getTransactionDate();
	    
	    if(transaction==null && billDetails==null && billNo ==null && transactionDate==null)
	    	return new CustomSupplierTransactionResponse(404, "Error", "At least one field required", null); 
	    
	    if(isPayment!=supplierTransaction.isPayment())
    	{
    		return new CustomSupplierTransactionResponse(404, "Error", "Invalid Transaction type", null);
    	}
	    
		if(transaction!=null)
		{
			supplierTransaction.setTransaction(transaction);
		}
		if(billDetails!=null)
		{
			supplierTransaction.setBillDetails(billDetails);
		}
		if(billNo!=null)
		{
			supplierTransaction.setBillNo(billNo);
		}
		if(transactionDate!=null)
		{
			supplierTransaction.setCreatedDate(transactionDate);
		}
		SupplierTransaction updatedSupplierTransaction = supplierTransactionRepository.save(supplierTransaction);
		Optional<SupplierNetBalance> optionalSupplierNetBalance  = supplierNetBalanceRepository.
																		findBySupplierId(supplierId);
			
		if(optionalSupplierNetBalance.isEmpty())
			return new CustomSupplierTransactionResponse(404, "Error", "No transaction of customer Found.", null); 
		SupplierNetBalance supplierNetBalance = optionalSupplierNetBalance.get();
		long netBalance = supplierNetBalance.getNetBalance();
		// have to check for overflow
		if(isPayment)
		{
			netBalance=netBalance+currTranscation-transaction;
		}
		else
		{
			netBalance=netBalance-currTranscation+transaction;
		}
		supplierNetBalance.setNetBalance(netBalance);
		supplierNetBalanceRepository.save(supplierNetBalance);
		CustomSupplierTransaction customSupplierTransaction = new CustomSupplierTransaction();
		customSupplierTransaction.setTransaction(updatedSupplierTransaction.getTransaction());
		customSupplierTransaction.setBillDetails(updatedSupplierTransaction.getBillDetails());
		customSupplierTransaction.setBillNo(updatedSupplierTransaction.getBillNo());
		customSupplierTransaction.setId(updatedSupplierTransaction.getId());
		customSupplierTransaction.setPayment(updatedSupplierTransaction.isPayment());
		customSupplierTransaction.setDate(updatedSupplierTransaction.getCreatedDate());
		customSupplierTransaction.setCustomerId(supplierId);
		return new CustomSupplierTransactionResponse(200,"Success",
				"Customer Transaction updated sucessfully",customSupplierTransaction);
	}

	public CustomSupplierTransactionResponse deleteSupplierTransaction(UUID transactionId, UUID supplierId,
			UUID userId) {
		Optional<Supplier> optionalSupplier=supplierRepository.findById(supplierId);
		if(optionalSupplier.isEmpty()) {
			return new CustomSupplierTransactionResponse(404, "Error", "Supplier not found", null);
		}
		Optional<SupplierTransaction> optionalSupplierTransaction = supplierTransactionRepository.findById(transactionId);  
		if(optionalSupplierTransaction.isEmpty())
		{
			return new CustomSupplierTransactionResponse(404, "Error", "Given Transaction not found", null);
		}
		SupplierTransaction supplierTransaction = optionalSupplierTransaction.get();
		Long currTranscation=supplierTransaction.getTransaction();
		boolean isPayment = supplierTransaction.isPayment();
		supplierTransactionRepository.deleteById(transactionId);
		Optional<SupplierNetBalance> optionalSupplierNetBalance  = supplierNetBalanceRepository.
				findBySupplierId(supplierId);

		if(optionalSupplierNetBalance.isEmpty())
			return new CustomSupplierTransactionResponse(404, "Error", "No transaction of supplier Found.", null); 
		SupplierNetBalance supplierNetBalance = optionalSupplierNetBalance.get();
		long netBalance = supplierNetBalance.getNetBalance();
		// have to check for overflow
		if(isPayment)
		{
			netBalance=netBalance+currTranscation;
		}
		else
		{
			netBalance=netBalance-currTranscation;
		}
		supplierNetBalance.setNetBalance(netBalance);
		supplierNetBalanceRepository.save(supplierNetBalance);
		return new CustomSupplierTransactionResponse(200,"Success","Supplier Transaction deleted sucessfully",null);
	}
	
	public CustomSupplierNetBalanceResponses getSupplierNetBalance(UUID userId) {
		 List<SupplierNetBalance> optionalSupplierNetBalanceList = supplierNetBalanceRepository.findByUserId(userId);
		 List<CustomSupplierNetBalance> arr= new ArrayList<CustomSupplierNetBalance>();
		 for(SupplierNetBalance i:optionalSupplierNetBalanceList )
		 {
			 arr.add(new CustomSupplierNetBalance(i.getId(),i.getNetBalance(),
					 i.getSupplier().getId(),i.getSupplier().getName()));		 
		 }
		 return new CustomSupplierNetBalanceResponses(200,"Success","Fetched supplier net balance",arr);
	}
}
	