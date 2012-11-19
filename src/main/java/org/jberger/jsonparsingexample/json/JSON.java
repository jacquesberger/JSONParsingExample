/*
 * Copyright 2011 Jacques Berger.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jberger.jsonparsingexample.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSON {

    public static void main(String[] args) throws Exception {
        String jsonTxt = FileReader.loadFileIntoString("json/library.json", "UTF-8");

        JSONArray root = (JSONArray) JSONSerializer.toJSON(jsonTxt);
        int documentCount = root.size();
        for (int i = 0; i < documentCount; i++) {
            JSONObject document = root.getJSONObject(i);
            if (document.getString("type").equals("book")) {
                System.out.println(document.getString("title") + " publié en " + document.getInt("year"));
            }
        }
    }
}
