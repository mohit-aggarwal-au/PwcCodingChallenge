package com.pwc.codingchallenge.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "address_book2")
@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressBook implements Comparable<AddressBook> {

    @Id
    @NotNull
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @Size(max = 200)
    @Column(name="name")
    private String name;

    @Pattern(regexp = "^[0-9]*$")
    @Size(max = 10)
    @Column(name="phoneNumber")
    private String phoneNumber;

    @Override
    public int compareTo(AddressBook addressBook){
        return this.getName().compareToIgnoreCase(addressBook.getName());
    }

}
