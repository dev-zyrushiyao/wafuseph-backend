package com.vtuber.wafuseph_backend.util;

public final class EmailNormalizer {

    //prevents calling new EmailNormalizer()
    private EmailNormalizer() {}

    //Access it using EmailNormalizer.normalize(email);
    public static String normalize(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }
}