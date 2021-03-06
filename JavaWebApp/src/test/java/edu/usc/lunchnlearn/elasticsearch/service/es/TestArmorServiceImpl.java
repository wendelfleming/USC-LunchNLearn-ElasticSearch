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
package edu.usc.lunchnlearn.elasticsearch.service.es;

import edu.usc.lunchnlearn.elasticsearch.dao.bean.Armor;
import edu.usc.lunchnlearn.elasticsearch.service.ArmorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wfleming on 7/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-application-context.xml"})
@EnableJpaRepositories(basePackages = "edu.usc.lunchnlearn.springmvc.dao")
public class TestArmorServiceImpl {


    @Autowired
    private ArmorService armorService;

    @Test
    public void testFindAll() {
        assertNotNull(armorService);
        Page<Armor> findAll = armorService.findAll(1);
        assertEquals(4383, findAll.getTotalPages());
    }


    @Test
    public void testFindByItemId() {
        assertNotNull(armorService);
        List<Armor> findAll = armorService.findByItemId("88065");
        assertEquals(1, findAll.size());
    }

}
