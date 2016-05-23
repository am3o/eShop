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

public interface IAuthenticationController {
    /**
     * Erzeugt einen neuen Benutzer und die Operation wird quitiert, wenn dies erfolgreich war.
     *
     * @return Status des Vorgangs.
     */
    boolean createUser();

    /**
     * Liefert alle vorliegenden Informationen zurück, welche von dem speziellen Benutzer vorhanden
     * sind.
     *
     * @param userId eindeutige BenutzerId
     * @return vorliegende Informationen zum Benutzer
     */
    String getUserInformation(int userId);

    /**
     * Aktualisiert die Informationen eines speziellen Benutzers.
     *
     * @param userId eindeutige BenutzerId
     * @return Status des Vorgangs
     */
    boolean updateUser(int userId);

    /**
     * Löscht einen speziellen Benutzer aus dem System.
     *
     * @param userId eindeutige BenutzerId
     * @return Status des Vorgangs.
     */
    boolean deleteUser(int userId);

    /**
     * Logt einen Benutzer in das System ein.
     *
     * @param userId eindeutige BenutzerId.
     * @return Status des Vorgangs
     */
    boolean loginUser(int userId);

    /**
     * Logt einen Benutzer aus dem System aus.
     *
     * @param userId eindeutige BenutzerId
     * @return Status des Vorgangs
     */
    boolean logoutUser(int userId);
}
