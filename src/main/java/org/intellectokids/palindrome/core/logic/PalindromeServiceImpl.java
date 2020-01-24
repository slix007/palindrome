package org.intellectokids.palindrome.core.logic;

import org.intellectokids.palindrome.core.dto.InDto;
import org.intellectokids.palindrome.core.dto.OutDto;
import org.intellectokids.palindrome.core.dto.Reasons;
import org.intellectokids.palindrome.core.dto.UserDto;
import org.intellectokids.palindrome.core.model.User;
import org.intellectokids.palindrome.core.store.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PalindromeServiceImpl implements PalindromeService {

    private UserRepository userRepository;

    public PalindromeServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * WARN: It is not thread safe.
     */
    public OutDto applyPalindrome(InDto in) {
        final String userName = in.getUserName();
        User user = userRepository.getUser(userName);
        if (user == null) { // create a new one
            user = userRepository.addUser(userName);
        }

        final String groomedPhrase = groomPhrase(in.getPhrase());

        // check1
        final boolean isPalindrome = isPalindrome(groomedPhrase);
        if (!isPalindrome) {
            return new OutDto(false, 0, user.getScore(), Reasons.IS_NOT_PALINDROM);
        }

        // check2
        final Boolean alreadyUsed = alreadyUsed(user, groomedPhrase);
        if (alreadyUsed) {
            return new OutDto(false, 0, user.getScore(), Reasons.ALREADY_USED);
        }

        // add
        final User userUpdated = userRepository.addPhrase(user, groomedPhrase);

        Integer addedScore = groomedPhrase.length();
        return new OutDto(true, addedScore, userUpdated.getScore(), "");
    }

    public boolean isPalindrome(String phrase) {
        final String origin = groomPhrase(phrase);
        final String reversed = new StringBuilder(origin).reverse().toString();
        return origin.equals(reversed);
    }

    private String groomPhrase(String phrase) {
        return phrase.trim().replaceAll("\\s", "").toLowerCase();
    }


    private Boolean alreadyUsed(User user, String phrase) {
        return user.getUsedPhrases().contains(phrase);
    }


    @Override
    public List<UserDto> getTopUsers() {
        return userRepository.getTopUsers().stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto userToUserDto(User user) {
        return new UserDto(user.getName(), user.getScore());
    }
}
