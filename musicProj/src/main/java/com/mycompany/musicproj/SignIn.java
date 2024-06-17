package com.mycompany.musicproj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;



//THIS VERSION HAS A CONSOLE INTERFACE

/**
 *
 * @author Xu Last Name
 */


public class SignIn {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        getTablesInDatabase();
        //clearTable();
        registrationSystem();
    }

    // Registration system method

    /**
     *
     */
    public static void registrationSystem() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayCirculationTable();
            System.out.println("Welcome to the Equipment Registration System");
            System.out.println("1. Sign Out Equipment");
            System.out.println("2. Sign In Equipment");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                // Sign out equipment
                System.out.print("Enter student barcode: ");
                String sbarcode = scanner.nextLine();

                System.out.print("Enter equipment barcode: ");
                String ebarcode = scanner.nextLine();

                System.out.print("Enter sign out date (YYYY-MM-DD): ");
                String signoutString = scanner.nextLine();
                
                try {
                    // Convert the string date to a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date signout = dateFormat.parse(signoutString);
                    // Get the next circulation ID
                    int circid = getNextCircId();
                   

                    // Prepare credentials with null circid (handled in signOut method)
                    Credentials credentials = new Credentials(circid, sbarcode, ebarcode, signout, null);

                    JSONObject response = signOut(credentials);
                    
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            //SIGN IN
            } else if (choice == 2) {
                // Sign in equipment
                System.out.print("Enter student barcode: ");
                String sbarcode = scanner.nextLine();
                
                System.out.print("Enter equipment barcode: ");
                String ebarcode = scanner.nextLine();

                System.out.print("Enter sign in date (YYYY-MM-DD): ");
                String signinString = scanner.nextLine();

                try {
                    // Convert the string date to a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date signin = dateFormat.parse(signinString);

                    // Find circid based on ebarcode
                    
                    int circid = getCircIdByEbarcode(ebarcode);

                    if (circid == -1) {
                        System.out.println("No circulation ID found for equipment barcode " + ebarcode);
                        continue;
                    }

                    // Prepare credentials with circid and null sbarcode and ebarcode (handled in signIn method)
                    Credentials credentials = new Credentials(circid, sbarcode, ebarcode, null, signin);

                    JSONObject response = signIn(credentials);
                    if (response != null) {
                        System.out.println("Equipment signed in successfully!");
                        System.out.println(response.toString());
                    } else {
                        System.out.println("Failed to sign in equipment.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                } 
            //EXIT
            } else if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                break;
                
            //INVALID INPUT
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Signs out equipment
    //must return a jsonobject

    /**
     *
     * @param credentials
     * @return
     */
    public static JSONObject signOut(Credentials credentials) {
        JSONObject responseJson = null;

         try {
        // Convert Date to String 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String signoutDateString = dateFormat.format(credentials.getSignout());
       
        
        // Create SQL INSERT query
        String query = "INSERT INTO Circulation (circid, sbarcode, ebarcode, signout) VALUES ("
                + credentials.getCircid() + ", '" + credentials.getSbarcode() + "', '" + credentials.getEbarcode() + "', '"
                + signoutDateString + "')";
        
        String updateQuery = "UPDATE Instruments SET availability = 0 WHERE ebarcode = '" + credentials.getEbarcode() + "'";

        // Create JSON payload
        String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + "MRRD" + "\"}";
        //String jsonPayload = "{\"queries\": [\"" + query + "\", \"" + updateQuery + "\"], \"password\": \"" + "MRRD" + "\"}";
        String jsonPayload2 = "{\"query\": \"" + updateQuery + "\", \"password\": \"" + "MRRD" + "\"}";
        //System.out.println(jsonPayload);
        //System.out.println(jsonPayload2);
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
        writer.write(jsonPayload2);
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

        // Print the response to debug
        
        //System.out.println("Response from server: " + response.toString());
        System.out.println(connection.getErrorStream());
        
        
        } catch (Exception e) {
            e.printStackTrace();
            
        }

        return responseJson;
    }

    // Signs in equipment

    /**
     *
     * @param credentials
     * @return
     */
    public static JSONObject signIn(Credentials credentials) {
        JSONObject responseJson = null;

        try {
            // Convert Date to String for SQL query
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String signinDateString = dateFormat.format(credentials.getSignin());

            // Create SQL UPDATE query
            String query = "UPDATE Circulation SET signin = '" + signinDateString + "' WHERE circid = "
                    + credentials.getCircid();

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + "MRRD" + "\"}";

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            
            
            //BORING STUFF BEGINS HERE
            // Create HTTP POST request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // Create HTTP POST 
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

            // Print the response to debug
            System.out.println("Response from server: " + response.toString());
            //BORING STUFF ENDS HERE
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    // Method to get the next available circulation ID

    /**
     *
     * @return
     */
    public static int getNextCircId() {
        int nextCircId = -1;

        try {
            // Define the SQL query to get the max circid
            String query = "SELECT MAX(circid) AS maxCircId FROM Circulation";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"MRRD\"}";

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
            JSONArray jsonArray = new JSONArray(response.toString());
            if (jsonArray.length() > 0) {
                JSONObject row = jsonArray.getJSONObject(0);
                nextCircId = row.getInt("maxCircId");
            } else {
                nextCircId = 1;  // If there are no records, start with 1
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextCircId + 1;
    }
    
    
    // Utility, clear a table if things go wrong

    /**
     *
     */
    public static void clearTable() {
        try {
            // Define the SQL query to truncate the table
            String query = "TRUNCATE TABLE Circulation";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"MRRD\"}";

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
            String responseStr;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                responseStr = response.toString().trim();
            }

            // Close the connection
            connection.disconnect();

            // Print the server response
            System.out.println("Server response: " + responseStr);

            // Check if the response indicates success
            if (responseStr.equalsIgnoreCase("success")) {
                System.out.println("Table 'Circulation' cleared successfully.");
            } else {
                System.out.println("Failed to clear the table 'Circulation'. Server response: " + responseStr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    //Just for monitoring if tables exist within the database

    /**
     *
     */
    public static void getTablesInDatabase() {
        try {
            // Define the SQL query to get the list of tables
            String query = "SHOW TABLES";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"MRRD\"}";

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

            // Print the response
            System.out.println("Response from server: " + response.toString());

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Print the list of tables
            System.out.println("Tables in the database:");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                String tableName = row.getString("Tables_in_u719074828_ics4");
                System.out.println(tableName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     */
    public static void displayCirculationTable() {
        try {
            // Define the SQL query to select all records from Circulation table
            String query = "SELECT * FROM Circulation";

            // Create JSON payload
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"MRRD\"}";

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            // Create HTTP POST request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to the connection
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(jsonPayload);
                writer.flush();
            }

            // Read response from the connection
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Close the connection
            connection.disconnect();

            // Print the server response
            System.out.println("Response from server: " + response.toString());

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Print the content of the Circulation table
            System.out.println("Contents of the Circulation table:");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                int circid = row.getInt("circid");
                String sbarcode = row.getString("sbarcode");
                String ebarcode = row.getString("ebarcode");
                String signout = row.getString("signout");
                String signin = row.optString("signin", "Not signed in");

                System.out.println("Circulation ID: " + circid);
                System.out.println("Student Barcode: " + sbarcode);
                System.out.println("Equipment Barcode: " + ebarcode);
                System.out.println("Sign Out Date: " + signout);
                System.out.println("Sign In Date: " + signin);
                System.out.println("-----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    //Broken, to be fixed
    //Not anymore, issue resolved

    /**
     *
     * @param ebarcode
     * @return
     */
    public static int getCircIdByEbarcode(String ebarcode) {
        int circid = -1;

        try {
            // Define the SQL query to retrieve circid based on ebarcode
            String query = "SELECT circid FROM Circulation WHERE ebarcode = '" + ebarcode + "' AND signin IS NULL";

            // Create JSON payload
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("query", query);
            jsonPayload.put("password", "MRRD");

            // Define the URL of the PHP script
            String apiUrl = "https://rhhscs.com/database/dbaccess.php";

            // Create HTTP POST request
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Write JSON payload to the connection
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(jsonPayload.toString());
                writer.flush();
            }

            // Read response from the connection
            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Parse JSON response to retrieve circid
            JSONArray results = new JSONArray(response.toString());
            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                circid = firstResult.getInt("circid");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return circid;
    }
    
    
    
}