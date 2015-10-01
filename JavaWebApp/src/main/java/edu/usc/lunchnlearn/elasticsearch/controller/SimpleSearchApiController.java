/**
 * Copyright 2015 wendel fleming
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.usc.lunchnlearn.elasticsearch.controller;

/**
 * Created by wfleming on 10/1/15.
 */

import edu.usc.lunchnlearn.elasticsearch.dao.bean.BaseItem;
import edu.usc.lunchnlearn.elasticsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

@RestController
public class SimpleSearchApiController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public Page<BaseItem> search(String searchQuery, int currentPage) {
        return searchService.findAll(searchQuery, currentPage);
//
//        Map urlParams = new HashMap<>();
//        urlParams.put("searchQuery", searchQuery);
//        modelMap.addAttribute("urlParams", urlParams);
//
//        modelMap.addAttribute("currentPage", currentPage);
//        modelMap.addAttribute("searchterm", searchQuery);
//        modelMap.addAttribute("resultsPage", searchService.findAll(searchQuery, currentPage));
//
//        return SEARCHRESULT_PAGE;
    }


}
