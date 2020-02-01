package com.pwc.codingchallenge.service;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import com.pwc.codingchallenge.repository.AddressBookRepository;
import com.pwc.codingchallenge.repository.entity.AddressBookEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public void saveAddressBook(AddressBook addressBook) {
        if (addressBook != null && !StringUtils.isBlank(addressBook.getName())) {
            AddressBookEntity entity = AddressBookEntity.builder().name(addressBook.getName())
                    .phoneNumber(addressBook.getPhoneNumber()).build();
            repository.save(entity);
        } else {
            throw new InvalidArgumentException("Request object is invalid");

        }
    }

    public List<AddressBook> getDbAddressBookList() {
        List<AddressBook> addressBookList = new ArrayList<>();
        List<AddressBookEntity> books = repository.findAll();
        if (!CollectionUtils.isEmpty(books)) {
            books.forEach(addressBookEntity -> {
                AddressBook book = AddressBook.builder().name(addressBookEntity.getName())
                        .phoneNumber(addressBookEntity.getPhoneNumber()).build();
                addressBookList.add(book);
            });

            //Comparator to sort AddressBook object as per name.
            Comparator<AddressBook> addressBookComparator = Comparator.comparing(AddressBook::getName,
                    Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));

            Collections.sort(addressBookList, addressBookComparator);
        }
        return addressBookList;

    }


    public Set<String> findUniqueFriends(List<AddressBook> bookList) {

        Set<String> nameSet = getSetOfUniqueNames(bookList);
        Set<String> nameSetFromDb = getSetOfUniqueNames(getDbAddressBookList());

        if (CollectionUtils.isEmpty(bookList)) {
            //If input bookList is empty, then return set of names from database
            return nameSetFromDb;
        }

        if (CollectionUtils.isEmpty(getDbAddressBookList())) {
            //If namesFromDbList is empty, then return set of input names
            return nameSet;
        }
        //Return concatenation of complement values from both sets
        return Stream.concat(nameSet.stream().filter(filterComplementValues(nameSetFromDb)),
                nameSetFromDb.stream().filter(filterComplementValues(nameSet)))
                .collect(Collectors.toSet());

    }

    private Set<String> getSetOfUniqueNames(List<AddressBook> addressBooks) {
        Set<String> setOfUniqueNames = new HashSet<>();
        if (!CollectionUtils.isEmpty(addressBooks)) {
            setOfUniqueNames = addressBooks.stream().filter(filterValidAddressBookObject()).map(AddressBook::getName)
                    .collect(Collectors.toSet());
        }
        return setOfUniqueNames;
    }


    public void deleteAll() {
        repository.deleteAll();
    }


    private static Predicate<String> filterComplementValues(Set<String> stringSet) {
        return stringValue -> !stringSet.contains(stringValue);
    }


    private static Predicate<AddressBook> filterValidAddressBookObject() {
        return book -> book != null && !StringUtils.isBlank(book.getName());
    }

}
