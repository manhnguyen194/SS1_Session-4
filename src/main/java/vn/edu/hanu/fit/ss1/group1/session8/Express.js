const express = require('express');
const bodyParser = require('body-parser');  // Import body-parser to handle JSON requests
const app = express();
const port = 3000;

// Use body-parser middleware to parse JSON request bodies
app.use(bodyParser.json());  // Parses application/json content-type

// Define the POST route to handle stable matching requests
app.post('/stable-matching', (req, res) => {
    const { ids, preferences } = req.body;  // Extract ids and preferences from request body

    // Validate input: Check that ids and preferences are both arrays of equal length
    if (!Array.isArray(ids) || !Array.isArray(preferences) || ids.length !== preferences.length) {
        return res.status(400).json({ error: 'Invalid input: ids and preferences must be arrays of equal length.' });
    }

    // For now, let's log the received ids and preferences for debugging purposes
    console.log("Received ids:", ids);
    console.log("Received preferences:", preferences);

    // Example of processing the stable matching (assuming `stableMatching` is a function)
    const matchingResult = stableMatching(ids, preferences);

    // Send the result back to the client
    res.json({
        result: matchingResult
    });
});

// Simple function to simulate stable matching logic (you can replace this with actual logic)
function stableMatching(ids, preferences) {
    // Return a simple object showing ids matched to their first preference for demo purposes
    return ids.map((id, index) => ({
        id: id,
        matchedWith: preferences[index][0]  // Just an example: matching with the first preference
    }));
}

// Start the Express.js server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
