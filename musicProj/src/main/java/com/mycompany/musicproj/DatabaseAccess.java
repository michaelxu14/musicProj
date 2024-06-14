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


//This class is purely for accessing the database remotely



public class DatabaseAccess {
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

            // Create an Appliance object,
            Appliance newAppliance = new Appliance("Microphone", false, 12345, 1, 2024);

            // Call the method to insert a new record and get the inserted object
            /*
            JSONObject insertedObject = addObject(newAppliance);
            if (insertedObject != null) {
                System.out.println("Inserted object:");
                System.out.println(insertedObject.toString());
            }
            */
            
            // Create a new Student table
            JSONObject response = createStudentTable();
            if (response != null) {
                System.out.println("Create Student table response:");
                System.out.println(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
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
    */


    // Method to create a new table called "Student"
    public static JSONObject createStudentTable() {
        JSONObject responseJson = null;

        try {
            // Define the SQL CREATE TABLE query
            String createTableQuery = "CREATE TABLE Student (" +
                                      "name VARCHAR(255)," +
                                      "id INT PRIMARY KEY," +
                                      "grade INT)";

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";
            String password = "MRRD";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + createTableQuery + "\", \"password\": \"" + password + "\"}";

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

            // Parse response to JSON
            responseJson = new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseJson;
    }
    
}
