package com.pwc.codingchallenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address_book")
public class AddressBookEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

}
