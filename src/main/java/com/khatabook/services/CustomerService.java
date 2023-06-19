package com.khatabook.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khatabook.entities.Customer;
import com.khatabook.entities.CustomerNetBalance;
import com.khatabook.entities.CustomerTransaction;
import com.khatabook.entities.User;
import com.khatabook.repository.CustomerNetBalanceRepository;
import com.khatabook.repository.CustomerRepository;
import com.khatabook.repository.CustomerTransactionRepository;
import com.khatabook.repository.UserRepository;
import com.khatabook.request.customer.UpdateCustomerRequestDTO;
import com.khatabook.request.customertransaction.AddCustomerTransactionDTO;
import com.khatabook.request.customertransaction.UpdateCustomerTransactionDTO;
import com.khatabook.response.customer.CustomCustomer;
import com.khatabook.response.customer.CustomCustomerResponse;
import com.khatabook.response.customer.CustomCustomerResponses;
import com.khatabook.response.customernetbalance.CustomCustomerNetBalance;
import com.khatabook.response.customernetbalance.CustomCustomerNetBalanceResponses;
import com.khatabook.response.customertransaction.CustomCustomerTransaction;
import com.khatabook.response.customertransaction.CustomCustomerTransactionResponse;
import com.khatabook.response.customertransaction.CustomCustomerTransactionResponses;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository customerRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private CustomerTransactionRepository customerTransactionRepository;
	
	@Autowired
	private CustomerNetBalanceRepository customerNetBalanceRepository;
	
 	public CustomCustomerResponses getCustomers(UUID userId){
		ArrayList<Customer> customers = customerRepository.findByUserId(userId);
		ArrayList<CustomCustomer> customCustomers=new ArrayList<>();
		for (Customer customer : customers) {
			customCustomers.add(new CustomCustomer(customer.getId(),customer.getName(),customer.getPhone(),
			customer.getUser().getId()));
		}
		CustomCustomerResponses customCustomerResponses = new CustomCustomerResponses(200,"Success",null,customCustomers);
		return customCustomerResponses;
	}
	
	public boolean findByUserIdAndPhoneNo(UUID userId,String phone) {
		ArrayList<Customer> customer=customerRepository.findByUserIdAndPhone(userId,phone);
		if(customer.isEmpty())
			return false;
		return true;
	}
	
	
	public CustomCustomerResponse addCustomer(String name,String phone,UUID userId)
	{
		if(findByUserIdAndPhoneNo(userId, phone))
		{
		    return new CustomCustomerResponse(409, "Danger", "Customer already exists with give phone number", null);
		}
		Customer customer=new Customer();
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
		customer.setName(name);
		customer.setPhone(phone);
		customer.setUser(user);
		customer = customerRepository.save(customer);
		CustomCustomer customCustomer = new CustomCustomer(customer.getId(), customer.getName(), 
															customer.getPhone(),user.getId());
		return new CustomCustomerResponse(200, "Success", "Customer added", customCustomer);
	}
	
	
	public CustomCustomerResponse updateCustomer(UUID customerId, UpdateCustomerRequestDTO updateCustomerRequestDTO)
	{
		String name=updateCustomerRequestDTO.getName();
		String phone=updateCustomerRequestDTO.getPhone();
		if(name ==null && phone==null)
		{
			return new CustomCustomerResponse(400, "Error", "Both name and phone can't be empty at a time", null);
		}
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			
			if(name!=null)
			{
				// same name exists do not proceed ahead
				if(customer.getName()==name)
					return new CustomCustomerResponse(400, "danger", "Duplicate name not allowed", null);
				else customer.setName(name);
			}
			if(phone!=null)
			{
				Optional<Customer> customerByPhoneNo=customerRepository.findByPhone(phone);
				if(customerByPhoneNo.isPresent()) 
				{
					// if new phone number exists with someone do not proceed ahead
					if(customerByPhoneNo.get().getId()!=customer.getId())
					return new CustomCustomerResponse(409, "danger", "Another Customer with given phone number already existed", null);
					// if id matches that means provided phone number actually exists in DB so duplicate phone number
					// provided by client.
					else return new CustomCustomerResponse(400, "danger", "Duplicate phone not allowed", null);
				}
				customer.setPhone(phone);
			}
			customerRepository.save(customer);
			CustomCustomer customCustomer = new CustomCustomer(customer.getId(), customer.getName(), customer.getPhone(),
			customer.getUser().getId());
			return new CustomCustomerResponse(200, "Success", "Customer details updated", customCustomer);
		}else {
			return new CustomCustomerResponse(404, "Error", "Customer not found", null);
		}
	}
	
	
	
	public CustomCustomerResponse deleteCustomer(UUID customerId, UUID userId)
	{
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			// Allow deletion only if user owns this customer
			if(!customer.getUser().getId().equals(userId))
			{
				return new CustomCustomerResponse(403, "Error", "Not Authorized", null);
			}
			 customerRepository.delete(customer);
			 return new CustomCustomerResponse(200, "Success", "Customer has been deleted", 
					 new CustomCustomer(customer.getId(), customer.getName(), customer.getPhone(), userId));
			 // will do it in future
			 // await CustomerTransactions.deleteMany({ customer: id });
			 // await customerNetBalance.deleteMany({ customer: id });
		}else {
			return new CustomCustomerResponse(404, "Error", "Customer not found", null);
		}
	}
	
	public CustomCustomerResponse getCustomer(UUID customerId,UUID userId) {
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			// Allow deletion only if user owns this customer
			if(!customer.getUser().getId().equals(userId))
			{
				return new CustomCustomerResponse(403, "Error", "Not Authorized", null);
			}
			 return new CustomCustomerResponse(200, "Success", null,
					 new CustomCustomer(customer.getId(), customer.getName(), customer.getPhone(), userId));
		}else {
			return new CustomCustomerResponse(404, "Error", "Customer not found", null);
		}
	}
	
	public CustomCustomerTransactionResponses getCustomerTransactions(UUID customerId,UUID userId) {
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			// Allow deletion only if user owns this customer
			if(!customer.getUser().getId().equals(userId))
			{
				return new CustomCustomerTransactionResponses(403, "Error", "Not Authorized", null);
			}
			
			List<CustomerTransaction> customerTransactions= new ArrayList<CustomerTransaction>();
			customerTransactions=customerTransactionRepository.findByCustomerId(customerId);
			ArrayList<CustomCustomerTransaction> customCustomerTransactions=new ArrayList<>();
			
			for (CustomerTransaction customerTransaction : customerTransactions) {
				customCustomerTransactions.add(
						new CustomCustomerTransaction(
								customerTransaction.getId(),
								customerTransaction.getTransaction(),
								customerTransaction.getIsBorrow(),
								customerTransaction.getBillNo(),
								customerTransaction.getBillDetails(),
								customerTransaction.getDate(),
								customerTransaction.getCustomer().getId()
								)
						);
			}
			CustomCustomerTransactionResponses customCustomerTransactionResponses = 
					new CustomCustomerTransactionResponses(200,"Success",null,customCustomerTransactions);
			return customCustomerTransactionResponses;
			
		}else {
			return new CustomCustomerTransactionResponses(404, "Error", "Customer not found", null);
		}
	}
	
	
	
   public CustomCustomerTransactionResponse addCustomerTransaction(UUID customerId,UUID userId,
		   AddCustomerTransactionDTO addCustomerTransactionDTO) {
	   
		boolean customerHasBorrowed=addCustomerTransactionDTO.isBorrow();
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			CustomerTransaction customerTransaction = addCustomerTransactionDTO.getCustomerTransaction();
			customerTransaction.setCustomer(customer);
			CustomerTransaction addedCustomerTransaction = customerTransactionRepository.save(customerTransaction);
			CustomCustomerTransaction customCustomerTransaction = 
					new CustomCustomerTransaction(
							addedCustomerTransaction.getId(),
							addedCustomerTransaction.getTransaction(),
							addedCustomerTransaction.getIsBorrow(),
							addedCustomerTransaction.getBillNo(),
							addedCustomerTransaction.getBillDetails(),
							addedCustomerTransaction.getDate(),
							userId
							);
			Optional<User> user = userRepository.findById(userId);
			
				Optional<CustomerNetBalance> optionalCustomerNetBalance  = customerNetBalanceRepository.
						findByCustomerId(customerId);
				
			if(optionalCustomerNetBalance.isPresent())
			{
				CustomerNetBalance customerNetBalance = optionalCustomerNetBalance.get();
				long netBalance = customerNetBalance.getNetBalance();
				// have to check for overflow
				if(customerHasBorrowed)
				{
					netBalance+=addedCustomerTransaction.getTransaction();
				}
				else
				{
					netBalance-=addedCustomerTransaction.getTransaction();
				}
				
				customerNetBalance.setNetBalance(netBalance);
				customerNetBalanceRepository.save(customerNetBalance);
			}
			else
			{
				CustomerNetBalance customerNetBalance  = new CustomerNetBalance();
				long netBalance=0;
				if(customerHasBorrowed)
				{
					netBalance+=addedCustomerTransaction.getTransaction();
				}
				else
				{
					netBalance-=addedCustomerTransaction.getTransaction();
				}
				customerNetBalance.setNetBalance(netBalance);
				customerNetBalance.setCustomer(customer);
				customerNetBalance.setUser(user.get());
				customerNetBalanceRepository.save(customerNetBalance);
			}
			
			return new CustomCustomerTransactionResponse(200,"Success",
					"Customer Transaction added sucessfully",customCustomerTransaction);
		}
		else {
			return new CustomCustomerTransactionResponse(404, "Error", "Customer not found", null);
		}
   }

	public CustomCustomerTransactionResponse updateCustomerTransaction(
			UUID transactionId,
			UUID customerId,
			UUID userId, 
			UpdateCustomerTransactionDTO updateCustomerTransactionDTO) 
	{
		boolean customerHasBorrowed=updateCustomerTransactionDTO.isBorrow();
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			return new CustomCustomerTransactionResponse(404, "Error", "Customer not found", null);
		}
		Optional<CustomerTransaction> optionalCustomerTransaction = customerTransactionRepository.findById(transactionId);  
		if(optionalCustomerTransaction.isEmpty())
		{
			return new CustomCustomerTransactionResponse(404, "Error", "Given Transaction not found", null);
		}
		CustomerTransaction customerTransaction = optionalCustomerTransaction.get();
		Long currTranscation=customerTransaction.getTransaction();
		Long transaction=updateCustomerTransactionDTO.getTransaction();
		String billNo=updateCustomerTransactionDTO.getBillNo();
		String billDetails=updateCustomerTransactionDTO.getBillDetails();
		boolean isBorrow = updateCustomerTransactionDTO.isBorrow();
	    LocalDateTime transactionDate=updateCustomerTransactionDTO.getTransactionDate();
	    
	    if(transaction==null && billDetails==null && billNo ==null && transactionDate==null)
	    	return new CustomCustomerTransactionResponse(404, "Error", "At least one field required", null); 
	    
	    if(isBorrow!=customerTransaction.getIsBorrow())
    	{
	    	System.out.println("value from request is "+ isBorrow);
	    	System.out.println("value from transaction database "+customerTransaction.getIsBorrow());
    		return new CustomCustomerTransactionResponse(404, "Error", "Invalid Transaction type", null);
    	}
	    
		if(transaction!=null)
		{
			customerTransaction.setTransaction(transaction);
		}
		if(billDetails!=null)
		{
			customerTransaction.setBillDetails(billDetails);
		}
		if(billNo!=null)
		{
			customerTransaction.setBillNo(billNo);
		}
		if(transactionDate!=null)
		{
			customerTransaction.setDate(transactionDate);
		}
		CustomerTransaction updatedCustomerTransaction = customerTransactionRepository.save(customerTransaction);
		Optional<CustomerNetBalance> optionalCustomerNetBalance  = customerNetBalanceRepository.
																		findByCustomerId(customerId);
			
		if(optionalCustomerNetBalance.isEmpty())
			return new CustomCustomerTransactionResponse(404, "Error", "No transaction of customer Found.", null); 
		CustomerNetBalance customerNetBalance = optionalCustomerNetBalance.get();
		long netBalance = customerNetBalance.getNetBalance();
		// have to check for overflow
		if(customerHasBorrowed)
		{
			netBalance=netBalance-currTranscation+transaction;
		}
		else
		{
			netBalance=netBalance+currTranscation-transaction;
		}
		customerNetBalance.setNetBalance(netBalance);
		customerNetBalanceRepository.save(customerNetBalance);
		CustomCustomerTransaction customCustomerTransaction = new CustomCustomerTransaction();
		customCustomerTransaction.setTransaction(updatedCustomerTransaction.getTransaction());
		customCustomerTransaction.setBillDetails(updatedCustomerTransaction.getBillDetails());
		customCustomerTransaction.setBillNo(updatedCustomerTransaction.getBillNo());
		customCustomerTransaction.setId(updatedCustomerTransaction.getId());
		customCustomerTransaction.setIsBorrow(updatedCustomerTransaction.getIsBorrow());
		customCustomerTransaction.setDate(updatedCustomerTransaction.getDate());
		customCustomerTransaction.setCustomerId(customerId);
		return new CustomCustomerTransactionResponse(200,"Success",
				"Customer Transaction updated sucessfully",customCustomerTransaction);
	}

	public CustomCustomerTransactionResponse deleteCustomerTransaction(UUID transactionId, UUID customerId,
			UUID userId) {
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			return new CustomCustomerTransactionResponse(404, "Error", "Customer not found", null);
		}
		Optional<CustomerTransaction> optionalCustomerTransaction = customerTransactionRepository.findById(transactionId);  
		if(optionalCustomerTransaction.isEmpty())
		{
			return new CustomCustomerTransactionResponse(404, "Error", "Given Transaction not found", null);
		}
		CustomerTransaction customerTransaction = optionalCustomerTransaction.get();
		Long currTranscation=customerTransaction.getTransaction();
		boolean isBorrow = customerTransaction.getIsBorrow();
		customerTransactionRepository.deleteById(transactionId);
		Optional<CustomerNetBalance> optionalCustomerNetBalance  = customerNetBalanceRepository.
				findByCustomerId(customerId);

		if(optionalCustomerNetBalance.isEmpty())
			return new CustomCustomerTransactionResponse(404, "Error", "No transaction of customer Found.", null); 
		CustomerNetBalance customerNetBalance = optionalCustomerNetBalance.get();
		long netBalance = customerNetBalance.getNetBalance();
		// have to check for overflow
		if(isBorrow)
		{
			netBalance=netBalance-currTranscation;
		}
		else
		{
			netBalance=netBalance+currTranscation;
		}
		customerNetBalance.setNetBalance(netBalance);
		customerNetBalanceRepository.save(customerNetBalance);
		return new CustomCustomerTransactionResponse(200,"Success","Customer Transaction deleted sucessfully",null);
	}
	
	

	public CustomCustomerNetBalanceResponses getCustomerNetBalance(UUID userId) {
		 List<CustomerNetBalance> optionalCustomerNetBalanceList = customerNetBalanceRepository.findByUserId(userId);
		 List<CustomCustomerNetBalance> arr= new ArrayList<CustomCustomerNetBalance>();
		 for(CustomerNetBalance i:optionalCustomerNetBalanceList )
		 {
			 arr.add(new CustomCustomerNetBalance(i.getId(),i.getNetBalance(),
					 i.getCustomer().getId(),i.getCustomer().getName()));		 
		 }
		 return new CustomCustomerNetBalanceResponses(200,"Success","Fetched customer net balance",arr);
	}
	
	
}
	