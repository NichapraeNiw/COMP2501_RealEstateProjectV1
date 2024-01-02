import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class: Agency
 *
 * Instance Variables:
 *      1. Name (String, 1 to 30 characters)
 *      2. Properties (HashMap of properties; key is property id, value is a Property)
 *
 * Methods:
 *      1. addProperty(property): adds the (non-null) property to the HashMap
 *      2. removeProperty(propertyId): removes the property whose ID matches the parameter, from the HashMap
 *      3. getProperty(propertyId): returns the property whose ID matches the parameter, from the HashMap (or null if there is no match)
 *      4. getTotalPropertyValues(): returns the total amount in USD of all Properties
 *      5. getPropertiesWithPools(): returns an ArrayList of such Properties...or null if there are none
 *      6. getPropertiesBetween(minUsd, maxUsd): returns an array of properties whose price falls in the range specified by the parameters...or null if there are none
 *      7. getPropertiesOn(streetName): returns an ArrayList of addresses which are on the specified street...or null if there are none
 *      8. getPropertiesWithBedrooms(minBedrooms, maxBedrooms): returns a HashMap of properties
 *          (key is property id, value is the Property) whose number of bedrooms falls in the range specified
 *          by the parameters...or null if there are none. Note that the order of the properties may differ
 *          from since a HashMap doesn’t store “in order,” but the contents must otherwise match those in the sample run in the tests.
 *      9. getPropertiesOfType(propertyType): returns an ArrayList of Strings, with all the information
 *          about every property (one property per line) that is of the specified type (e.g. “commErciAl”: be
 *          case-insensitive) in the exact format of:
 *
 *          Type: COMMERCIAL
 *              1) Property 9999: unit #9 at 99 Gretzky Way T6V7H3 in Toronto (1 bedroom): $99999.
 *              2) Property 678T: 1515 Main Street V8Y7R3 in West Vancouver (2 bedrooms plus pool): $4000000.
 *              3) Property A1212: unit #7h at 1500 Railway Avenue V9V5V4 in Richmond (4 bedrooms): $840000.
 *
 *          Note that the sample output above is exactly what should be returned for the data shown
 *          below; it must create similar sentence structures for any property of any type. Notice the
 *          capitalization of various parts of the string (see above) versus how it was stored (see below).
 *
 *          If there are NO properties of the specified type the output must be as follows:
 *
 *          Type: RETAIL
 *          <none found>
 *
 * @author Nattanicha Nilsriphaiwan
 * @version 1.0
 */
public class Agency {

    private String name;
    private HashMap<String, Property> properties;

    private static double TOTAL_PRICE_USD = 0.00;
    private static int PROPERTIES_BETWEEN_FIRST_INDEX = 0;
    private static int PROPERTIES_BETWEEN_TOTAL_INDEX = 0;
    private static final int SMALLEST_SIZE_OF_MATCHING_PROPERTIES = 1;

    /**
     * constructor
     * @param name - name of the class
     */
    public Agency(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new NullPointerException("Invalid Name: " + name);
        } else {
            this.name = name;
        }
        this.properties = new HashMap<>();
    }

    /**
     * addProperty method
     * @param property - adds the (non-null) property to the HashMap
     */
    void addProperty(final Property property)
    {
        if (property == null) {
            throw new NullPointerException("Property cannot be null");
        }
        else {
            properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * getProperty method
     * @param propertyId    - property id you want to find
     * @return              - the property whose ID matches the parameter, from the HashMap (or null if there is no match)
     */
    Property getProperty(final String propertyId)
    {
        for (String key : properties.keySet())
        {
            if (key.equals(propertyId)) {
                return properties.get(key);
            }
        }
        return null;
    }

    /**
     * removeProperty method
     * @param propertyId - property id you want to remove
     */
    void removeProperty(final String propertyId)
    {
        if (propertyId == null || propertyId.isBlank())
        {
            throw new NullPointerException("Property cannot be null");
        }
        else if (!properties.containsKey(propertyId))
        {
            throw new IllegalArgumentException("Property ID doesn't match");
        }
        else
        {
            properties.remove(propertyId);
        }
    }

    /**
     * getTotalPropertyValues method
     * @return - the total amount in USD of all Properties
     */
    double getTotalPropertyValues() {
        for (String key : properties.keySet())
        {
            double price = properties.get(key).getPriceUsd();
            TOTAL_PRICE_USD += price;
        }
        return TOTAL_PRICE_USD;
    }

    /**
     * getPropertiesWithPools method
     * @return - an ArrayList of such Properties, or null if there are none
     */
    ArrayList<Property> getPropertiesWithPools()
    {
        ArrayList<Property> propertiesWithPools = new ArrayList<>();
        for (String key : properties.keySet())
        {
            if (properties.get(key).hasSwimmingPool())
            {
                propertiesWithPools.add(properties.get(key));
            }
        }

        return propertiesWithPools;
    }

    /**
     * getPropertiesBetween method
     * @param minUsd - minimum price in usd
     * @param maxUsd - maximum price in usd
     * @return       - an array of properties whose price falls in the range specified by the parameters, or null if there are none
     */
    Property[] getPropertiesBetween(final int minUsd, final int maxUsd)
    {
        for (String key : properties.keySet())
        {
            double priceUsd = properties.get(key).getPriceUsd();
            if (priceUsd >= minUsd && priceUsd <= maxUsd)
            {
                PROPERTIES_BETWEEN_TOTAL_INDEX++;
            }
        }

        Property[] propertiesBetween = new Property[PROPERTIES_BETWEEN_TOTAL_INDEX];
        for (String key : properties.keySet())
        {
            double priceUsd = properties.get(key).getPriceUsd();
            if (priceUsd >= minUsd && priceUsd <= maxUsd)
            {
                propertiesBetween[PROPERTIES_BETWEEN_FIRST_INDEX] = properties.get(key);
                PROPERTIES_BETWEEN_FIRST_INDEX++;
            }
        }

        return propertiesBetween;
    }

    /**
     * getPropertiesOn method
     * @param streetName - street name you want to find
     * @return           - an ArrayList of addresses which are on the specified street, or null if there are none
     */
    ArrayList<Address> getPropertiesOn(final String streetName)
    {
        ArrayList<Address> propertiesOn = new ArrayList<>();
        for (String key : properties.keySet())
        {
            Property property = properties.get(key);
            if (property.getAddress().getStreetName().equalsIgnoreCase(streetName))
            {
                propertiesOn.add(property.getAddress());
            }
        }
        // if streetName is not found, return null
        if (propertiesOn.isEmpty())
        {
            return null;
        }

        return propertiesOn;
    }

    /**
     * getPropertiesWithBedrooms method
     * @param minBedrooms   - minimum number of bedroom
     * @param maxBedrooms   - maximum number of bedroom
     * @return              - a HashMap of properties (key is property id, value is the Property)
     *                        whose number of bedrooms falls in the range specified by the parameters...or null if there are none
     */
    HashMap<String, Property> getPropertiesWithBedrooms(final int minBedrooms, final int maxBedrooms)
    {
        HashMap<String, Property> propertiesWithBedrooms = new HashMap<>();
        for (String key : properties.keySet())
        {
            int propertyNumberOfBedrooms = properties.get(key).getNumberOfBedrooms();
            if (propertyNumberOfBedrooms >= minBedrooms && propertyNumberOfBedrooms <= maxBedrooms)
            {
                propertiesWithBedrooms.put(properties.get(key).getPropertyId(), properties.get(key));
            }
        }
        // if streetName is not found, return null
        if (propertiesWithBedrooms.isEmpty())
        {
            return null;
        }

        return propertiesWithBedrooms;
    }

    /**
     * getPropertiesOfType method
     * @param propertyType  - property type tou want to find
     * @return              - an ArrayList of Strings, with all the information about every property (one property per line)
     *                        that is one of the specified type (“residence”, “commercial”, or “retail”) in the exact format
     */
    public ArrayList<String> getPropertiesOfType(final String propertyType)
    {
        ArrayList<String> matchingProperties = new ArrayList<>();
        String string;
        string = "Type: " + propertyType.toUpperCase() + "\n";
        matchingProperties.add(string);
        for (String key : properties.keySet()) {
            Property property = properties.get(key);
            if (properties.get(key).getType().equalsIgnoreCase(propertyType))
            {
                // HAS UNIT
                if (property.getAddress().getUnitNumber() != null)
                {
                    // has unit number, HAS SWIMMING POOL
                    if (property.hasSwimmingPool())
                    {
                        // has unit number, has swimming pool, HAS 1 BEDROOM
                        if (property.getNumberOfBedrooms() == 1)
                        {
                            // ") Property 9999: unit #9 at 99 Gretzky Way T6V7H3 in Toronto (1 bedroom plus pool): $99999.\n"
                            string = String.format(") Property %s: unit #%s at %d %s %s in %s (%d bedroom plus pool): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getUnitNumber(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                        // has unit number, has swimming pool, HAS MORE THAN 1 BEDROOM
                        else
                        {
                            // ") Property 9999: unit #9 at 99 Gretzky Way T6V7H3 in Toronto (4 bedrooms plus pool): $99999.\n"
                            string = String.format(") Property %s: unit #%s at %d %s %s in %s (%d bedrooms plus pool): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getUnitNumber(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                    }
                    // has unitNumber, HAS NO SWIMMING POOL
                    else
                    {
                        // has unit number, has NO swimming pool, HAS 1 BEDROOM
                        if (property.getNumberOfBedrooms() == 1)
                        {
                            // ") Property 9999: unit #9 at 99 Gretzky Way T6V7H3 in Toronto (1 bedroom): $99999.\n"
                            string = String.format(") Property %s: unit #%s at %d %s %s in %s (%d bedroom): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getUnitNumber(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                        // has unit number, has NO swimming pool, HAS MORE THAN 1 BEDROOM
                        else
                        {
                            // ") Property 9999: unit #9 at 99 Gretzky Way T6V7H3 in Toronto (4 bedrooms): $99999.\n"
                            string = String.format(") Property %s: unit #%s at %d %s %s in %s (%d bedrooms): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getUnitNumber(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                    }
                }
                // HAS NO UNIT
                else
                {
                    // has NO unit number, HAS SWIMMING POOL
                    if (property.hasSwimmingPool())
                    {
                        // has NO unit number, has swimming pool, HAS 1 BEDROOM
                        if (property.getNumberOfBedrooms() == 1)
                        {
                            // ") Property 9999: 99 Gretzky Way T6V7H3 in Toronto (1 bedroom plus pool): $99999.\n"
                            string = String.format(") Property %s: %d %s %s in %s (%d bedroom plus pool): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                        // has NO unit number, has swimming pool, HAS MORE THAN 1 BEDROOM
                        else
                        {
                            // ") Property 9999: 99 Gretzky Way T6V7H3 in Toronto (4 bedroom plus pool): $99999.\n"
                            string = String.format(") Property %s: %d %s %s in %s (%d bedrooms plus pool): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                    }
                    // has NO unitNumber, HAS NO SWIMMING POOL
                    else
                    {
                        // has NO unit number, has NO swimming pool, HAS 1 BEDROOM
                        if (property.getNumberOfBedrooms() == 1)
                        {
                            // ") Property 9999: 99 Gretzky Way T6V7H3 in Toronto (1 bedroom): $99999.\n"
                            string = String.format(") Property %s: %d %s %s in %s (%d bedroom): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                        // has NO unit number, has NO swimming pool, HAS MORE THAN 1 BEDROOM
                        else
                        {
                            // ") Property 9999: 99 Gretzky Way T6V7H3 in Toronto (4 bedroom plus pool): $99999.\n"
                            string = String.format(") Property %s: %d %s %s in %s (%d bedrooms): $%.0f.\n",
                                    property.getPropertyId(), property.getAddress().getStreetNumber(),
                                    toTitleCase(property.getAddress().getStreetName()), property.getAddress().getPostalCode().toUpperCase(),
                                    toTitleCase(property.getAddress().getCity()), property.getNumberOfBedrooms(), property.getPriceUsd());
                            matchingProperties.add(string);
                        }
                    }
                }
            }
        }
        // if property type cannot be found, show "<none found>"
        if (matchingProperties.size() == SMALLEST_SIZE_OF_MATCHING_PROPERTIES)
        {
            matchingProperties.add("<none found>");
        }

        return matchingProperties;
    }

    /**
     * a method to make a string in title case
     * @param string - String
     * @return      - title string
     */
    String toTitleCase(String string) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : string.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
