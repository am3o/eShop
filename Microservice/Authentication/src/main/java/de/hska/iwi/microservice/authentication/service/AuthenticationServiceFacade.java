/*
 *    Copyright (c) 2016.   Joshua Braun
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.hska.iwi.microservice.authentication.service;

import de.hska.iwi.microservice.authentication.builder.CustomerBuilder;
import de.hska.iwi.microservice.authentication.domian.CustomerDAO;
import de.hska.iwi.microservice.authentication.domian.CustomerRepository;
import de.hska.iwi.microservice.authentication.entity.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceFacade implements IAuthenticationServiceFacade {

  private final Logger logger = Logger.getLogger(AuthenticationServiceFacade.class);
  private final CustomerRepository customerRepository;

  private static final int PERMISSION_ADMIN_ROLE = 1;

  @Autowired
  public AuthenticationServiceFacade(CustomerRepository customerRepository) {
    super();
    logger.info("Erzeuge AuthenticationServiceFasade");
    this.customerRepository = customerRepository;
  }

  private CustomerDAO convertToCustomerDAO(Customer customer) {
    return new CustomerDAO(customer.getId(), customer.getName(), customer.getLastname(),
        customer.getUsername(), customer.getPassword(), customer.getRole().getValue());
  }

  private Customer convertToCustomer(CustomerDAO customerDAO) {
    return null;
  }

  @Override
  public Customer createCustomer(Customer customer) {
    logger.info("Erzeuge neuen Kunden in der Datenbank");
    CustomerDAO customerDAO = this.convertToCustomerDAO(customer);
    return this.convertToCustomer(customerRepository.save(customerDAO));
  }

  @Override
  public Customer getCustomerInformation(Customer customer) {
    logger.info(
        String.format("Suche nach Benutzern mit den folgenden Kriterien: %s", customer.toString()));
    return this.convertToCustomer(customerRepository.findByUsername(customer.getUsername()));
  }

  @Override
  public Customer getCustomerInformation(String customername) {
    logger.info(String.format("Suche nach Anwendername[%s] im System", customername));
    return this.convertToCustomer(customerRepository.findByUsername(customername));
  }

  @Override
  public Customer getCustomerInformation(long customerId) {
    logger.info(String.format("Suche nach BenutzerId [%d] im System", customerId));
    return this.convertToCustomer(customerRepository.findById(customerId));
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    logger.info(String.format("Aktualisiere Benutzer %s im System", customer.toString()));
    CustomerDAO customerDAO = this.convertToCustomerDAO(customer);
    return this.convertToCustomer(customerRepository.save(customerDAO));
  }

  @Override
  public boolean deleteCustomer(long customerId) {
    logger.info(String.format("Lösche Benutzer %d aus dem System", customerId));
    customerRepository.delete(customerId);
    return !(customerRepository.findById(customerId) instanceof CustomerDAO);
  }

  @Override
  public boolean existCustomer(String username, String password) {
    logger
        .info(String.format("Überprüft ob Benutzer %s:%s dem System vorliegt", username, password));
    return customerRepository.findByUsernameAndPassword(username, password) instanceof CustomerDAO;
  }

  @Override
  public boolean checkPermission(String username, String password) {
    logger.info(String
        .format("Überprüft ob Benutzer %s:%s dem System vorliegt mit passenden Rechten", username,
            password));
    CustomerDAO result = customerRepository.findByUsernameAndPassword(username, password);
    return result.getRole() == PERMISSION_ADMIN_ROLE;
  }

  private Customer existCustomer(Customer customer) {
    Customer resultCustomer = null;
    try {
      resultCustomer = this
          .convertToCustomer(customerRepository
              .findByUsernameAndPassword(customer.getUsername(), customer.getPassword()));
    } catch (Exception ex) {
      logger.error(String
              .format("Benutzer %s:%s liegt dem System nicht vor.", customer.getUsername(),
                  customer.getPassword()),
          ex);
    } finally {
      return resultCustomer;
    }
  }

  @Override
  public Customer logInCustomer(String username, String password) {
    logger.info(String.format("Melde Benutzer %s:%s im System an", username, password));
    Customer customer = new CustomerBuilder(username, password).build();
    return this.existCustomer(customer);
  }

  @Override
  public Customer logOutCustomer(String username, String password) {
    logger.info(String.format("Melde Benutzer %s:%s vom System ab", username, password));
    Customer customer = new CustomerBuilder(username, password).build();
    return this.existCustomer(customer);
  }
}
