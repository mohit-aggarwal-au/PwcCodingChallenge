package com.pwc.codingchallenge.controller;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/address")
class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAddressBookList() {
        return new ResponseEntity<List<AddressBook>>(service.getAddressBookList(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveAddressBook(@RequestBody @NotNull @Valid AddressBook addressBook) {
        service.saveAddressBook(addressBook);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteAllAddressBookObjects() {
        service.deleteAll();
    }

    @PostMapping("/unique")
    public ResponseEntity<Set<String>> findUniqueFriends(@RequestBody List<AddressBook> bookList) {
        Set<String> uniqueList = service.findUniqueFriends(bookList);
        return new ResponseEntity<Set<String>>(uniqueList, new HttpHeaders(), HttpStatus.OK);
    }

}
