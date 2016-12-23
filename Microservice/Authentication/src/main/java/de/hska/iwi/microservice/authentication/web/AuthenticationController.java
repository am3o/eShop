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

import de.hska.iwi.microservice.authentication.builder.CustomerBuilder;
import de.hska.iwi.microservice.authentication.entity.Customer;
import de.hska.iwi.microservice.authentication.service.IAuthenticationServiceFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info(String.format("Service-Aufruf: createCustomer mit dem Wert: %s", customer.toString()));
        return new ResponseEntity<Customer>(authenticationServiceFasade.createCustomer(customer), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Customer> existCustomer(@RequestHeader(name = "usr") String username, @RequestHeader(name = "pass") String password, @RequestParam(value = "permission", required = false, defaultValue = "false") boolean permission) {
        ResponseEntity<Customer> response = new ResponseEntity<Customer>(new CustomerBuilder().buildEmpty(), HttpStatus.NOT_FOUND);
        try{
            if(this.authenticationServiceFasade.checkPermission(username, password)) {
                CustomerBuilder builder = new CustomerBuilder(username, password);
                if(!permission) {
                    response = new ResponseEntity<Customer>(builder.build(), HttpStatus.OK);
                } else {
                    Customer.Permission role = this.authenticationServiceFasade.checkPermission(username, password)?
                                                    Customer.Permission.Admin : Customer.Permission.User;
                    response = new ResponseEntity<Customer>(builder.setRole(role).build(), HttpStatus.OK);
                }
            }
        }catch (NullPointerException ex) {
            logger.error("Benutzer liegt dem System nicht vor.", ex);
        } finally {
            return response;
        }
    }

    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerInformation(@PathVariable("userId") long customerId) {
        logger.info(String.format("Service-Aufruf: getCustomerInformation mit dem Wert: %d", customerId));
        return new ResponseEntity<Customer>(authenticationServiceFasade.getCustomerInformation(customerId), HttpStatus.OK);
    }


    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        logger.info(String.format("Service-Aufruf: updateCustomer mit dem Wert: %s", customer.toString()));
        return new ResponseEntity<Customer>(authenticationServiceFasade.updateCustomer(customer), HttpStatus.ACCEPTED);
    }

    @Override
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable("userId") int customerId) {
        logger.info(String.format("Service-Aufruf: deleteCustomer mit dem Wert: %d", customerId));
        return new ResponseEntity<Boolean>(authenticationServiceFasade.deleteCustomer(customerId), HttpStatus.ACCEPTED);
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> loginCustomer(@RequestHeader(name = "usr") String username, @RequestHeader(name = "pass") String password) {
        logger.info(String.format("Service-Aufruf: loginCustomer mit dem Wert: %s : %s", username, password));
        return new ResponseEntity<Boolean>(authenticationServiceFasade.logInCustomer(username, password), authenticationServiceFasade.logInCustomer(username, password)?HttpStatus.OK:HttpStatus.FORBIDDEN);
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> logoutCustomer(@RequestHeader(name = "usr") String username, @RequestHeader(name = "pass") String password) {
        logger.info(String.format("Service-Aufruf: logoutCustomer mit dem Wert: %s : %s", username, password));
        return new ResponseEntity<Boolean>(authenticationServiceFasade.logInCustomer(username, password), authenticationServiceFasade.logInCustomer(username, password)?HttpStatus.OK:HttpStatus.FORBIDDEN);

    }
}
