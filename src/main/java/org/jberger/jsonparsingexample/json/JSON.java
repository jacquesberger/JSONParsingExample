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

import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class JSON {

    public static void main(String[] args) throws Exception {
        ArrayList<String> bookTitles = getBookTitles();
        printToConsole(bookTitles);
        saveToFiles(bookTitles);        
    }

    private static void saveToFiles(ArrayList<String> bookTitles) throws IOException {
        JSONArray bookTitleList = initializeBookList(bookTitles);
        saveAsRawJsonFile(bookTitleList);
        saveAsIndentedJsonFile(bookTitleList);        
    }

    private static JSONArray initializeBookList(ArrayList<String> bookTitles) {
        JSONArray bookTitleList = new JSONArray();
        for (String title : bookTitles) {
            bookTitleList.add(title);
        }
        return bookTitleList;       
    }

    private static void saveAsIndentedJsonFile(JSONArray outputList) throws IOException {
        Utf8File.saveStringIntoFile("json/output-pretty.json", outputList.toString(2));
    }

    private static void saveAsRawJsonFile(JSONArray outputList) throws IOException {
        Utf8File.saveStringIntoFile("json/output-raw.json", outputList.toString());
    }

    private static void printToConsole(ArrayList<String> bookTitles) {
        System.out.println("Voici les livres contenus dans le fichier original :");
        for (String title : bookTitles) {
            System.out.println(title);
        }
    }

    private static ArrayList<String> getBookTitles() throws IOException {
        ArrayList<String> bookTitles = new ArrayList<String>();
        String jsonTxt = Utf8File.loadFileIntoString("json/library.json");
        JSONArray root = (JSONArray) JSONSerializer.toJSON(jsonTxt);
        
        int documentCount = root.size();
        for (int i = 0; i < documentCount; i++) {
            addOneBook(root, i, bookTitles);
        }
        
        return bookTitles;
    }

    private static void addOneBook(JSONArray root, int i, ArrayList<String> bookTitles) {
        JSONObject document = root.getJSONObject(i);
        if (document.getString("type").equals("book")) {
            String title = document.getString("title");
            bookTitles.add(title);
        }
    }
}
