package edu.usc.lunchnlearn.elasticsearch.dao;

import java.util.List;

/**
 * Created by wfleming on 7/5/15.
 */
public interface SearchSuggest {

    List<String> getSuggestions(String partial);

}
