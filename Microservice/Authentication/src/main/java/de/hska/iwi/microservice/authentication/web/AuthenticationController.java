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

package de.hska.iwi.microservice.authentication.web;

import de.hska.iwi.microservice.authentication.entity.Customer;
import de.hska.iwi.microservice.authentication.service.IAuthenticationServiceFacade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthenticationController implements IAuthenticationController {
    private final Logger logger = Logger.getLogger(AuthenticationController.class);
    private final IAuthenticationServiceFacade authenticationServiceFasade;

    @Autowired
    public AuthenticationController(IAuthenticationServiceFacade authenticationServiceFasade) {
        logger.info("Erzeuge Authentication-Steuereinheit");
        this.authenticationServiceFasade = authenticationServiceFasade;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Customer createCustomer(@ModelAttribute Customer customer) {
        logger.info(String.format("Service-Aufruf: createCustomer mit dem Wert: %s", customer.toString()));
        return authenticationServiceFasade.createCustomer(customer);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Customer getCustomerInformation(@RequestParam("user") String customername) {
        logger.info(String.format("Service-Aufruf: getCustomerInformation mit dem Wert: %s", customername));
        return authenticationServiceFasade.getCustomerInformation(customername);
    }

    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Customer getCustomerInformation(@PathVariable("userId") long customerId) {
        logger.info(String.format("Service-Aufruf: getCustomerInformation mit dem Wert: %d", customerId));
        return authenticationServiceFasade.getCustomerInformation(customerId);
    }


    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Customer updateCustomer(@ModelAttribute Customer customer) {
        logger.info(String.format("Service-Aufruf: updateCustomer mit dem Wert: %s", customer.toString()));
        return authenticationServiceFasade.updateCustomer(customer);
    }

    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public boolean deleteCustomer(@ModelAttribute Customer customer) {
        logger.info(String.format("Service-Aufruf: deleteCustomer mit dem Wert: %s", customer.toString()));
        return authenticationServiceFasade.deleteCustomer(customer);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public Customer loginCustomer(Customer customer) {
        logger.info(String.format("Service-Aufruf: loginCustomer mit dem Wert: %s", customer.toString()));
        return authenticationServiceFasade.logInCustomer(customer);
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public Customer logoutCustomer(Customer customer) {
        logger.info(String.format("Service-Aufruf: logoutCustomer mit dem Wert: %s", customer.toString()));
        return authenticationServiceFasade.logOutCustomer(customer);
    }
}
