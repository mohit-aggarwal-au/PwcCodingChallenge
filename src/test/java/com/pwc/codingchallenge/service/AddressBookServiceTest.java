package com.pwc.codingchallenge.service;

import com.pwc.codingchallenge.api.AddressBook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddressBookServiceTest {

    private AddressBookService service = new AddressBookService();

    @Test
    public void checkFraudulentTransactions_withValidValues_returnsSuccess() {

        List<AddressBook> books = new ArrayList<>();

        Set<String> uniqueList = service.findUniqueFriends(books);

        System.out.print(uniqueList);

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

}
