package com.pwc.coding.repository;

import com.pwc.coding.api.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public interface AddressBookRepository extends JpaRepository<AddressBook, String> {

}
