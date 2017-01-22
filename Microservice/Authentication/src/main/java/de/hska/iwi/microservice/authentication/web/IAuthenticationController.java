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
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

  /**
   * Erzeugt einen neuen Benutzer und die Operation wird quitiert, wenn dies erfolgreich war.
   */
  ResponseEntity<Customer> createCustomer(Customer customer);

  /**
   * Überprüft ob der angegebene Benutzer existiert.
   */
  ResponseEntity<Customer> existCustomer(String username, String password, boolean permission);

  /**
   * Liefert alle vorliegenden Informationen zurück, welche von dem speziellen Benutzer vorhanden
   * sind.
   */
  ResponseEntity<Customer> getCustomerInformation(long customerId);

  /**
   * Liefert einen vorliegenden Benutzer zurück mit dem gegebenen Benutzernamen.
   */
  ResponseEntity<Customer> getCustomerByUsername(String username);

  /**
   * Aktualisiert die Informationen eines speziellen Benutzers.
   */
  ResponseEntity<Customer> updateCustomer(Customer customer);

  /**
   * Löscht einen speziellen Benutzer aus dem System.
   */
  ResponseEntity<Boolean> deleteCustomer(int customerId);

  /**
   * Logt einen Benutzer in das System ein.
   */
  ResponseEntity<Customer> loginCustomer(String username, String password);

  /**
   * Logt einen Benutzer aus dem System aus.
   */
  ResponseEntity<Customer> logoutCustomer(String username, String password);
}
