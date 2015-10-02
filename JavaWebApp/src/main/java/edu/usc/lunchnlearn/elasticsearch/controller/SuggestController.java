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

import edu.usc.lunchnlearn.elasticsearch.service.SearchSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wfleming on 7/5/15.
 */

@RestController
public class SuggestController {

    @Autowired
    private SearchSuggestService searchSuggestService;

    @RequestMapping(value = "/suggest/{partial}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List listData(@PathVariable("partial") String partial) {
        return searchSuggestService.getSuggestions(partial);
    }

    @RequestMapping(value = "/suggest/{partial}", method = RequestMethod.GET, headers = "Accept=application/json", params = {"callback"})
    public MappingJacksonValue listData(@PathVariable("partial") String partial, @RequestParam("callback") String callback) {
        MappingJacksonValue value = new MappingJacksonValue(searchSuggestService.getSuggestions(partial));
        value.setJsonpFunction(callback);
        return value;
//        return searchSuggestService.getSuggestions(partial);
    }

}


