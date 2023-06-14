package com.example.books.repository;

import com.example.books.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    @Query("SELECT s FROM Seller s WHERE (s.name LIKE %?1% or ?1 is null)"
            + " AND s.address LIKE %?2% or ?2 is null")
    Page<Seller> findByNameAndAddress(String name, String address, Pageable pageSort);
}
