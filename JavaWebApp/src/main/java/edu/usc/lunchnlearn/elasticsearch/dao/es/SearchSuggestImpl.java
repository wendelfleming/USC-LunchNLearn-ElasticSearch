package edu.usc.lunchnlearn.elasticsearch.dao.es;

import edu.usc.lunchnlearn.elasticsearch.dao.SearchSuggest;
import org.elasticsearch.action.suggest.SuggestRequestBuilder;
import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wfleming on 7/5/15.
 */
public class SearchSuggestImpl implements SearchSuggest {

    private Client client;


    @Override
    public List<String> getSuggestions(String partial) {
        List<String> returnList = new ArrayList<>();

        String suggestionName = "suggestion";

        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(suggestionName);
        SuggestResponse suggestResponse = client.prepareSuggest("wow").setSuggestText(partial).addSuggestion(completionSuggestionBuilder.field("name.suggest")).execute().actionGet();
        Suggest suggest = suggestResponse.getSuggest();
        Suggest.Suggestion suggestion = suggest.getSuggestion(suggestionName);

        List<Suggest.Suggestion.Entry> list = suggestion.getEntries();
        for(Suggest.Suggestion.Entry entry : list) {
            List<Suggest.Suggestion.Entry.Option> options = entry.getOptions();
            for(Suggest.Suggestion.Entry.Option option : options) {
                returnList.add(option.getText().toString());
            }
        }

        return returnList;
    }



    public void setClient(Client client) {
        this.client = client;
    }
}
