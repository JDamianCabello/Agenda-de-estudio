package es.jdamiancabello.agendadeestudio.data.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import es.jdamiancabello.agendadeestudio.data.model.User;

public class UserRepository {
    private static UserRepository userRepository;
    private List<User> userList;

    static {
        userRepository = new UserRepository();
    }

    private UserRepository(){
        userList = new ArrayList<>();
        Initialize();
    }

    public static UserRepository getInstance(){
        return userRepository;
    }

    private void Initialize(){
        userList.add(new User("test","test@test.test","test"));
    }

    public List<User> getUserList(){
        return userList;
    }

    public boolean UserLogin(String email, String password){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getEmail().equals(email) && userList.get(i).getPassword().equals(password)
                    || userList.get(i).getUserName().equals(email) && userList.get(i).getPassword().equals(password))
                return true;
        }
        return false;
    }

    public boolean userAdd(String name, String email, String password){
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getEmail().equals(email))
                return false;
        }
        userList.add(new User(name,email,password));
        return true;
    }

    public User getUser(String user, String pass) {
        for(User u : userList){
            if(u.getEmail().equals(user) && u.getPassword().equals(pass)
            || u.getUserName().equals(user) && u.getPassword().equals(pass))
                return u;
        }
        return null;
    }
}
