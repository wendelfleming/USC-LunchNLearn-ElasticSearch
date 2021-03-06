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

import edu.usc.lunchnlearn.elasticsearch.dao.WeaponRepository;
import edu.usc.lunchnlearn.elasticsearch.dao.bean.Weapon;
import edu.usc.lunchnlearn.elasticsearch.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wfleming on 7/2/15.
 */
public class WeaponServiceImpl extends BaseService implements WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    @Override
    public Page<Weapon> findAll(int pageNumber) {
        return weaponRepository.findAll(this.constructPageable(pageNumber));
    }

    @Override
    public List<Weapon> findByItemId(String itemId) {
        return weaponRepository.findByItemId(itemId);
    }

}
