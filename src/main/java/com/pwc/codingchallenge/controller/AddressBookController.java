package com.pwc.codingchallenge.controller;

import com.pwc.codingchallenge.api.AddressBook;
import com.pwc.codingchallenge.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/address")
class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping
    public ResponseEntity<List<AddressBook>> findAll() {
        return new ResponseEntity<List<AddressBook>>(service.getAddressBook(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveAddressBookDetail(@RequestBody @NotNull @Valid AddressBook addressBook) {
        service.saveAddressBook(addressBook);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteAll(){
        service.deleteAll();
    }

    @PostMapping("/unique")
    public ResponseEntity<Set<String>> findUniqueFriends(@RequestBody @NotNull @Valid List<AddressBook> bookList) {
        Set<String> uniqueList = service.findUniqueFriends(bookList);

        return new ResponseEntity<Set<String>>(uniqueList, new HttpHeaders(), HttpStatus.OK);
    }

}
