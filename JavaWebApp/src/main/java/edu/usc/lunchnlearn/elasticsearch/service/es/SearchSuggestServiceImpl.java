package edu.usc.lunchnlearn.elasticsearch.service.es;

import edu.usc.lunchnlearn.elasticsearch.dao.SearchSuggest;
import edu.usc.lunchnlearn.elasticsearch.service.SearchSuggestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wfleming on 7/5/15.
 */
public class SearchSuggestServiceImpl implements SearchSuggestService {

    @Autowired
    private SearchSuggest searchSuggest;

    @Override
    public List<String> getSuggestions(String partial) {
        return searchSuggest.getSuggestions(partial);
    }
}
