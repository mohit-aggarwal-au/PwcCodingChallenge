package com.pwc.codingchallenge.service;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.component.ComponentTest;
import com.pwc.codingchallenge.repository.AddressBookRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ComponentTest
public class AddressBookServiceTest {

    @Autowired
    private AddressBookService service;

    @Autowired
    private AddressBookRepository repository;

    @BeforeEach
    public void setup(){
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void saveAndGetAddressBook_withValidValues_returnsSuccess() {
        AddressBook book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("12345");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("mike");
        book.setPhoneNumber("23930");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("alpha");
        book.setPhoneNumber("10009");
        service.saveAddressBook(book);

        List<AddressBook> bookList = service.getAddressBook();
        assertEquals(3, bookList.size());

        assertEquals("alpha", bookList.get(0).getName());
        assertEquals("10009", bookList.get(0).getPhoneNumber());

        assertEquals("mike", bookList.get(1).getName());
        assertEquals("23930", bookList.get(1).getPhoneNumber());

        assertEquals("rachel", bookList.get(2).getName());
        assertEquals("12345", bookList.get(2).getPhoneNumber());

        System.out.print(bookList);
    }

    @Test
    public void deleteAllAddressBook_withValidValues_returnsSuccess() {
        AddressBook book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("12345");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("mike");
        book.setPhoneNumber("23930");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("alpha");
        book.setPhoneNumber("10009");
        service.deleteAll();

        List<AddressBook> bookList = service.getAddressBook();
        assertEquals(0, bookList.size());

    }

    @Test
    public void findUniqueFriends_withValidValues_returnsSuccess() {
        AddressBook book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("12345");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("mike");
        book.setPhoneNumber("23930");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("alpha");
        book.setPhoneNumber("10009");
        service.saveAddressBook(book);


        List<AddressBook> bookList = new ArrayList<>();
        book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("12345");
        bookList.add(book);

        book.setName("smith");
        book.setPhoneNumber("87876");
        bookList.add(book);


        Set<String> uniqueFriends = service.findUniqueFriends(bookList);
        assertEquals(4, uniqueFriends.size());
        assertTrue(uniqueFriends.contains("alpha"));
        assertTrue(uniqueFriends.contains("mike"));
        assertTrue(uniqueFriends.contains("rachel"));
        assertTrue(uniqueFriends.contains("smith"));

    }


}
