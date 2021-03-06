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
package edu.usc.lunchnlearn.elasticsearch.dao;

import edu.usc.lunchnlearn.elasticsearch.dao.bean.BaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wfleming on 7/2/15.
 */
public interface MultiTypeSearch {

    Page<BaseItem> findAll(Pageable pageable);

    Page<BaseItem> findAll(String searchString, Pageable pageable);

    List<BaseItem> findByItemId(String itemId);


}
