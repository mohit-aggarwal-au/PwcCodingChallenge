package com.pwc.codingchallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Valid
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressBook  {

    //Pattern check to discard special characters
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @Size(max = 100)
    @NotNull
    private String name;

    //Pattern check to allow only digits
    @Pattern(regexp = "^[0-9]*$")
    @Size(max = 10)
    private String phoneNumber;

}
