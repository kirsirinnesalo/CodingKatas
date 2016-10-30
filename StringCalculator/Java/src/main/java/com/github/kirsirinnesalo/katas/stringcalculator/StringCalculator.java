package com.github.kirsirinnesalo.katas.stringcalculator;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_SEPARATORS = ",\n";
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("\\[(.*)\\]*");
    public static final String LINE_BREAK = "\n";
    public static final String SEPARATOR_PREFIX = "//";
    public static final String SEPARATOR_BRACKET = "[";

    public int add(String values) {
        if (StringUtils.isEmpty(values)) {
            return 0;
        }
        return calculateSumOf(values);
    }

    private int calculateSumOf(String values) {
        int sum = 0;
        for (String number : getNumbersFrom(values)) {
            sum += getValue(number);
        }
        return sum;
    }

    private int getValue(String number) {
        int value = Integer.parseInt(number);
        if (value < 0) {
            throw new UnsupportedOperationException("Negative values are not supported ("+value+").");
        }
        if (value <= 1000) {
            return value;
        }
        return 0;
    }

    private String[] getNumbersFrom(String values) {
        String separators = DEFAULT_SEPARATORS;
        if (separatorsAreDefinedBefore(values)) {
            separators = parseSeparatorsFrom(getSeparatorLineFrom(values));
            values = StringUtils.substringAfter(values, LINE_BREAK);
        }
        return StringUtils.split(values, separators);
    }

    private String getSeparatorLineFrom(String values) {
        return dropSeparatorPrefixFrom(StringUtils.substringBefore(values, LINE_BREAK));
    }

    private String dropSeparatorPrefixFrom(String line) {
        return line.substring(SEPARATOR_PREFIX.length());
    }

    private boolean separatorsAreDefinedBefore(String values) {
        return StringUtils.startsWith(values, SEPARATOR_PREFIX);
    }

    private String parseSeparatorsFrom(String separatorLine) {
        if (containsMultipleSeparators(separatorLine)) {
            return parseMultipleSeparatorsFrom(separatorLine);
        } else {
            return separatorLine;
        }
    }

    private boolean containsMultipleSeparators(String separatorLine) {
        return StringUtils.startsWith(separatorLine, SEPARATOR_BRACKET);
    }

    private String parseMultipleSeparatorsFrom(String values) {
        List<String> separators = new ArrayList<>();
        Matcher matcher = getSeparatorMatcherFor(values);
        while (matcher.find()) {
            separators.add(matcher.group());
        }
        return StringUtils.join(separators, ",");
    }

    private Matcher getSeparatorMatcherFor(String separators) {
        return SEPARATOR_PATTERN.matcher(separators);
    }
}
