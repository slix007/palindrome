package org.intellectokids.palindrome.core.store;

import org.intellectokids.palindrome.core.model.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryInMemory implements UserRepository {

    private HashMap<String, User> users = new HashMap<>();

    public User getUser(String name) {
        return users.get(name);
    }


    public List<User> getTopUsers() {
        return users.values().stream()
                .sorted(Comparator.comparing(User::getScore).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public User addUser(String userName) {
        final User newUser = new User(userName);
        users.put(userName, newUser);
        return newUser;
    }

    public User addPhrase(User user, String groomedPhrase) {
        user.getUsedPhrases().add(groomedPhrase);
        final int res = user.getScore() + groomedPhrase.length();
        user.setScore(res);
        return user;
    }
}
