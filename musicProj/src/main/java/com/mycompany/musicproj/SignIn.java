package com.mycompany.musicproj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mycompany.musicproj.Appliance;
import com.mycompany.musicproj.Credentials;

/**
 *
 * @author Xu Last Name
 */
public class SignIn {
    public static void main(String[] args) {
        try {
            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            // Define the SQL query and password, access Circulation
            String query = "SELECT * FROM Circulation";
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
            // Use known database field names to pull info 
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                System.out.println("Row " + (i + 1) + ":");
                
                int id = row.getInt("circid");
                String date = row.getString("signin");

                System.out.println("ID: " + id);
                System.out.println("Signin Date: " + date);
            }

            // Loop through the JSON array Method 2
            // Just grabs every key and value one by one.
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Params are:
       circid
       sbarcode (student barcode)
       ebarcode (equipment barcode)
       signout (signout date)
       signin (signin date)
    */
    
    // Signs out equipment
    public static JSONObject signOut(Credentials credentials) {
        JSONObject responseJson = null;

        try {
            // Create SQL INSERT query
            String query = "INSERT INTO Circulation (circid, sbarcode, ebarcode, signout) VALUES (" +
                    credentials.getCircid() + ", '" + credentials.getSbarcode() + "', '" + credentials.getEbarcode() + "', '" +
                    credentials.getSignout() + "')";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + "MRRD" + "\"}";

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

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

    // Signs in equipment
    public static JSONObject signIn(Credentials credentials) {
        JSONObject responseJson = null;

        try {
            // Create SQL UPDATE query
            String query = "UPDATE Circulation SET signin = '" + credentials.getSignin() +
                    "' WHERE circid = " + credentials.getCircid();

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + "MRRD" + "\"}";

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

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