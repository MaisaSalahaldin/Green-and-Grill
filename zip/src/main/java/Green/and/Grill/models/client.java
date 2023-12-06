package Green.and.Grill.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class client extends mainUser {
    public client(){}
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "isVegetarian is required")
    private boolean isVegetarian;
    // I think this is not here
    //private String feedback;

    public client( String email, String password, String phone, String address,String zip,String city,
                  String firstName, String lastName, boolean isVegetarian) {
        super(email, password, phone, address,zip,city);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVegetarian = isVegetarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    //public String getFeedback() {
       // return feedback;
    //}

    //public void setFeedback(String feedback) {
       // this.feedback = feedback;
    //}
}
