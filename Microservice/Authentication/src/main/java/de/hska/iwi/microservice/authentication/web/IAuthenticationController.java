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

import java.util.List;

public interface IAuthenticationController {
    /**
     * Erzeugt einen neuen Benutzer und die Operation wird quitiert, wenn dies erfolgreich war.
     *
     * @return Status des Vorgangs.
     */
    Customer createCustomer(Customer customer);

    /**
     * Liefert alle vorliegenden Informationen zurück, welche von dem speziellen Benutzer vorhanden
     * sind.
     *
     * @param userId eindeutige BenutzerId
     * @return vorliegende Informationen zum Benutzer
     */
    Customer getCustomerInformation(long customerId);

    /**
     * Liefert anhand des einzigartigen Anwendernamen den Anwender zurück.
     *
     * @param username Anwendername
     * @return Anwender
     */
    Customer getCustomerInformation(String username);

    /**
     * Aktualisiert die Informationen eines speziellen Benutzers.
     *
     * @param customer eindeutige Benutzer
     * @return Status des Vorgangs
     */
    Customer updateCustomer(Customer customer);

    /**
     * Löscht einen speziellen Benutzer aus dem System.
     *
     * @param userId eindeutige BenutzerId
     * @return Status des Vorgangs.
     */
    boolean deleteCustomer(Customer customer);

    /**
     * Logt einen Benutzer in das System ein.
     *
     * @param userId eindeutige BenutzerId.
     * @return Status des Vorgangs
     */
    Customer loginCustomer(Customer customer);

    /**
     * Logt einen Benutzer aus dem System aus.
     *
     * @param userId eindeutige BenutzerId
     * @return Status des Vorgangs
     */
    Customer logoutCustomer(Customer customer);
}
