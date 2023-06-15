package com.example.books.service;

import com.example.books.model.Seller;
import com.example.books.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;

    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Map<String, Object> getAllSellers(String name, String address, int page, int size, String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                Sort.Direction dire = _sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
                orders.add(new Sort.Order(dire, _sort[0]));
            }
        } else {
            // sort=[field, direction]
            Sort.Direction dire = sort[1].contains("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            orders.add(new Sort.Order(dire, sort[0]));
        }


        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        Page<Seller> pageSellers;

        pageSellers = sellerRepository.findByNameAndAddress(name, address, pagingSort);

        List<Seller> sellers = pageSellers.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("data", sellers);
        response.put("currentPage", pageSellers.getNumber());
        response.put("totalItems", pageSellers.getTotalElements());
        response.put("totalPages", pageSellers.getTotalPages());
        return response;
    }
}
