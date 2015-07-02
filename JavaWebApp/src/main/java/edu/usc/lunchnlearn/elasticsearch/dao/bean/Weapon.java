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
package edu.usc.lunchnlearn.elasticsearch.dao.bean;

import edu.usc.lunchnlearn.elasticsearch.bean.WeaponInfo;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by wfleming on 7/2/15.
 */

@Document(indexName = "wow", type = "Weapon", shards = 1, replicas = 0)
public class Weapon extends Equipment {

    private WeaponInfo weaponInfo;

    public WeaponInfo getWeaponInfo() {
        return weaponInfo;
    }

    public void setWeaponInfo(WeaponInfo weaponInfo) {
        this.weaponInfo = weaponInfo;
    }
}



