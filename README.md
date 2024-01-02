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

**1. Instance Variable Retrieval Tests:** 
getUnitNumber(), getStreetNumber(), getStreetName(), getPostalCode(), and getCity() tests check if the corresponding getter methods return the expected values for the Address instance variables.

**2. Expected Exceptions Tests:**
  - Tests with the prefix getExpectedExceptions check if the Address constructor throws the expected exceptions for invalid input parameters during object creation.
  - Invalid Unit Number: Empty string and a string with length > 4.
  - Invalid Street Number: Negative value and a value > 999999.
  - Invalid Street Name: Null, empty string, and a string with length > 20.
  - Invalid Postal Code: Null, a string with length < 5, and a string with length > 6.
  - Invalid City: Null, empty string, and a string with length > 30.

### AgencyTest

**1. addGetProperty Test:**

Verifies that a property can be added to the agency and retrieved using its property ID.
removeProperty Test:

Checks the ability to remove a property from the agency using its property ID.
getTotalPropertyValues Test:

Validates that the total value of all properties in the agency is calculated correctly.
getPropertiesWithPools Test:

Ensures that the method returns properties with pools, comparing the result to an expected list.
getPropertiesBetween Test:

Tests the method that retrieves properties within a specified price range, comparing the result to an expected list.
getPropertiesOn Test:

Tests the method that retrieves properties on a specific street, comparing the result to an expected list.
getPropertiesWithBedrooms Test:

Verifies the method that retrieves properties within a specified range of bedrooms, comparing the result to an expected list.
getPropertiesOfType Test:

Tests the method that retrieves properties of a specific type (e.g., "commercial"), comparing the result to an expected list.
