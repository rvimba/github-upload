/**
 * The DVD class defines a dvd object which stores all the 
 * information about a single dvd.
 * 
 * @author Ryan Vimba 
 * @andrewid rvimba
 */

public class DVD {
    /**
     * Properties to identify each unique DVD.
     */
    String name;
    int year_created;
    String director;
    String description;

    /**
     * Construct a DVD object.
     * 
     * @param name - the title of the movie
     * @param year - the year the movie was created
     * @param director - the director of the movie
     * @param description - the movie's description
     */
    public DVD(String name, int year, String director, String description) {
        this.name = name;
        this.year_created = year;
        this.director = director;
        this.description = description;
    }

    /**
     * Check the equality of DVDs.
     * 
     * @param o - Object o is the DVD to which we are comparing the DVD in the current context
     * @return - The result of the equality check
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DVD)) {
            return false;
        }
        DVD dvd = (DVD) o;
        return (name.equals(dvd.name) && (year_created == dvd.year_created) && 
                director.equals(dvd.director) && description.equals(dvd.description));
    }
}
