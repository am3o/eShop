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

import de.hska.iwi.microservice.authentication.domian.Customer;
import de.hska.iwi.microservice.authentication.domian.CustomerRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean createCustomer(Customer value) {
        return false;
    }

    @Override
    public Customer getCustomerInformation(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public boolean updateCustomer(Customer value) {

        return true;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId);

        return false;
    }

    @Override
    public boolean logInCustomer(int customerId) {
        return false;
    }

    @Override
    public boolean logOutCustomer(int customerId) {
        return false;
    }
}
