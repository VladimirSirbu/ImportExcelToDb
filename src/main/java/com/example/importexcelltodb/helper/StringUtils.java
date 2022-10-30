package com.example.importexcelltodb.helper;

public final class StringUtils {
    public static String replaceSpaceWithUnderscore(String s) {
        return s.trim().replaceAll("\\s+","_");
    }
}
