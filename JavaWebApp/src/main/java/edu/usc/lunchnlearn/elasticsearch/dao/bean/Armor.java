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

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.usc.lunchnlearn.elasticsearch.bean.Race;
import edu.usc.lunchnlearn.elasticsearch.bean.Stat;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

/**
 * Created by wfleming on 7/1/15.
 */

@Document(indexName = "wow", type = "Armor", shards = 1, replicas = 0)
public class Armor extends BaseItem {

//    @Id
//    @Field()
//    private String indexPath;
//
//    @JsonProperty("id")
//    private Long itemId;

    private Long armor;

//    private String name;

    private List<String> allowableClasses;

    private List<Race> allowableRaces;

    private List<String> availableContexts;

    private Long baseArmor;

    private List<Stat> bonusStats;

//    private Long buyPrice;

    private Long containerSlots;

//    private String description;

    private Long disenchantingSkillRank;

    private Long displayInfoId;

//    private String icon;

    private String inventoryType;

    private Boolean isAuctionable;

//    private String itemBind;
//
//    private String itemClass;
//
//    private String itemSubClass;

    private Long itemLevel;

    private String nameDescription;

    private String nameDescriptionColor;

//    private String quality;

    private Long requiredLevel;

//    private Long sellPrice;



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


    public Long getArmor() {
        return armor;
    }

    public void setArmor(Long armor) {
        this.armor = armor;
    }

    public List<String> getAllowableClasses() {
        return allowableClasses;
    }

    public void setAllowableClasses(List<String> allowableClasses) {
        this.allowableClasses = allowableClasses;
    }

    public List<Race> getAllowableRaces() {
        return allowableRaces;
    }

    public void setAllowableRaces(List<Race> allowableRaces) {
        this.allowableRaces = allowableRaces;
    }

    public List<String> getAvailableContexts() {
        return availableContexts;
    }

    public void setAvailableContexts(List<String> availableContexts) {
        this.availableContexts = availableContexts;
    }

    public Long getBaseArmor() {
        return baseArmor;
    }

    public void setBaseArmor(Long baseArmor) {
        this.baseArmor = baseArmor;
    }

    public List<Stat> getBonusStats() {
        return bonusStats;
    }

    public void setBonusStats(List<Stat> bonusStats) {
        this.bonusStats = bonusStats;
    }

    public Long getContainerSlots() {
        return containerSlots;
    }

    public void setContainerSlots(Long containerSlots) {
        this.containerSlots = containerSlots;
    }

    public Long getDisenchantingSkillRank() {
        return disenchantingSkillRank;
    }

    public void setDisenchantingSkillRank(Long disenchantingSkillRank) {
        this.disenchantingSkillRank = disenchantingSkillRank;
    }

    public Long getDisplayInfoId() {
        return displayInfoId;
    }

    public void setDisplayInfoId(Long displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Boolean getIsAuctionable() {
        return isAuctionable;
    }

    public void setIsAuctionable(Boolean isAuctionable) {
        this.isAuctionable = isAuctionable;
    }

    public Long getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Long itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getNameDescription() {
        return nameDescription;
    }

    public void setNameDescription(String nameDescription) {
        this.nameDescription = nameDescription;
    }

    public String getNameDescriptionColor() {
        return nameDescriptionColor;
    }

    public void setNameDescriptionColor(String nameDescriptionColor) {
        this.nameDescriptionColor = nameDescriptionColor;
    }

    public Long getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Long requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}
