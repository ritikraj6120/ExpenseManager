package com.khatabook.request.supplier;

import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateSupplierRequestDTO {

	@Nullable
    @Size(min = 1, max = 25, message = "Name must be between 1 and 25 characters")
    private String name;

	@Nullable
	@Pattern(regexp="^\\+91\\d{10}$",message="phone number must start with +91 and 10 digits long")
    private String phone;

	public UpdateSupplierRequestDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
