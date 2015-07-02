/**
 * Copyright 2015 wendel fleming
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usc.lunchnlearn.elasticsearch.service.es;

import edu.usc.lunchnlearn.elasticsearch.dao.ArmorRepository;
import edu.usc.lunchnlearn.elasticsearch.dao.bean.Armor;
import edu.usc.lunchnlearn.elasticsearch.service.ArmorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wfleming on 7/1/15.
 */

public class ArmorServiceImpl implements ArmorService {

    private static final int NUMBER_OF_RESULTS_PER_PAGE = 10;

    @Autowired
    private ArmorRepository armorRepository;

    public Page<Armor> findAll(int pageNumber) {
        return armorRepository.findAll(constructPageable(pageNumber));
    }

    public List<Armor> findByItemId(String itemId) {
//        return armorRepository.search()
        return armorRepository.findByItemId(itemId);
    }







    private Pageable constructPageable(int pageNumber) {
        return new PageRequest(pageNumber, NUMBER_OF_RESULTS_PER_PAGE);
    }

}
