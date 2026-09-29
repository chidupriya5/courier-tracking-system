package Courier.Tracking.System.repository;

import Courier.Tracking.System.model.Customer; // Updated from .entity to .model
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}