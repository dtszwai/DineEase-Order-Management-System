package org.order.services;

import org.order.exceptions.NotFoundException;
import org.order.mappers.CustomerTokenMapper;
import org.order.pojo.entity.Customer;
import org.order.pojo.entity.CustomerToken;
import org.order.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class is responsible for managing customer tokens in the system. It provides methods
 * to create a new customer token and find a customer token by its token.
 */
@Service
public class CustomerTokenService {

  @Autowired
  private CustomerTokenMapper customerTokenMapper;

  public CustomerToken getToken(String token) {
    CustomerToken customerToken = customerTokenMapper.findByToken(token);
    if (customerToken == null) {
      throw new NotFoundException();
    }
    return customerToken;
  }

  public CustomerToken getToken(Long customerId) {
    CustomerToken customerToken = customerTokenMapper.findToken(customerId);
    if (customerToken == null) {
      throw new NotFoundException();
    }
    return customerToken;
  }


  public void createToken(Customer customer) {
    String token;
    // Generate a unique token
    do {
      token = TokenGenerator.generateToken();
    } while (customerTokenMapper.findByToken(token) != null);

    var customerToken = new CustomerToken();

    customerToken.setToken(token);
    customerToken.setCustomerId(customer.getId());

    // Save the new token in the database
    customerTokenMapper.insert(customerToken);
  }

  public void updateToken(CustomerToken customerToken) {
    customerTokenMapper.update(customerToken);
  }
}