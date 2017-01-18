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

import de.hska.iwi.microservice.authentication.entity.Customer;

public interface IAuthenticationServiceFacade {

  /**
   * Erzeugt einen neuen Benutzer im System.
   */
  Customer createCustomer(Customer customer);

  /**
   * Liefert eine Liste an CustomerDAO zurück auf welche die gegebenen Informationen zutreffen.
   */
  Customer getCustomerInformation(Customer customer);

  /**
   * Liefert die hinterlegten Informationen zu dem speziellen Benutzer zurück.
   */
  Customer getCustomerInformation(String customername);

  /**
   * Liefert die hinterlegten Informationen zu dem speziellen Benutzer zurück.
   */
  Customer getCustomerInformation(long customerId);

  /**
   * Aktualisiert einen Benutzer im System.
   */
  Customer updateCustomer(Customer customer);

  /**
   * Löscht einen Benutzer aus dem System.
   */
  boolean deleteCustomer(long CustomerId);

  /**
   * Überprüft ob der angegebene Benutzer dem System vorliegt.
   */
  boolean existCustomer(String username, String password);

  /**
   * Überprüfung ob der angegebene Benutzer über passende Rechte im System verfügt.
   */
  boolean checkPermission(String username, String password);

  /**
   * Logt einen Benutzer in das System ein.
   */
  Customer logInCustomer(String username, String password);

  /**
   * Logt einen Benutzer aus dem System aus.
   */
  Customer logOutCustomer(String username, String password);
}
