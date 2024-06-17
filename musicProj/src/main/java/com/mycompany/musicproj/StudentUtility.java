/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musicproj;

/**
 *
 * @author Xu Last Name
 * This class is for modifying the student database
 */



//This class is for modifying students 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Xu Last Name
 */
public class StudentUtility {

    private static final String API_URL = "https://rhhscs.com/database/dbaccess.php";
    private static final String PASSWORD = "MRRD";

    // Method to display all contents of the Student table

    /**
     *
     */
    public static void displayAllStudents() {
        try {
            String query = "SELECT * FROM Student";
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + PASSWORD + "\"}";

            HttpURLConnection connection = sendPostRequest(jsonPayload);

            StringBuilder response = readResponse(connection);
            System.out.println("Response from server:");
            System.out.println(response.toString());

            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject row = jsonArray.getJSONObject(i);
                System.out.println("Row " + (i + 1) + ":");
                int id = row.getInt("id");
                String name = row.getString("name");
                int grade = row.getInt("grade");
                System.out.println("ID: " + id + ", Name: " + name + ", Grade: " + grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a student to the Student table

    /**
     *
     * @param id
     * @param name
     * @param grade
     */
    public static void addStudent(int id, String name, int grade) {
        try {
            String query = String.format("INSERT INTO Student (id, name, grade) VALUES (%d, '%s', %d)", id, name, grade);
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + PASSWORD + "\"}";

            HttpURLConnection connection = sendPostRequest(jsonPayload);

            StringBuilder response = readResponse(connection);
            System.out.println("Response from server:");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to clear the Student table

    /**
     *
     */
    public static void clearStudentTable() {
        try {
            String query = "DELETE FROM Student";
            String jsonPayload = "{\"query\": \"" + query + "\", \"password\": \"" + PASSWORD + "\"}";

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
        // Example usage:
        // Display all students
        displayAllStudents();

        // Add a student
        //addStudent(12345, "John Doe", 10);

        // Clear the student table
        //clearStudentTable();
    }

}
