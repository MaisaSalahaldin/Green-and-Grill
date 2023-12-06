package Green.and.Grill.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class chef extends mainUser {
public chef(){}
    @NotBlank(message = "restaurantName is required")
    private String restaurantName;

    public chef(String email, String password,
                String phone, String address,String zip, String restaurantName,String city) {
        super(email, password, phone, address,zip,city);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}
