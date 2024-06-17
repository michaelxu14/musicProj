package com.mycompany.musicproj;

/**
 *
 * @author Xu Last Name
 * This class is for modifying the student database
 */



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Xu Last Name
 */
public class EquipmentUtility {

    private static final String API_URL = "https://rhhscs.com/database/dbaccess.php";
    private static final String PASSWORD = "MRRD";

    // Method to display all contents of the Equipment table

    /**
     *
     */
    public static void displayEquipment() {
        try {
            String query = "SELECT * FROM Equipment";
            String jsonPayload = new JSONObject()
                    .put("query", query)
                    .put("password", PASSWORD)
                    .toString();

            HttpURLConnection connection = sendPostRequest(jsonPayload);
            StringBuilder response = readResponse(connection);

            // Parse and display the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                String name = row.getString("name");
                boolean signedOut = row.getInt("availability") == 1; // Convert TINYINT to boolean
                int id = row.getInt("barcode"); // Assuming 'barcode' is the column name for id
                String type = row.getString("type");
                int year = row.getInt("year");
                String category = row.getString("category");

                System.out.println("Name: " + name);
                System.out.println("Category: " + category);
                System.out.println("Signed Out: " + signedOut);
                System.out.println("ID (Barcode): " + id);
                System.out.println("Type: " + type);
                System.out.println("Year: " + year);
                System.out.println("----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a new equipment item

    /**
     *
     * @param name
     * @param category
     * @param signedOut
     * @param id
     * @param type
     * @param year
     */
    public static void addEquipment(String name, String category, boolean signedOut, int id, String type, int year) {
        try {
            int availabilityInt = signedOut ? 1 : 0; // Convert boolean to TINYINT
            String query = String.format("INSERT INTO Equipment (name, category, availability, barcode, type, year) VALUES ('%s', '%s', %d, %d, '%s', %d)",
                    name, category, availabilityInt, id, type, year);
            String jsonPayload = new JSONObject()
                    .put("query", query)
                    .put("password", PASSWORD)
                    .toString();

            HttpURLConnection connection = sendPostRequest(jsonPayload);
            StringBuilder response = readResponse(connection);

            System.out.println("Response from server:");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to clear the Equipment table

    /**
     *
     */
    public static void clearEquipmentTable() {
        try {
            String query = "DELETE FROM Equipment";
            String jsonPayload = new JSONObject()
                    .put("query", query)
                    .put("password", PASSWORD)
                    .toString();

            HttpURLConnection connection = sendPostRequest(jsonPayload);
            StringBuilder response = readResponse(connection);

            System.out.println("Response from server:");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Send HTTP POST request, bits and pieces taken from Github
    private static HttpURLConnection sendPostRequest(String jsonPayload) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
            writer.write(jsonPayload);
            writer.flush();
        }

        return connection;
    }

    // Read response from the connection, bits and pieces taken from Github
    private static StringBuilder readResponse(HttpURLConnection connection) throws Exception {
        StringBuilder response = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        

        // Add new equipment
        //Name, cat, signed, id, type, year
        /*
        addEquipment("Music Book", "Misc", false, 23450, "Misc", 2006);
        addEquipment("Microphone", "Misc", false, 23451, "Misc", 2006);
        addEquipment("Alto Saxophone", "Woodwinds", false, 23452, "Instrument", 2006);
        addEquipment("Alto Saxophone", "Woodwinds", false, 23453, "Instrument", 2006);
        addEquipment("Alto Saxophone", "Woodwinds", false, 23454, "Instrument", 2006);
        addEquipment("Trumpet", "Brass", false, 23456, "Instrument", 2006);
        addEquipment("Tenor Saxophone", "Woodwinds", false, 23457, "Instrument", 2006);
        addEquipment("Stand", "Misc", false, 23458, "Misc", 2006);
        addEquipment("Snare Drum", "Percussion", false, 23459, "Instrument", 2006);
        */
        // Clear equipment table
        //clearEquipmentTable();
        
        // Display equipment
        displayEquipment();
    }
}