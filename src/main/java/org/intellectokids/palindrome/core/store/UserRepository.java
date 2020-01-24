package org.intellectokids.palindrome.core.store;

import org.intellectokids.palindrome.core.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String name);

    List<User> getTopUsers();

    User addUser(String userName);

    User addPhrase(User user, String groomedPhrase);

}
