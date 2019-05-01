package com.woxis.votingapp.util;

public class UserId {

    private final static ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    public static void set(Long userId) {
        userIdHolder.set(userId);
    }

    public static Long get() {
        return userIdHolder.get();
    }
}
