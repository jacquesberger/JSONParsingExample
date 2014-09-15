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

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
// Commentaire ajouté en classe
public class JSON {

    public static void main(String[] args) throws Exception {
        ArrayList<String> bookTitles = getBookTitles();
        printToConsole(bookTitles);
        saveToFiles(bookTitles);        
    }

    private static void saveToFiles(ArrayList<String> bookTitles) throws IOException {
        JSONArray outputList = new JSONArray();
        for (String title : bookTitles) {
            outputList.add(title);
        }
        saveAsRawJsonFile(outputList);
        saveAsIndentedJsonFile(outputList);        
    }

    private static void saveAsIndentedJsonFile(JSONArray outputList) throws IOException {
        FileWriter pretty = new FileWriter("json/output-pretty.json");
        pretty.write(outputList.toString(2));
        pretty.close();
    }

    private static void saveAsRawJsonFile(JSONArray outputList) throws IOException {
        FileWriter raw = new FileWriter("json/output-raw.json");
        outputList.write(raw);
        raw.close();
    }

    private static void printToConsole(ArrayList<String> bookTitles) {
        System.out.println("Voici les livres contenus dans le fichier original :");
        for (String title : bookTitles) {
            System.out.println(title);
        }
    }

    private static ArrayList<String> getBookTitles() throws IOException {
        ArrayList<String> bookTitles = new ArrayList<String>();
        String jsonTxt = FileReader.loadFileIntoString("json/library.json", "UTF-8");
        JSONArray root = (JSONArray) JSONSerializer.toJSON(jsonTxt);
        
        int documentCount = root.size();
        for (int i = 0; i < documentCount; i++) {
            JSONObject document = root.getJSONObject(i);
            if (document.getString("type").equals("book")) {
                bookTitles.add(document.getString("title"));
            }
        }
        
        return bookTitles;
    }
}
