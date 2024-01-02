# COMP2501_RealEstateProjectV1
A real estate management system with three interconnected classes: Agency, Property, and Address. The classes represent the structure of a system managing agencies that deal with various properties, each having specific attributes like price, address, bedrooms, swimming pool availability, type (residence, commercial, or retail), and a unique property ID.

Also, the test classes (AgencyTest, PropertyTest, and AddressTest) are designed to validate and verify the correctness of the corresponding main classes (Agency, Property, and Address). Each test class contains test methods that check various aspects of the functionality of the associated class.

## Main
Represent the structure of a system managing agencies

### Address Class:

Represents the address details of a property, including unit number, street number, street name, postal code, and city.

### Agency Class:

Manages a list of properties through a HashMap, where each property is associated with a unique property ID.
Includes methods to add, remove, and retrieve properties, as well as perform various queries such as getting the total property values, properties with pools, properties within a specified price range, and more.

### Property Class:

Represents a real estate property with various attributes such as price, address, number of bedrooms, swimming pool availability, type, and a unique property ID.
Includes methods to retrieve information about the property and set the price.

## Test
The test code uses JUnit 5 annotations (@BeforeEach, @AfterEach, and @Test) to set up and tear down the test environment and define individual test cases. The assertEquals and assertThrows methods are used to validate the expected outcomes of the tests.

### AdressTest

**1. Instance Variable Retrieval Tests**

getUnitNumber(), getStreetNumber(), getStreetName(), getPostalCode(), and getCity() tests check if the corresponding getter methods return the expected values for the Address instance variables.

**2. Expected Exceptions Tests**

Tests with the prefix getExpectedExceptions check if the Address constructor throws the expected exceptions for invalid input parameters during object creation.
- Invalid Unit Number: Empty string and a string with length > 4.
- Invalid Street Number: Negative value and a value > 999999.
- Invalid Street Name: Null, empty string, and a string with length > 20.
- Invalid Postal Code: Null, a string with length < 5, and a string with length > 6.
- Invalid City: Null, empty string, and a string with length > 30.

### AgencyTest

**1. Instance Variable Retrieval Tests**

These are tests that check if the instance variables (properties, in this case) can be correctly retrieved or manipulated through the methods provided by the Agency class. 

For example, the addGetProperty and removeProperty methods test the ability to add properties to the agency and retrieve them by property ID.

**2. Expected Exceptions Tests**

These are tests that check if the methods handle expected exceptions correctly.

For example, the tests for invalid unit numbers, street numbers, street names, postal codes, and cities in the Address class.
  
**3. Functionality Tests**

These are tests that assess the overall functionality of the Agency class. They include tests for calculating total property values, identifying properties with pools, filtering properties based on price range, street name, bedrooms, and property type.

### PropertyTest

**1. Instance Variable Retrieval Tests:**

These tests (getPriceUsd, getAddress, getNumberOfBedrooms, hasSwimmingPool, getType, getPropertyId) check if the corresponding getter methods in the Property class correctly retrieve the values assigned during the setup.

**2. Setter Method Test:**

The setPriceUsd test checks if the setPriceUsd method correctly updates the price of the property.

**3. Expected Exceptions Tests:**

These tests (getExpectedExceptionsPriceUsd, getExpectedExceptionsAddress, getExpectedExceptionsNumBedrooms, getExpectedExceptionsPropertyType, getExpectedExceptionsPropertyId) check if the Property constructor handles expected exceptions correctly. 

For example, it tests if the constructor correctly throws exceptions for negative prices, null addresses, invalid numbers of bedrooms, null property types, and invalid property IDs.

