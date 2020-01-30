package com.pwc.service;

import com.pwc.coding.api.AddressBook;
import com.pwc.coding.service.AddressBookService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    }

}
