package org.intellectokids.palindrome.core.logic;

import org.intellectokids.palindrome.core.dto.InDto;
import org.intellectokids.palindrome.core.dto.OutDto;
import org.intellectokids.palindrome.core.dto.UserDto;

import java.util.List;

public interface PalindromeService {
    OutDto applyPalindrome(InDto in);

    List<UserDto> getTopUsers();
}
