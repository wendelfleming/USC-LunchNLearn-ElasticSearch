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
package edu.usc.lunchnlearn.elasticsearch.dao.es;

import edu.usc.lunchnlearn.elasticsearch.dao.MultiTypeSearch;
import edu.usc.lunchnlearn.elasticsearch.dao.bean.BaseItem;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;


import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.List;

/**
 * Created by wfleming on 7/2/15.
 */

public class MultiTypeSearchImpl implements MultiTypeSearch {

    private static final int NUMBER_OF_RESULTS_PER_PAGE = 10;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private String indexName;
    private String[] typeNames;

    @Override
    public Page<BaseItem> findAll(Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withIndices(indexName)
                .withTypes(typeNames)
                .build();

        return elasticsearchTemplate.queryForPage(searchQuery, BaseItem.class);
    }


    @Override
    public Page<BaseItem> findAll(String searchString, Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(searchString))
                .withPageable(pageable)
                .withIndices(indexName)
                .withTypes(typeNames)
                .build();

        return elasticsearchTemplate.queryForPage(searchQuery, BaseItem.class);
    }


    @Override
    public List<BaseItem> findByItemId(String itemId) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("id", itemId))
                .withIndices(indexName)
                .withTypes(typeNames)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, BaseItem.class);
    }


    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String[] getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String[] typeNames) {
        this.typeNames = typeNames;
    }


    private Pageable constructPageable(int pageNumber) {
        return new PageRequest(pageNumber, NUMBER_OF_RESULTS_PER_PAGE);
    }

}
