<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate RestaurantChain</title>
</head>
<body>
    <form action="download.php" method="post">
        <div class="form-group">
            <label for="minNumOfRestaurantChains">Minimum Number of RestaurantChain:</label>
            <input type="number" id="minNumOfRestaurantChains" name="minNumOfRestaurantChains" min="1" max="100" value="1">
        </div>
        <div class="form-group">
            <label for="maxNumOfRestaurantChains">Maximum Number of RestaurantChain:</label>
            <input type="number" id="maxNumOfRestaurantChains" name="maxNumOfRestaurantChains" min="1" max="100" value="3">
        </div>
        <div class="form-group">
            <label for="minNumOfRestaurantLocations">Minimum Number of RestaurantLocation:</label>
            <input type="number" id="minNumOfRestaurantLocations" name="minNumOfRestaurantLocations" min="1" max="100" value="1">
        </div>
        <div class="form-group">
            <label for="maxNumOfRestaurantLocations">Maximum Number of RestaurantLocation:</label>
            <input type="number" id="maxNumOfRestaurantLocations" name="maxNumOfRestaurantLocations" min="1" max="100" value="3">
        </div>
        <div class="form-group">
            <label for="minNumOfEmployees">Minimum Number of Employees:</label>
            <input type="number" id="minNumOfEmployees" name="minNumOfEmployees" min="1" max="100" value="1">
        </div>
        <div class="form-group">
            <label for="maxNumOfEmployees">Maximum Number of Employees:</label>
            <input type="number" id="maxNumOfEmployees" name="maxNumOfEmployees" min="1" max="100" value="3">
        </div>
        
        <label for="format">Download Format:</label>
        <select id="format" name="format">
            <option value="html">HTML</option>
            <option value="markdown">Markdown</option>
            <option value="json">JSON</option>
            <option value="txt">Text</option>
        </select>

        <button type="submit">Generate</button>
    </form>
</body>
</html>