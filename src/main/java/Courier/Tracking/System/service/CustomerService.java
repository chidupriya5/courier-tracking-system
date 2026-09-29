package Courier.Tracking.System.service;

import Courier.Tracking.System.model.Customer;
import Courier.Tracking.System.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean emailExists(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    public Customer registerCustomer(
            String name,
            String email,
            String phone,
            String address,
            String password) {

        Customer customer = new Customer(
                name,
                email,
                phone,
                address,
                password
        );

        return customerRepository.save(customer);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean validateLogin(String email, String password) {

        Optional<Customer> customer =
                customerRepository.findByEmail(email);

        return customer.isPresent()
                && customer.get().getPassword().equals(password);
    }
}