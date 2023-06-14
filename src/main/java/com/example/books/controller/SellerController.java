package com.example.books.controller;

import com.example.books.exception.SellerNotFoundException;
import com.example.books.model.Seller;
import com.example.books.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sellers")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/")
    Seller newSeller(@RequestBody Seller seller) {
        return sellerService.save(seller);
    }

    // Single item

    @GetMapping("/{id}")
    Seller one(@PathVariable Long id) {

        return sellerService.findById(id)
                .orElseThrow(() -> new SellerNotFoundException(String.valueOf(id)));
    }


    // HAND MADE

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllSellers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        Map<String, Object> response = sellerService.getAllSellers(name, address, page, size, sort);
        if (response.containsKey("sellers") && ((List<Seller>) response.get("sellers")).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
