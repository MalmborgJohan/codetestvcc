# Congestion Tax Calculator

# Improvements made
The Congestion Tax Calculator is now functioning and runnable with spring-boot. 
I structured the application in packages for an easier overview. I added a Controller class so that the application has an entrypoint through a Rest API endpoint (/calculate).
I removed the classes that represented vehicles (Car, Motorbike and vehicle) as these did not serve a purpose for the scope of the current application. I replaced it with a simple String input. 

# Bugs fixed
Bugs with the existing code that have been fixed is the year was parsed in a wierd way due to the use of a deprecated API (java.util.date) it parses the year - 1900. There was also no way to actually use one of the "Tax Exempt Vehicles". 
Since it's a String input now this is possible. 

# Future Improvements: 
Replace the deprecated java.util.date with an up to date dependency with for example the java.time library.
Add validation for the input of the /calculate API. Since vehicle is just a String now you can input whatever, so a
validation that what is entered is actually a valid vehicle would be preferential. 

# How to run
The application uses spring-boot so it can be run either with (If you have maven installed)
    `mvn spring-boot:run`
or by running it through the EDI (Right-click the App class and select Run)

Here is an example payload for the API

```json
{
  "vehicle": "Car",
  "dates": ["2013-03-27 06:59:00",
  "2013-03-27 08:00:00",
  "2013-03-27 09:31:00",
  "2013-03-27 15:31:00",
  "2013-03-27 17:31:00"]
}
```
vehicle can be replaced by any other vehicle in this list for testing purposes

- Emergency vehicles
- Busses
- Diplomat vehicles
- Motorcycles
- Military vehicles
- Foreign vehicles