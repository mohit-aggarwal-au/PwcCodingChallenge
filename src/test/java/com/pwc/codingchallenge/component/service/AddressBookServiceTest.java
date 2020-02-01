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

    @Test
    public void deleteAllAddressBook_withValidValues_returnsSuccess() {
        saveDataInDb();
        service.deleteAll();
        List<AddressBook> bookList = service.getAddressBookList();
        assertEquals(0, bookList.size());

    }

    @Test
    public void findUniqueFriends_WithEmptyRequestAndEmptyDbList_returnsEmptyList() {
        List<String> uniqueFriends = service.findUniqueFriends(null);
        assertEquals(0, uniqueFriends.size());
    }

    @Test
    public void findUniqueFriends_WithEmptyRequest_returnsSuccess() {
        saveDataInDb();
        List<String> uniqueFriends = service.findUniqueFriends(null);
        assertEquals(3, uniqueFriends.size());
        assertTrue("alpha".equals(uniqueFriends.get(0)));
        assertTrue("mike".equals(uniqueFriends.get(1)));
        assertTrue("rachel".equals(uniqueFriends.get(2)));

    }

    private void saveDataInDb() {
        service.saveAddressBook(AddressBook.builder().name("rachel").phoneNumber("0411223399").build());
        service.saveAddressBook(AddressBook.builder().name("mike").phoneNumber("0411223388").build());
        service.saveAddressBook(AddressBook.builder().name("alpha").phoneNumber("0411223344").build());
    }

    @Test
    public void findUniqueFriends_withValidValues_returnsSuccess() {
        saveDataInDb();

        List<String> uniqueFriends = service.findUniqueFriends(getAddressBookList());
        assertEquals(4, uniqueFriends.size());
        assertTrue("alpha".equals(uniqueFriends.get(0)));
        assertTrue("mike".equals(uniqueFriends.get(1)));
        assertTrue("rachel".equals(uniqueFriends.get(2)));
        assertTrue("smith".equals(uniqueFriends.get(3)));

    }

    @Test
    public void findUniqueFriends_WithEmptyDbList_returnsSuccess() {
        List<String> uniqueFriends = service.findUniqueFriends(getAddressBookList());
        assertEquals(3, uniqueFriends.size());
        assertTrue("mike".equals(uniqueFriends.get(0)));
        assertTrue("rachel".equals(uniqueFriends.get(1)));
        assertTrue("smith".equals(uniqueFriends.get(2)));
    }

    private List<AddressBook> getAddressBookList() {
        List<AddressBook> bookList = new ArrayList<>();
        bookList.add(AddressBook.builder().name("rachel").phoneNumber("0422334455").build());
        bookList.add(AddressBook.builder().name("  ").phoneNumber("0422334456").build());
        bookList.add(AddressBook.builder().name("smith").phoneNumber("0422334457").build());
        bookList.add(null);
        bookList.add(AddressBook.builder().name(null).phoneNumber("0422334458").build());
        bookList.add(AddressBook.builder().name("mike").phoneNumber("0422334459").build());
        return bookList;
    }

    private static Stream<Arguments> provideAddressBookInvalidObjects() {

        AddressBook bookWithNullName = AddressBook.builder().phoneNumber("0411223399").build();
        AddressBook bookWithInvalidName = AddressBook.builder().name("   ").build();
        AddressBook nullObject = null;
        return Stream.of(
                Arguments.of(bookWithNullName),
                Arguments.of(bookWithInvalidName),
                Arguments.of(nullObject)
        );
    }

}
