package com.crm.crm.payload;


import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class EmployeeDto {
    private Long id;


    @Size(min=3,message = "At least 3 chars")
    private String name;
    @Email
    private String emailId;
    @Size(min=10,max=20,message=" should be 10 digits")
    private String mobile;

    public @Size(min = 3, message = "At least 3 chars") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "At least 3 chars") String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Email String getEmailId() {
        return emailId;
    }

    public void setEmailId(@Email String emailId) {
        this.emailId = emailId;
    }

    public @Size(min = 10, max = 20, message = " should be 10 digits") String getMobile() {
        return mobile;
    }

    public void setMobile(@Size(min = 10, max = 20, message = " should be 10 digits") String mobile) {
        this.mobile = mobile;
    }
}
