package com.pwc.coding.service;

import com.pwc.coding.api.AddressBook;
import com.pwc.coding.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    public void saveAddressBook(AddressBook addressBook) {
        System.out.println("address details are:" + addressBook.getName() + addressBook.getPhoneNumber());
        repository.save(addressBook);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public List<AddressBook> getAddressBook() {

        List<AddressBook> books = repository.findAll();
        Collections.sort(books);
        return books;
//        List<AddressBook> books = new ArrayList<>();
//
//        AddressBook book = new AddressBook();
//        book.setName("rachel");
//        book.setPhoneNumber("12345");
//        books.add(book);
//        book = new AddressBook();
//        book.setName("chuchu");
//        book.setPhoneNumber("23930");
//        books.add(book);
//        book = new AddressBook();
//        book.setName("mike");
//        book.setPhoneNumber("23930");
//        books.add(book);
//        book = new AddressBook();
//        book.setName("alpha");
//        book.setPhoneNumber("23930");
//        books.add(book);
//        book = new AddressBook();
//        book.setName("mike");
//        book.setPhoneNumber("23931");
//        books.add(book);
//        Collections.sort(books);
//        return books;

    }

    public Set<String> findUniqueFriends(List<AddressBook> bookList) {

        Set<String> nameList = bookList.stream().map(AddressBook::getName).collect(Collectors.toSet());
        List<AddressBook> addressBooks = getAddressBook();
        Set<String> nameListFromDb = addressBooks.stream().map(AddressBook::getName).collect(Collectors.toSet());
        SortedSet<String> uniqueSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        uniqueSet.addAll(nameList);
        uniqueSet.addAll(nameListFromDb);

        return uniqueSet;

    }
}
