package com.pwc.codingchallenge.repository;

import com.pwc.codingchallenge.repository.entity.AddressBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntity, String> {

}
