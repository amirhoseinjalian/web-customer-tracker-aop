package com.jalian.springdemo.dao;

import com.jalian.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);

    void delete(int id);
}
