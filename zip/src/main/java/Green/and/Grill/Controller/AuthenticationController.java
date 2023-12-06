package Green.and.Grill.Controller;
import Green.and.Grill.Data.ChefRepository;
import Green.and.Grill.Data.ClientRepository;
import Green.and.Grill.models.DTO.LoginFormDTO;
import Green.and.Grill.models.DTO.RegisterFormChefDTO;
import Green.and.Grill.models.DTO.RegisterFormClientDTO;
import Green.and.Grill.models.chef;
import Green.and.Grill.models.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;
@Controller
public class AuthenticationController {


    @Autowired
    ChefRepository chefRepository;
    @Autowired
    ClientRepository clientRepository;

    // The key to store user IDs
    private static final String chefSessionKey = "chef";
    private static final String clientSessionKey = "client";

// Look up user with key

    public chef getChefFromSession(HttpSession session) {
        Integer chefId = (Integer) session.getAttribute(chefSessionKey);
        if (chefId == null) {
            return null;
        }

        Optional<chef> chef = chefRepository.findById(chefId);

        if (chef.isEmpty()) {
            return null;
        }

        return chef.get();
    }
    // Stores key/value pair with session key and chef ID

    private static void setChefInSession(HttpSession session, chef chef) {
        session.setAttribute(chefSessionKey, chef.getId());
    }
    // Look up user with key
    public boolean getClientInSession(HttpSession session){
        Integer clientId = (Integer) session.getAttribute(clientSessionKey);
        if (clientId == null) {
            return false;
        }

        Optional<client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            return false;
        }

        return true;
    }

    // Stores key/value pair with session key and client ID

    private static void setClientInSession(HttpSession session, client client) {
        session.setAttribute(clientSessionKey, client.getId());
    }

//registration for client
    @GetMapping("/registerClient")
    public String displayRegistrationFormClient(Model model,HttpSession session) {
        model.addAttribute(new RegisterFormClientDTO());
        model.addAttribute("title", "Client Register");
        return "registerClient";
    }

    @PostMapping("/registerClient")
    public String processRegistrationFormClient(@ModelAttribute @Valid RegisterFormClientDTO registerFormClientDTO,

                                                Errors errors, HttpServletRequest request,
                                                Model model) {

        // Send client back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Client Register");
            return "registerClient";
        }
// Look up client in database using email they provided in the form
        client existingUser = clientRepository.findByEmail(registerFormClientDTO.getEmail());

        // Send client back to form if email already exists
        if (existingUser != null) {
            errors.rejectValue("email", "email.alreadyexists", "A clinet with that email already exists");
            model.addAttribute("title", "Client Register");
            return "registerClient";
        }
// Send client back to form if passwords didn't match
        String password = registerFormClientDTO.getPassword();
        String verifyPassword = registerFormClientDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Client Register");
            return "registerClient";
        }

        // OTHERWISE, save new email and hashed password in database, start a new session, and redirect to home page
        client newClient = new client(registerFormClientDTO.getEmail(), registerFormClientDTO.getPassword(),
                registerFormClientDTO.getPhone(),registerFormClientDTO.getFirstName(),
                registerFormClientDTO.getLastName(), registerFormClientDTO.getAddress(), registerFormClientDTO.getZip(),
                registerFormClientDTO.getCity(),registerFormClientDTO.isVegetarian());
        clientRepository.save(newClient);
        setClientInSession(request.getSession(), newClient);

        return "redirect:client/menu";
    }

    //registration for chef
    @GetMapping("/register")
    public String displayChefRegistrationForm(Model model,HttpSession session) {
        model.addAttribute(new RegisterFormChefDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationChefForm(@ModelAttribute @Valid RegisterFormChefDTO registerFormChefDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        // Send user back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }
// Look up user in database using email they provided in the form
        chef existingUser = chefRepository.findByEmail(registerFormChefDTO.getEmail());
        chef existingRestaurant = chefRepository.findByResturentName(registerFormChefDTO.getRestaurantName());

        // Send user back to form if email already exists
        if (existingUser != null ) {
            errors.rejectValue("email", "email.alreadyexists", "A Chef with that email already exists");
            model.addAttribute("title", "Register");
            return "register";
        }
        //check  duplicates
        if (existingRestaurant != null ) {
            errors.rejectValue("restaurantName", "restaurantName.alreadyexists", "This Restaurant Name is already exists");
            model.addAttribute("title", "Register");
            return "register";
        }
// Send chef back to form if passwords didn't match
        String password = registerFormChefDTO.getPassword();
        String verifyPassword = registerFormChefDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }


        // OTHERWISE, save new email and hashed password in database, start a new session, and redirect to home page

        chef newchef = new chef(registerFormChefDTO.getEmail(), registerFormChefDTO.getPassword(),registerFormChefDTO.getPhone()
                ,registerFormChefDTO.getAddress(), registerFormChefDTO.getZip()

                ,registerFormChefDTO.getRestaurantName(),registerFormChefDTO.getCity());

        chefRepository.save(newchef);

        setChefInSession(request.getSession(), newchef);

        return "redirect:chef/dashboard";
    }
    // Handlers for login form
    @GetMapping("/login")
    public String displayLoginForm(Model model, HttpSession session) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        if(session.getAttribute("chef") != null){

            model.addAttribute("loggedIn", session.getAttribute("chef") != null);
        }
        else{
            model.addAttribute("loggedIn", session.getAttribute("client") != null);}


        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {
// Send user back to form if errors are found
        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }
        // Look up user in database using email they provided in the form
        chef theChef = chefRepository.findByEmail(loginFormDTO.getEmail());
        client theClient=clientRepository.findByEmail((loginFormDTO.getEmail()));
        if (theChef == null && theClient==null) {
            errors.rejectValue("email", "email.invalid", "The given email does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        //if chef
        if(theChef!=null && theClient==null){

            String password = loginFormDTO.getPassword();


            if (!theChef.isMatchingPassword(password)) {
                errors.rejectValue("password", "password.invalid", "Invalid password");
                model.addAttribute("title", "Log In");
                return "login";
            }

            // OTHERWISE, create a new session for the chef and take them to the home page
            setChefInSession(request.getSession(), theChef);


            return "redirect:chef/dashboard";

        }
        //if client
        else {
            String password = loginFormDTO.getPassword();

            if (!theClient.isMatchingPassword(password)) {
                errors.rejectValue("password", "password.invalid", "Invalid password");
                model.addAttribute("title", "Log In");
                return "login";
            }

            // OTHERWISE, create a new session for the client and take them to the home page
            setClientInSession(request.getSession(), theClient);

// go to the menu
            return "redirect:client/menu";

        }
    }
    // Handler for logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:";
    }

}
