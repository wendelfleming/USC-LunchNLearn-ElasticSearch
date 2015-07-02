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
package edu.usc.lunchnlearn.elasticsearch.controller;

import edu.usc.lunchnlearn.elasticsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wfleming on 7/2/15.
 */

@Controller
public class SimpleSearchController {

    private static final String SEARCHRESULT_PAGE = "searchresult";


    @Autowired
    private SearchService searchService;


    @RequestMapping(value = "/simplesearch.html", method = RequestMethod.POST)
    public String printIndex(String searchQuery, int currentPage, ModelMap modelMap) {
//        modelMap.addAttribute("message", "WoW ElasticSearch");

        modelMap.addAttribute("searchterm", searchQuery);
        modelMap.addAttribute("resultsPage", searchService.findAll(searchQuery, currentPage));

        return SEARCHRESULT_PAGE;
    }


}



