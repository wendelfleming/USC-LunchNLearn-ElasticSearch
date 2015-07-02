/**
 * Copyright 2015 University of Southern California
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
 * Bean that holds breadcrumb information.  It also holds a link to the parent breadcrumb.  Breadcrumbs
 * only care about the parent ahead of it.  This allows a daisy chain where each crumb only knows what
 * came before it.
 *
 * @author wendel fleming
 *
 */

public class Crumb {

    private long x;
    private long parentX;
    private String url;
    private String displayText;

    /**
     * Gets the time in milliseconds for this breadcrumb.  This is the unique value for this breadcrumb since
     * no two breadcrumbs from same user will have the same time in milliseconds.
     * @return  Breadcrumb key (time in milliseconds).
     */
    public long getX() {
        return x;
    }

    /**
     * Sets the time in milliseconds for this breadcrumb.
     * @param x     The time in milliseconds which will be the unique key for breadcrumbs.
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Gets the parent's unique key (ie time in milliseconds).
     * @return  Parent's breadcrumb key.
     */
    public long getParentX() {
        return parentX;
    }

    /**
     * Sets the parent's unique key (ie time in milliseconds).
     * @param parentX   The parent breadcrumb key.
     */
    public void setParentX(long parentX) {
        this.parentX = parentX;
    }

    /**
     * The URL that the breadcrumb links to.
     * @return  A url string.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL the breadcrumb links to.
     * @param url   A url string.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * The text that is displayed on the breadcrumb.
     * @return  The display text string.
     */
    public String getDisplayText() {
        return displayText;
    }

    /**
     * Sets the display text for the breadcrumb.
     * @param displayText   The display text string.
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
