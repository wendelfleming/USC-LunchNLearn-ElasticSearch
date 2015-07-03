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
package edu.usc.lunchnlearn.elasticsearch.tags;

import java.util.Map;

/**
 * Created by wfleming on 7/2/15.
 */
public class Paginator {

    private Paginator() {
    }

    public static String paginate(int currentPage, int totalPages, Map<String, String> urlParams, String pathToImages) {
        StringBuilder stringBuilder = new StringBuilder();

        if(currentPage != 1) {
            int previousPage = currentPage - 1;

            stringBuilder.append("<a href='?currentPage=").append(previousPage).append(addParams(urlParams)).append("'>");
            stringBuilder.append("<img src='").append(pathToImages).append("icon_pagelft.gif' alt='Previous Page' border='0'/></a>&nbsp;&nbsp;");
            stringBuilder.append("<a href='?currentPage=").append(previousPage).append(addParams(urlParams)).append("'>Previous</a>&nbsp;&nbsp;");
        }
        else {
            stringBuilder.append("<img src='").append(pathToImages).append("icon_pagelft.gif' alt='Previous Page' border='0'/>&nbsp;&nbsp;Previous&nbsp;&nbsp;");
        }

        int startPage = currentPage - 2 < 1 ? 1 : currentPage - 2;
        int endPage = currentPage + 2 < 5 ? 5 : currentPage + 2;
        int lastStartPage = totalPages - 4 <= 0 ? 1 : totalPages - 4;

        int loopStart = startPage > lastStartPage ? lastStartPage : startPage;
        int loopEnd = endPage > totalPages ? totalPages : endPage;
        for(int i = loopStart; i <= loopEnd; i++ ) {
            if(i == currentPage) {
                stringBuilder.append(i).append("&nbsp;&nbsp;");
            }
            else {
                stringBuilder.append("<a href='?currentPage=").append(i).append(addParams(urlParams)).append("'>").append(i).append("</a>&nbsp;&nbsp;");
            }
        }

        if(currentPage != totalPages) {
            int nextPage = currentPage + 1;
            stringBuilder.append("<a href='?currentPage=").append(nextPage).append(addParams(urlParams)).append("'>Next</a>&nbsp;&nbsp;");
            stringBuilder.append("<a href='?currentPage=").append(nextPage).append(addParams(urlParams)).append("'>");
            stringBuilder.append("<img src='").append(pathToImages).append("icon_pagert.gif' alt='Next Page' border='0'/></a>");
        }
        else {
            stringBuilder.append("Next&nbsp;&nbsp;<img src='").append(pathToImages).append("icon_pagert.gif' alt='Next Page' border='0'/>");
        }

        return stringBuilder.toString();
    }


    private static String addParams(Map urlParams) {
        StringBuilder stringBuilder = new StringBuilder();

        for(Object key : urlParams.keySet()) {
            stringBuilder.append("&").append(key).append("=").append(StringUtilities.encodeUrl(""+urlParams.get(key)));
        }

        return stringBuilder.toString();
    }


}
