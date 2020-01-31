package com.pwc.codingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, String> {

}
