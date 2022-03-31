package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

    @JsonIgnore
    private int individualCustomerId;

    @NotNull
    @Length(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Length(min = 2, max = 20)
    private String lastName;

    @NotNull
    private long customerNumber;

    @NotNull
    @Length(min = 2, max = 11)
    private String nationalIdentity;

    @NotNull
    @Size(min = 2, max = 20)
    @Email(message = "This value is not email!")
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String password;


}
