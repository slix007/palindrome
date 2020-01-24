package org.intellectokids.palindrome.core.logic;

import org.intellectokids.palindrome.core.dto.InDto;
import org.intellectokids.palindrome.core.dto.OutDto;
import org.intellectokids.palindrome.core.dto.UserDto;
import org.intellectokids.palindrome.core.store.UserRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeServiceTest {

    private PalindromeServiceImpl palindromeService;

    @BeforeEach
    void setUp() {
        palindromeService = new PalindromeServiceImpl(new UserRepositoryInMemory());
    }

    @Test
    void testIsPalindrome1() {
        assertFalse(palindromeService.isPalindrome("PalindromeCheckImpl"));
        assertTrue(palindromeService.isPalindrome("топот"));
        assertFalse(palindromeService.isPalindrome("топоток"));
        assertTrue(palindromeService.isPalindrome("   топот   "));
        assertTrue(palindromeService.isPalindrome("а роза упала на лапу Азора"));
        assertTrue(palindromeService.isPalindrome("а роза упала     на лапу Азора   "));
        assertFalse(palindromeService.isPalindrome("а роза упала     на лапу Азор"));
    }

    @Test
    void testCheck1() {
        final OutDto out = palindromeService.applyPalindrome(new InDto("Иван", "топот"));
        assertTrue(out.getResult());
        assertEquals(5, out.getAddedScore());
    }

    @Test
    void testCheck2() {
        final OutDto out = palindromeService.applyPalindrome(new InDto("Иван", "топоток"));
        assertFalse(out.getResult());
        assertEquals(0, out.getAddedScore());
    }

    @Test
    void testCheckMany() {
        final OutDto out = palindromeService.applyPalindrome(new InDto("Иван", "топот"));
        assertTrue(out.getResult());
        assertEquals(5, out.getAddedScore());

        final OutDto out2 = palindromeService.applyPalindrome(new InDto("Иван", "топоток"));
        assertFalse(out2.getResult());
        assertEquals("Is not palindrome", out2.getReason());
        assertEquals(0, out2.getAddedScore());
        assertEquals(5, out2.getSumScore());

        final OutDto out3 = palindromeService.applyPalindrome(new InDto("Иван", "а роза упала     на лапу Азора   "));
        assertTrue(out3.getResult());
        assertEquals(21, out3.getAddedScore());
        assertEquals(26, out3.getSumScore());

        final OutDto out4 = palindromeService.applyPalindrome(new InDto("Андрей", "а роза упала     на лапу Азора   "));
        assertTrue(out4.getResult());
        assertEquals(21, out4.getAddedScore());
        assertEquals(21, out4.getSumScore());

        final OutDto out5 = palindromeService.applyPalindrome(new InDto("Иван", "а роза упала     на лапу Азора   "));
        assertFalse(out5.getResult());
        assertEquals("Already used", out5.getReason());
        assertEquals(0, out5.getAddedScore());
        assertEquals(26, out5.getSumScore());

    }

    @Test
    void testTop() {
        final OutDto out = palindromeService.applyPalindrome(new InDto("Иван", "топот"));
        final OutDto out3 = palindromeService.applyPalindrome(new InDto("Иван", "а роза упала     на лапу Азора   "));
        final OutDto out6 = palindromeService.applyPalindrome(new InDto("Иван", "Aа роза упала     на лапу Азора   A"));
        assertEquals(49, out6.getSumScore());
        final OutDto out4 = palindromeService.applyPalindrome(new InDto("Андрей", "а роза упала     на лапу Азора   "));
        assertEquals(21, out4.getSumScore());
        final OutDto out7 = palindromeService.applyPalindrome(new InDto("Юля", "Aа роза упала     на лапу Азора   A"));
        assertEquals(23, out7.getSumScore());
        final OutDto out8 = palindromeService.applyPalindrome(new InDto("Марина", "тт"));
        assertEquals(2, out8.getSumScore());
        final OutDto out9 = palindromeService.applyPalindrome(new InDto("Миша", "тхт"));
        assertEquals(3, out9.getSumScore());
        final OutDto out10 = palindromeService.applyPalindrome(new InDto("Игорь", "Ртопотр"));
        assertEquals(7, out10.getSumScore());

        List<UserDto> topUsers = palindromeService.getTopUsers();
        System.out.println(Arrays.toString(topUsers.toArray()));

        assertEquals(5, topUsers.size());
        assertEquals("Иван", topUsers.get(0).getName());
        assertEquals(49, topUsers.get(0).getScore());
        assertEquals("Юля", topUsers.get(1).getName());
        assertEquals(23, topUsers.get(1).getScore());
        assertEquals("Андрей", topUsers.get(2).getName());
        assertEquals(21, topUsers.get(2).getScore());
        assertEquals("Игорь", topUsers.get(3).getName());
        assertEquals(7, topUsers.get(3).getScore());
        assertEquals("Миша", topUsers.get(4).getName());
        assertEquals(3, topUsers.get(4).getScore());

        // top updated
        final OutDto out11 = palindromeService.applyPalindrome(new InDto("Марина", "тПРрРПт"));
        topUsers = palindromeService.getTopUsers();
        System.out.println(Arrays.toString(topUsers.toArray()));
        assertEquals(9, out11.getSumScore());
        assertEquals("Марина", topUsers.get(3).getName());
        assertEquals(9, topUsers.get(3).getScore());
        assertEquals("Игорь", topUsers.get(4).getName());
        assertEquals(7, topUsers.get(4).getScore());

    }


}
