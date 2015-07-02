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
package edu.usc.lunchnlearn.elasticsearch.tags;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * EL Custom tag class that handles URL encoding.
 *
 * @author wendel fleming
 *
 */
public class StringUtilities {

    private StringUtilities() {
        //hide the constructor since static class.
    }

    /**
     * encoded url in utf-8 format
     *
     * @param input
     * @return encoded string
     */
    public static String encodeUrl(String input) {
        String out = null;
        try {
            if (input != null) {
                out = URLEncoder.encode(input, "utf-8");
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return out;
    }


}
