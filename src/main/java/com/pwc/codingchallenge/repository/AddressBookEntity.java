package com.pwc.codingchallenge.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "address_book5")
public class AddressBookEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

}
