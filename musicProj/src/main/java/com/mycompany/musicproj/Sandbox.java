package com.mycompany.musicproj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mycompany.musicproj.Appliance; // Import Appliance class

public class Sandbox {
    public static void main(String[] args) {
        try {
            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            // Define the SQL query and password
            String query = "SELECT * FROM Equipment";
            String password = "MRRD";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + password + "\"}";

            // Create HTTP POST request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to the connection
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(jsonPayload);
            writer.flush();

            // Read response from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close connections
            writer.close();
            reader.close();

            // Print the response
            System.out.println("Response from server:");
            System.out.println(response.toString());

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Loop through the JSON array Method 1
            // You use known database field names to pull info you want
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                System.out.println("Row " + (i + 1) + ":");

                // Assuming the columns are "id" and "name". Replace with your actual column names
                int id = row.getInt("barcode");
                String name = row.getString("name");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
            }

            // Loop through the JSON array Method 2
            // just grabs every key and value one by one.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                System.out.println("Row " + (i + 1) + ":");

                // Iterate over keys dynamically
                Iterator<String> keys = row.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object value = row.get(key);
                    System.out.println(key + ": " + value);
                }
            }

            // Create an Appliance object
            Appliance newAppliance = new Appliance("Microphone", false, 12345, 1, 2024);

            // Call the method to insert a new record and get the inserted object
            JSONObject insertedObject = addObject(newAppliance);
            if (insertedObject != null) {
                System.out.println("Inserted object:");
                System.out.println(insertedObject.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONObject addObject(Appliance appliance) {
        JSONObject insertedObject = null;

        try {
            // Create a JSON object for the inserted record
            insertedObject = new JSONObject();
            insertedObject.put("barcode", appliance.getId());
            insertedObject.put("category", appliance.getType());  // Assuming type is category
            insertedObject.put("type", appliance.getType());
            insertedObject.put("name", appliance.getName());
            insertedObject.put("availability", appliance.isSignedOut() ? 0 : 1);
            insertedObject.put("year", appliance.getYear());

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            // Define the SQL INSERT query and password
            String insertQuery = "INSERT INTO Equipment (barcode, category, type, name, availability, year) VALUES (" +
                    appliance.getId() + ", '" + appliance.getType() + "', '" + appliance.getType() + "', '" + appliance.getName() +
                    "', " + (appliance.isSignedOut() ? 0 : 1) + ", " + appliance.getYear() + ")";
            String password = "MRRD";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + insertQuery + "\", \"password\": \"" + password + "\"}";

            // Create HTTP POST request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to the connection
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(jsonPayload);
            writer.flush();

            // Read response from the connection
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Close connections
            writer.close();
            reader.close();

            // Print the response
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return insertedObject;
    }
}
