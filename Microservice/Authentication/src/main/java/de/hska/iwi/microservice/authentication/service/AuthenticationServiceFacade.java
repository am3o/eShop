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

import de.hska.iwi.microservice.authentication.domian.CustomerDAO;
import de.hska.iwi.microservice.authentication.domian.CustomerRepository;
import de.hska.iwi.microservice.authentication.entity.Customer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationServiceFacade implements IAuthenticationServiceFacade {
    private final Logger logger = Logger.getLogger(AuthenticationServiceFacade.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthenticationServiceFacade(CustomerRepository customerRepository) {
        super();
        logger.info("Erzeuge AuthenticationServiceFasade");
        this.customerRepository = customerRepository;
    }

    private CustomerDAO convertToCustomerDAO(Customer customer) {
        return new CustomerDAO(customer.getId(), customer.getName(), customer.getLastname(),
                customer.getUsername(), customer.getPassword(), customer.getRole());
    }

    private List<Customer> convertToListCustomer(List<CustomerDAO> value) {
        List<Customer> result = new ArrayList<>();
        for (CustomerDAO customerDAO : value)
            result.add(customerDAO.toCustomer());
        return result;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("Erzeuge neuen Kunden in der Datenbank");
        CustomerDAO customerDAO = this.convertToCustomerDAO(customer);
        return customerRepository.save(customerDAO).toCustomer();
    }

    @Override
    public List<Customer> getCustomerInformation(Customer customer) {
        logger.info(String.format("Suche nach Benutzern mit den folgenden Kriterien: %s", customer.toString()));
        return this.convertToListCustomer(customerRepository.findByUsername(customer.getUsername()));
    }

    @Override
    public Customer getCustomerInformation(long customerId) {
        logger.info(String.format("Suche nach BenutzerId [%d] im System", customerId));
        return customerRepository.findById(customerId).toCustomer();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        logger.info(String.format("Aktualisiere Benutzer %s im System", customer.toString()));
        CustomerDAO customerDAO = this.convertToCustomerDAO(customer);
        return customerRepository.save(customerDAO).toCustomer();
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        logger.info(String.format("Lösche Benutzer %s aus dem System", customer.toString()));
        CustomerDAO customerDAO = this.convertToCustomerDAO(customer);
        customerRepository.delete(customerDAO);
        return customerRepository.equals(customerDAO);
    }

    @Override
    public Customer logInCustomer(Customer customer) {
        logger.info(String.format("Melde Benutzer %s im System an", customer.toString()));
        return customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()).toCustomer();
    }

    @Override
    public Customer logOutCustomer(Customer customer) {
        logger.info(String.format("Melde Benutzer %s vom System ab", customer.toString()));
        return customerRepository.findByUsernameAndPassword(customer.getUsername(), customer.getPassword()).toCustomer();
    }
}
