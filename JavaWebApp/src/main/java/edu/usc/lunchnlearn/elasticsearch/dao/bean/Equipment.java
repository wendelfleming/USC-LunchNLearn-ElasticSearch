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

import edu.usc.lunchnlearn.elasticsearch.bean.Race;
import edu.usc.lunchnlearn.elasticsearch.bean.Stat;

import java.util.List;

/**
 * Created by wfleming on 7/2/15.
 */

public class Equipment extends BaseItem {

    private Long armor;

    private List<String> allowableClasses;

    private List<Race> allowableRaces;

    private List<String> availableContexts;

    private Long baseArmor;

    private List<Stat> bonusStats;

    private Long containerSlots;

    private Long disenchantingSkillRank;

    private Long displayInfoId;

    private String inventoryType;

    private Boolean isAuctionable;

    private Long itemLevel;

    private String nameDescription;

    private String nameDescriptionColor;

    private Long requiredLevel;


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



