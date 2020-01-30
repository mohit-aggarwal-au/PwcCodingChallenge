package com.pwc.codingchallenge.api;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address_book2")
public class AddressBook implements Comparable<AddressBook> {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Override
    public int compareTo(AddressBook addressBook){
        return this.getName().compareToIgnoreCase(addressBook.getName());
    }

}