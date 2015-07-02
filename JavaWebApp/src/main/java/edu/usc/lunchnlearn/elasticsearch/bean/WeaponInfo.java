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
package edu.usc.lunchnlearn.elasticsearch.bean;

/**
 * Created by wfleming on 7/2/15.
 */

public class WeaponInfo {

    private Double dps;
    private Double weaponSpeed;
    private Damage damage;

//weaponInfo": {
//    "properties":
//    {
//        "damage":
//        {
//            "properties":
//            {
//                "exactMax":
//                {
//                    "type": "double"
//                },
//                "exactMin":
//                {
//                    "type": "double"
//                },
//                "max":
//                {
//                    "type": "long"
//                },
//                "min":
//                {
//                    "type": "long"
//                }
//            }
//        },
//        "dps":
//        {
//            "type": "double"
//        },
//        "weaponSpeed":
//        {
//            "type": "double"
//        }
//    }
//}


    public Double getDps() {
        return dps;
    }


    public void setDps(Double dps) {
        this.dps = dps;
    }


    public Double getWeaponSpeed() {
        return weaponSpeed;
    }


    public void setWeaponSpeed(Double weaponSpeed) {
        this.weaponSpeed = weaponSpeed;
    }


    public Damage getDamage() {
        return damage;
    }


    public void setDamage(Damage damage) {
        this.damage = damage;
    }


    class Damage {
        private Double exactMax;
        private Double exactMin;
        private Long max;
        private Long min;

        public Damage() {
        }

        public Double getExactMax() {
            return exactMax;
        }

        public void setExactMax(Double exactMax) {
            this.exactMax = exactMax;
        }

        public Double getExactMin() {
            return exactMin;
        }

        public void setExactMin(Double exactMin) {
            this.exactMin = exactMin;
        }

        public Long getMax() {
            return max;
        }

        public void setMax(Long max) {
            this.max = max;
        }

        public Long getMin() {
            return min;
        }

        public void setMin(Long min) {
            this.min = min;
        }
    }

}


