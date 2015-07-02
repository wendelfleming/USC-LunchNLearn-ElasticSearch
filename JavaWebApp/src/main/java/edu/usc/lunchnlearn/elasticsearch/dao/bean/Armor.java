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

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by wfleming on 7/1/15.
 */

@Document(indexName = "wow", type = "Armor", shards = 1, replicas = 0)
public class Armor extends Equipment {



//    "bonusLists":
//    {
//        "type": "long"
//    },
//    "bonusSummary":
//    {
//        "properties":
//        {
//            "bonusChances":
//            {
//                "properties":
//                {
//                    "chanceType":
//                    {
//                        "type": "string"
//                    },
//                    "sockets":
//                    {
//                        "properties":
//                        {
//                            "socketType":
//                            {
//                                "type": "string"
//                            }
//                        }
//                    },
//                    "stats":
//                    {
//                        "properties":
//                        {
//                            "delta":
//                            {
//                                "type": "long"
//                            },
//                            "maxDelta":
//                            {
//                                "type": "long"
//                            },
//                            "statId":
//                            {
//                                "type": "string"
//                            }
//                        }
//                    },
//                    "upgrade":
//                    {
//                        "properties":
//                        {
//                            "id":
//                            {
//                                "type": "long"
//                            },
//                            "name":
//                            {
//                                "type": "string"
//                            },
//                            "upgradeType":
//                            {
//                                "type": "string"
//                            }
//                        }
//                    }
//                }
//            },
//            "chanceBonusLists":
//            {
//                "type": "long"
//            },
//            "defaultBonusLists":
//            {
//                "type": "long"
//            }
//        }
//    },
//    "boundZone":
//    {
//        "properties":
//        {
//            "id":
//            {
//                "type": "long"
//            },
//            "name":
//            {
//                "type": "string"
//            }
//        }
//    },
//    "context":
//    {
//        "type": "string"
//    },
//    "equippable":
//    {
//        "type": "boolean"
//    },
//    "hasSockets":
//    {
//        "type": "boolean"
//    },
//    "heroicTooltip":
//    {
//        "type": "boolean"
//    },
//    "itemSet":
//    {
//        "properties":
//        {
//            "id":
//            {
//                "type": "long"
//            },
//            "items":
//            {
//                "type": "long"
//            },
//            "name":
//            {
//                "type": "string"
//            },
//            "setBonuses":
//            {
//                "properties":
//                {
//                    "description":
//                    {
//                        "type": "string"
//                    },
//                    "threshold":
//                    {
//                        "type": "long"
//                    }
//                }
//            }
//        }
//    },
//    "itemSource":
//    {
//        "properties":
//        {
//            "sourceId":
//            {
//                "type": "long"
//            },
//            "sourceType":
//            {
//                "type": "string"
//            }
//        }
//    },
//    "itemSpells":
//    {
//        "properties":
//        {
//            "categoryId":
//            {
//                "type": "long"
//            },
//            "consumable":
//            {
//                "type": "boolean"
//            },
//            "nCharges":
//            {
//                "type": "long"
//            },
//            "spell":
//            {
//                "properties":
//                {
//                    "castTime":
//                    {
//                        "type": "string"
//                    },
//                    "cooldown":
//                    {
//                        "type": "string"
//                    },
//                    "description":
//                    {
//                        "type": "string"
//                    },
//                    "icon":
//                    {
//                        "type": "string"
//                    },
//                    "id":
//                    {
//                        "type": "long"
//                    },
//                    "name":
//                    {
//                        "type": "string"
//                    },
//                    "powerCost":
//                    {
//                        "type": "string"
//                    },
//                    "range":
//                    {
//                        "type": "string"
//                    },
//                    "subtext":
//                    {
//                        "type": "string"
//                    }
//                }
//            },
//            "spellId":
//            {
//                "type": "long"
//            },
//            "trigger":
//            {
//                "type": "string"
//            }
//        }
//    },
//    "maxCount":
//    {
//        "type": "long"
//    },
//    "maxDurability":
//    {
//        "type": "long"
//    },
//    "minFactionId":
//    {
//        "type": "long"
//    },
//    "minReputation":
//    {
//        "type": "long"
//    },
//    "requiredAbility":
//    {
//        "properties":
//        {
//            "description":
//            {
//                "type": "string"
//            },
//            "name":
//            {
//                "type": "string"
//            },
//            "spellId":
//            {
//                "type": "long"
//            }
//        }
//    },
//    "requiredSkill":
//    {
//        "type": "long"
//    },
//    "requiredSkillRank":
//    {
//        "type": "long"
//    },
//    "socketInfo":
//    {
//        "properties":
//        {
//            "socketBonus":
//            {
//                "type": "string"
//            },
//            "sockets":
//            {
//                "properties":
//                {
//                    "type":
//                    {
//                        "type": "string"
//                    }
//                }
//            }
//        }
//    },
//    "stackable":
//    {
//        "type": "long"
//    },
//    "upgradable":
//    {
//        "type": "boolean"
//    }
//}
//
//},


}
