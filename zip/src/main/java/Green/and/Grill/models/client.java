package Green.and.Grill.models;

import javax.persistence.Entity;

@Entity
public class client extends mainUser {
    private String firstName;
    private String lastName;
    private boolean isVegetarian;
    private String feedback;

    public client(int id, String email, String password, String phone, String address,
                  String firstName, String lastName, boolean isVegetarian) {
        super(id, email, password, phone, address);
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
