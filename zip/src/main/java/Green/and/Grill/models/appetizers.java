package Green.and.Grill.models;

import javax.persistence.Entity;

@Entity
public class appetizers extends mainMenuItem{
    public appetizers(int id, String name, double price, String description, boolean isVegetarian) {
        super(id, name, price, description, isVegetarian);
    }
}
