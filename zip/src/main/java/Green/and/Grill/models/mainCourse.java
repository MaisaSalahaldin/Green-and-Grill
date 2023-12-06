package Green.and.Grill.models;

import javax.persistence.Entity;

@Entity
public class mainCourse extends mainMenuItem{
    public mainCourse(int id, String name, double price, String description, boolean isVegetarian) {
        super(id, name, price, description, isVegetarian);
    }
}
