package com.hungdx.customer_management.repository;

import com.hungdx.customer_management.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    public boolean existsByEmail(String email);
    public boolean existsByEmailAndIdNot(String email, UUID id);

    Optional<Customer> findByEmail(String email);
}
