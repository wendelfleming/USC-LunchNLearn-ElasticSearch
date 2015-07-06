package edu.usc.lunchnlearn.elasticsearch.service;

import java.util.List;

/**
 * Created by wfleming on 7/5/15.
 */
public interface SearchSuggestService {

    List<String> getSuggestions(String partial);

}
