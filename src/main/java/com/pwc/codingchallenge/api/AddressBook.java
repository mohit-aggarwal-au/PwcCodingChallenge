package com.pwc.codingchallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Valid
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressBook implements Comparable<AddressBook> {

    @NotNull
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @Size(max = 200)
    private String name;

    @Pattern(regexp = "^[0-9]*$")
    @Size(max = 10)
    private String phoneNumber;

    @Override
    public int compareTo(AddressBook addressBook){
        return this.getName().compareToIgnoreCase(addressBook.getName());
    }

}
