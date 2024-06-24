package com.example.universeapi.service;

import com.example.universeapi.api.exceptions.*;
import com.example.universeapi.api.model.User;
import com.example.universeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int id) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (!userOptional.isPresent()) throw new UnavailableUser();
            return userOptional.orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public User getUser(String login, String password) throws Exception {
        try {
            Optional<User> userOptionalEmail = userRepository.findByEmail(login);
            Optional<User> userOptionalUsername = userRepository.findByUsername(login);
            if (userOptionalEmail.isEmpty() && userOptionalUsername.isEmpty()) throw new LoginUserNotFound();
            System.out.println(password);
            String hashedPassword = User.hashPassword(password);
            User user;
            if(userOptionalEmail.isPresent()){
                user = userOptionalEmail.get();
            } else {
                user = userOptionalUsername.get();
            }
            if(!Objects.equals(user.getPassword().strip(), hashedPassword)) throw new LoginIncorrectPassword();
            return user;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(String username, String password, String email, String fullName, String role) throws Exception {
        try {
            checkFullName(fullName);
            checkUsername(username);
            checkPassword(password);
            checkEmail(email);
            checkRole(role);
            password = User.hashPassword(password.strip());
            User user = new User(username, password, email, fullName, role);
            userRepository.save(user);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void removeUser(int id) throws Exception {
        try {
            Optional<User> existingUser = userRepository.findById(id);
            if (existingUser.isEmpty()) {
                throw new UnavailableUser();
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateUser(int id, String choice, String value) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            try {
                User user = userOptional.get();
                switch (choice) {
                    case "username":
                        checkUsername(value);
                        user.setUsername(value);
                        break;
                    case "password":
                        checkPassword(value);
                        user.setPassword(value);
                        break;
                    case "email":
                        checkEmail(value);
                        user.setEmail(value);
                        break;
                    case "fullname":
                        checkFullName(value);
                        user.setFullName(value);
                        break;
                    case "bio":
                        checkBio(value);
                        user.setBio(value);
                        break;
                    default:
                        throw new InvalidUpdateOption();
                }
                userRepository.save(user);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }


    public boolean checkEmail(String email) throws InvalidEmailException, AlreadyAvailableEmail {
        String regex1 = "^[a-zA-Z0-9._%+-]+@+(edu\\.umi\\.ac\\.ma)$";
        String regex2 = "^[a-zA-Z0-9._%+-]+@+(umi\\.ac\\.ma)$";

        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher1 = pattern1.matcher(email);
        Matcher matcher2 = pattern2.matcher(email);

        boolean isEmailValid = (matcher1.matches() || matcher2.matches()) && email.length() <= 100;

        if(!isEmailValid) throw new InvalidEmailException();

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new AlreadyAvailableEmail();
        }

        return true;
    }

    public boolean checkPassword(String password) throws InvalidPassword {
        boolean containsUpperCase = false;
        boolean containsDigit = false;
        boolean isLengthValid = password.length() >= 8;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                containsUpperCase = true;
            } else if (Character.isDigit(ch)) {
                containsDigit = true;
            }
        }

        if(containsUpperCase && containsDigit && isLengthValid) return true;
        throw new InvalidPassword();
    }

    public boolean checkUsername(String username) throws InvalidUsername, AlreadyAvailableUsername {
        boolean isLengthValid = username.length() <= 20;
        boolean containsIllegalChars = !username.matches("^[a-zA-Z0-9_.]*$");

        if (!isLengthValid && !containsIllegalChars) throw new InvalidUsername();

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new AlreadyAvailableUsername();
        }

        return true;
    }

    public boolean checkFullName(String fullName) throws InvalidFullName {
        boolean isLengthValid = fullName.length() <= 30;
        boolean containsOnlyLetters = fullName.matches("^[a-zA-Z ]*$");

        if (!isLengthValid && containsOnlyLetters) throw new InvalidFullName();

        return true;
    }

    public boolean checkBio(String bio) throws InvalidBio {
        if (!(bio.length() <= 150)) throw new InvalidBio();

        return true;
    }

    public boolean checkRole(String role) throws InvalidRole {
        if (!(role.equals("student") || role.equals("professor") || role.equals("admin"))) throw new InvalidRole();

        return true;
    }

    public User getUserByUsername(String username) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (!userOptional.isPresent()) throw new UnavailableUser();
            return userOptional.orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
