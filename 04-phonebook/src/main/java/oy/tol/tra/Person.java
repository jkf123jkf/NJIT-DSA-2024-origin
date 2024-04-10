package oy.tol.tra;

/**
 * Class Person represents a person with first and last name.
 */
public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    /**
     * Copy constructor to create a new Person object with the same attributes as another Person object.
     * @param person The person object to copy.
     */
    public Person(final Person person) {
        this.firstName = new String(person.firstName);
        this.lastName = new String(person.lastName);
    }

    /**
     * Constructor to create a new Person object with specified first and last names.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the last name of the person.
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the first name of the person.
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the full name of the person (last name + first name).
     * @return The full name of the person.
     */
    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    /**
     * Calculates the hash code of the person based on their first and last names.
     * @return The hash code of the person.
     */
    @Override
    public int hashCode() {
        int hash=0;
        String hashString=firstName+lastName;
        for (int i = 0; i < hashString.length(); i++) {
            hash=37*hash+hashString.charAt(i);
        }
        return hash;
    }

    /**
     * Checks if this person is equal to another object.
     * @param other The object to compare with.
     * @return True if the other object is a Person with the same full name, otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.getFullName().equals(((Person)other).getFullName());
        }
        return false;
    }

    /**
     * Compares this person to another person based on their full names.
     * @param other The other person to compare with.
     * @return A negative integer, zero, or a positive integer as this person is less than, equal to, or greater than the other person.
     */
    @Override
    public int compareTo(Person other) {
        return getFullName().compareTo(other.getFullName());
    }
}
