package com.dimiroma.vernam.utils.passwordGenerator;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@Service
public class PasswordGeneratorServiceImpl implements PasswordGeneratorService {
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    private static final String special = "!@#$%^&*+-_/";

    private static final String alphanum = upper + lower + digits + special;

    private Random random;

    private char[] symbols;

    private char[] buf;

    /*** Create an alphanumeric string from a secure generator. ***/
    public String generatePassword(final int length) {
        // 01: Setup Generator
        alphanumericStringGenerator(length, new SecureRandom());
        // 02: Generate a string
        String output = this.nextString();

        return output;
    }

    /*** initGenerator ***/
    private void initializeGenerator(final int length, final Random random, final String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /*** Alphanumeric string generator. ***/
    private void alphanumericStringGenerator(final int length, final Random random) {
        initializeGenerator(length, random, alphanum);
    }

    /*** Generate a random string. ***/
    private String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
