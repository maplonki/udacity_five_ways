# Udacity Five Ways Project

##Project Description
The main idea for the project, its an app that teaches people to cook, this is targeted to anyone who doesn’t cook or that don’t have idea of what to cook. 
With this app the user can enter ingredients and the app will show him 5 great recipe ideas.You will get an image for that recipe and the name, then you can access to the full recipe details via URL.

##App Design
This image shows the basic flow of the app
![alt tag] (https://raw.githubusercontent.com/maplonki/udacity_five_ways/master/app/app_basic_design.jpg)

##Project Setup
This project uses a Recipe Search API (https://developer.edamam.com/).

To use the API you can register and you will get an `APP ID` and an `APP KEY` which
then you can append in your request URL.

``` https://https://api.edamam.com/search/?q=your_search&app_id=your_id&app_key=your_key ```

These credentials are stored on a `keys.properties` file, just replace your credentials on the file
and you're all good to go.
