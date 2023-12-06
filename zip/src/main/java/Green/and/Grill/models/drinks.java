package Green.and.Grill.models;

import javax.persistence.Entity;

@Entity
public class drinks extends mainMenuItem{
    public drinks(int id, String name, double price, String description, boolean isVegetarian) {
        super(id, name, price, description, isVegetarian);
    }
}
