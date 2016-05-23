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

import de.hska.iwi.microservice.authentication.service.IAuthenticationServiceFacade;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements IAuthenticationController {
    private final Logger logger = Logger.getLogger(AuthenticationController.class);
    private final IAuthenticationServiceFacade authenticationServiceFasade;

    @Autowired
    public AuthenticationController(IAuthenticationServiceFacade authenticationServiceFasade) {
        logger.info("Erzeuge Authentication-Steuereinheit");
        this.authenticationServiceFasade = authenticationServiceFasade;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public boolean createUser() {
        return false;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String getUserInformation(@PathVariable("userId") int userId) {
        return authenticationServiceFasade.getCustomerInformation(userId).toString();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public boolean updateUser(@PathVariable("userId") int userId) {
        return false;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("userId") int userId) {
        return false;
    }

    @RequestMapping(value = "/{userId}/login", method = RequestMethod.PUT)
    public boolean loginUser(@PathVariable("userId") int userId) {
        return false;
    }

    @RequestMapping(value = "/{userId}/logout", method = RequestMethod.PUT)
    public boolean logoutUser(@PathVariable("userId") int userId) {
        return false;
    }
}
