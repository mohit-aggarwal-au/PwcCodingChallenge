package com.pwc.codingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, String> {

}
