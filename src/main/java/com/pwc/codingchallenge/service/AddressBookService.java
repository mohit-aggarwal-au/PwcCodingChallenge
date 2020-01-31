package com.pwc.codingchallenge.service;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import com.pwc.codingchallenge.repository.AddressBookEntity;
import com.pwc.codingchallenge.repository.AddressBookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public void saveAddressBook(AddressBook addressBook) {
        if (addressBook != null && !StringUtils.isBlank(addressBook.getName())) {
            AddressBookEntity entity = new AddressBookEntity();
            entity.setName(addressBook.getName());
            entity.setPhoneNumber(addressBook.getPhoneNumber());
            repository.save(entity);
        } else {
            throw new InvalidArgumentException("Request object is invalid");

        }
    }

    public List<AddressBook> getAddressBookList() {
        List<AddressBook> addressBookList = new ArrayList<>();
        List<AddressBookEntity> books = repository.findAll();
        if (!CollectionUtils.isEmpty(books)) {
            books.forEach(addressBookEntity -> {
                AddressBook book = new AddressBook(addressBookEntity.getName(), addressBookEntity.getPhoneNumber());
                addressBookList.add(book);
            });
            Collections.sort(addressBookList);
        }
        return addressBookList;

    }

    public Set<String> findUniqueFriends(List<AddressBook> bookList) {

        SortedSet<String> uniqueSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        if (!CollectionUtils.isEmpty(bookList)) {
            Set<String> nameList = bookList.stream().filter(filterValidAddressBookObject())
                    .map(AddressBook::getName).collect(Collectors.toSet());
            uniqueSet.addAll(nameList);
        }

        List<AddressBook> addressBooks = getAddressBookList();
        if (!CollectionUtils.isEmpty(addressBooks)) {
            Set<String> nameListFromDb = addressBooks.stream().filter(filterValidAddressBookObject())
                    .map(AddressBook::getName).collect(Collectors.toSet());
            uniqueSet.addAll(nameListFromDb);
        }
        return uniqueSet;

    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private static Predicate<AddressBook> filterValidAddressBookObject() {
        return book -> book != null && !StringUtils.isBlank(book.getName());
    }


}
