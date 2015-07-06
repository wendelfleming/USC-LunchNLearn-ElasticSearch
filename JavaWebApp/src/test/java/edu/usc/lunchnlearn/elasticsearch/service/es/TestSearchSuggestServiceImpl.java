package edu.usc.lunchnlearn.elasticsearch.service.es;

import edu.usc.lunchnlearn.elasticsearch.service.SearchSuggestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wfleming on 7/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-application-context.xml"})
@EnableJpaRepositories(basePackages = "edu.usc.lunchnlearn.springmvc.dao")
public class TestSearchSuggestServiceImpl {

    @Autowired
    private SearchSuggestService searchSuggestService;


    @Test
    public void testFindAllWithValue() {
        assertNotNull(searchSuggestService);
        List<String> suggestions = searchSuggestService.getSuggestions("Bla");
        assertEquals(5, suggestions.size());
    }



}
