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
            AddressBookEntity entity = AddressBookEntity.builder().name(addressBook.getName())
                    .phoneNumber(addressBook.getPhoneNumber()).build();
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

    public List<String> findUniqueFriends(List<AddressBook> bookList) {

        SortedSet<String> uniqueSet = new TreeSet<>();

        if (!CollectionUtils.isEmpty(bookList)) {
            //Stream to filter out null and blank values and get unique set of names
            Set<String> nameList = bookList.stream().filter(filterValidAddressBookObject())
                    .map(AddressBook::getName).collect(Collectors.toSet());
            uniqueSet.addAll(nameList);
        }

        List<AddressBook> addressBooks = getAddressBookList();
        if (!CollectionUtils.isEmpty(addressBooks)) {
            //Stream to filter out null and blank values and get unique set of names
            Set<String> nameListFromDb = addressBooks.stream().filter(filterValidAddressBookObject())
                    .map(AddressBook::getName).collect(Collectors.toSet());
            uniqueSet.addAll(nameListFromDb);
        }

        List<String> uniqueList = uniqueSet.stream().collect(Collectors.toList());
        Collections.sort(uniqueList, String.CASE_INSENSITIVE_ORDER);
        return uniqueList;

    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private static Predicate<AddressBook> filterValidAddressBookObject() {
        return book -> book != null && !StringUtils.isBlank(book.getName());
    }


}
