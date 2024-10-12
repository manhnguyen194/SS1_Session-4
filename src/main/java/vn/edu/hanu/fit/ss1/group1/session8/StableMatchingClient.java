package vn.edu.hanu.fit.ss1.group1.session8;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class StableMatchingClient {
    public static void main(String[] args) {
        String jsonFilePath = "Example.json";  // Replace with the actual path to Example.json

        try {
            // Read JSON data from Example.json file
            String jsonInputString = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
            System.out.println("JSON Data Sent: " + jsonInputString);  // Debugging to print JSON content

            // URL of the Express.js server
            URL url = new URL("http://localhost:3000/stable-matching");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            // Write JSON data to the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code from the server
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // If response code is 400 (Bad Request), read the error response
            if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String errorLine;
                StringBuilder errorResponse = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorResponse.append(errorLine);
                }
                System.out.println("Error Response: " + errorResponse.toString());
            } else {
                // Read the response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the response from the server
                System.out.println("Response Body: " + response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
