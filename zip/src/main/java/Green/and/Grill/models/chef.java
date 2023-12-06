package Green.and.Grill.models;

import javax.persistence.Entity;

@Entity
public class chef extends mainUser {

    private String restaurantName;

    public chef(int id, String email, String password, String phone, String address, String restaurantName) {
        super(id, email, password, phone, address);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
