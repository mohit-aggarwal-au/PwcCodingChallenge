package com.pwc.codingchallenge.component.service;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.component.ComponentTest;
import com.pwc.codingchallenge.exception.InvalidArgumentException;
import com.pwc.codingchallenge.repository.AddressBookRepository;
import com.pwc.codingchallenge.service.AddressBookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ComponentTest
public class AddressBookServiceTest {

    @Autowired
    private AddressBookService service;

    @Autowired
    private AddressBookRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void saveAndGetAddressBook_withValidValues_returnsSuccess() {
        saveDataInDb();

        List<AddressBook> bookList = service.getAddressBookList();
        assertEquals(3, bookList.size());

        assertEquals("alpha", bookList.get(0).getName());
        assertEquals("0411223344", bookList.get(0).getPhoneNumber());

        assertEquals("mike", bookList.get(1).getName());
        assertEquals("0411223388", bookList.get(1).getPhoneNumber());

        assertEquals("rachel", bookList.get(2).getName());
        assertEquals("0411223399", bookList.get(2).getPhoneNumber());
    }


    @Test
    public void getAddressBook_withNoValuesInDb_returnsEmptyList() {
        List<AddressBook> bookList = service.getAddressBookList();
        assertEquals(0, bookList.size());
    }

    @ParameterizedTest
    @MethodSource("provideAddressBookInvalidObjects")
    void saveAndGetAddressBook_withInValidValues_throwsException(AddressBook book) {

        InvalidArgumentException exception = assertThrows(InvalidArgumentException.class, () ->
                service.saveAddressBook(book), "Expected to throw InvalidArgumentException, but didn't throw it");
        exception.getMessage();
        assertTrue(exception.getMessage().contains("Request object is invalid"));
    }

    private static Stream<Arguments> provideAddressBookInvalidObjects() {

        AddressBook bookWithNullName = new AddressBook();
        bookWithNullName.setPhoneNumber("0411223399");
        AddressBook bookWithInvalidName = new AddressBook();
        bookWithInvalidName.setName("  ");
        AddressBook nullObject = null;
        return Stream.of(
                Arguments.of(bookWithNullName),
                Arguments.of(bookWithInvalidName),
                Arguments.of(nullObject)
        );
    }

    @Test
    public void deleteAllAddressBook_withValidValues_returnsSuccess() {
        saveDataInDb();
        service.deleteAll();
        List<AddressBook> bookList = service.getAddressBookList();
        assertEquals(0, bookList.size());

    }

    @Test
    public void findUniqueFriends_WithEmptyRequestAndEmptyDbList_returnsEmptyList() {
        Set<String> uniqueFriends = service.findUniqueFriends(null);
        assertEquals(0, uniqueFriends.size());
    }

    @Test
    public void findUniqueFriends_WithEmptyRequest_returnsSuccess() {
        saveDataInDb();
        Set<String> uniqueFriends = service.findUniqueFriends(null);
        assertEquals(3, uniqueFriends.size());
        assertTrue(uniqueFriends.contains("alpha"));
        assertTrue(uniqueFriends.contains("mike"));
        assertTrue(uniqueFriends.contains("rachel"));
    }

    private void saveDataInDb() {
        AddressBook book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("0411223399");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("mike");
        book.setPhoneNumber("0411223388");
        service.saveAddressBook(book);

        book = new AddressBook();
        book.setName("alpha");
        book.setPhoneNumber("0411223344");
        service.saveAddressBook(book);
    }

    @Test
    public void findUniqueFriends_withValidValues_returnsSuccess() {
        saveDataInDb();

        Set<String> uniqueFriends = service.findUniqueFriends(getAddressBookList());
        assertEquals(4, uniqueFriends.size());
        assertTrue(uniqueFriends.contains("alpha"));
        assertTrue(uniqueFriends.contains("mike"));
        assertTrue(uniqueFriends.contains("rachel"));
        assertTrue(uniqueFriends.contains("smith"));

    }

    @Test
    public void findUniqueFriends_WithEmptyDbList_returnsSuccess() {
        Set<String> uniqueFriends = service.findUniqueFriends(getAddressBookList());
        assertEquals(3, uniqueFriends.size());
        assertTrue(uniqueFriends.contains("mike"));
        assertTrue(uniqueFriends.contains("rachel"));
        assertTrue(uniqueFriends.contains("smith"));
    }

    private List<AddressBook> getAddressBookList() {
        List<AddressBook> bookList = new ArrayList<>();

        AddressBook book = new AddressBook();
        book.setName("rachel");
        book.setPhoneNumber("0422334455");
        bookList.add(book);

        book = new AddressBook();
        book.setName("  ");
        book.setPhoneNumber("0422334456");
        bookList.add(book);

        book = new AddressBook();
        book.setName("smith");
        book.setPhoneNumber("0422334457");
        bookList.add(book);

        bookList.add(null);

        book = new AddressBook();
        book.setName(null);
        book.setPhoneNumber("0422334458");
        bookList.add(book);

        book = new AddressBook();
        book.setName("mike");
        book.setPhoneNumber("0422334459");
        bookList.add(book);

        return bookList;
    }

}
