package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCnt = 3;

    static {
        users.add(new User(1, "kim", new Date(),"pass1","123456-1234567"));
        users.add(new User(2, "kim", new Date(),"pass2","223456-2234567"));
        users.add(new User(3, "kim", new Date(),"pass3","323456-3234567"));
        users.add(new User(4, "kim", new Date(),"pass4","423456-4234567"));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCnt);
        }

        users.add(user);

        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> it = users.iterator();

        while (it.hasNext()) {
            User user = it.next();

            if(user.getId()==id) {
                it.remove();;
                return user;
            }
        }

        return null;
    }
}
