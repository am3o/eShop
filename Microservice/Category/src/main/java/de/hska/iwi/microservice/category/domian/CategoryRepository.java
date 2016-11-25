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

package de.hska.iwi.microservice.category.domian;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ameo on 27.05.16.
 */
public interface CategoryRepository extends CrudRepository<CategoryDAO, Long> {
    List<CategoryDAO> findAll();

    CategoryDAO findById(int id);

    CategoryDAO findByName(String name);
}
