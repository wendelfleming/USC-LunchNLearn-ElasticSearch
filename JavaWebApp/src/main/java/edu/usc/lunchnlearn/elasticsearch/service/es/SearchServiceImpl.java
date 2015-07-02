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

import edu.usc.lunchnlearn.elasticsearch.dao.MultiTypeSearch;
import edu.usc.lunchnlearn.elasticsearch.dao.bean.BaseItem;
import edu.usc.lunchnlearn.elasticsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wfleming on 6/30/15.
 */

public class SearchServiceImpl implements SearchService {

    private static final int NUMBER_OF_RESULTS_PER_PAGE = 10;

    @Autowired
    private MultiTypeSearch multiTypeSearch;

    @Override
    public Page<BaseItem> findAll(int pageNumber) {
        return multiTypeSearch.findAll(pageNumber);
    }

    @Override
    public List<BaseItem> findByItemId(String itemId) {
        return multiTypeSearch.findByItemId(itemId);
    }

    @Override
    public Page<BaseItem> findAll(String searchString, int pageNumber) {
        return multiTypeSearch.findAll(searchString, pageNumber);
    }




    private Pageable constructPageable(int pageNumber) {
        return new PageRequest(pageNumber, NUMBER_OF_RESULTS_PER_PAGE);
    }

}
