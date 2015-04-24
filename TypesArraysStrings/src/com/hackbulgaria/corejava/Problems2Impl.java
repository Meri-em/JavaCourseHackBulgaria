package com.hackbulgaria.corejava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problems2Impl implements Problems2 {

    @Override
    public boolean isOdd(int number) {
        return number % 2 != 0;
    }

    @Override
    public boolean isPrime(int number) {
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0)
                return false;
        }
        return true;

    }

    @Override
    public int min(int... array) {
        int minimalValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minimalValue)
                minimalValue = array[i];
        }
        return minimalValue;
    }

    // There will be no duplicates in the array.
    @Override
    public int kthMin(int k, int[] array) {
        int border = min(array);
        for (int i = 1; i < k; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < array.length; j++) {
                if(array[j] < min && array[j] > border){
                    min = array[j];
                }
            }
            border = min;
        }
        return border;

    }

    @Override
    public float getAverage(int[] array) {
        float sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return (float) sum / array.length;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = n; i >= 2; i--) {
            result *= i;
        }
        return result;
    }

    @Override
    public long doubleFac(int n) {
        return factorial(factorial(n));
    }

    @Override
    public long kthFac(int k, int n) {
        int result = 1;
        for (int i = n; i >= k; i -= k) {
            result *= i;
        }
        return result;
    }

    private static int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        }
        return gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        int temp = gcd(a, b);
        return temp != 0 ? (a / temp * b) : 0;
    }

    @Override
    public long getSmallestMultiple(int upperBound) {
        int result = upperBound;
        for (int i = upperBound - 1; i >= 2; i--) {
            result = lcm(result, i);
        }
        return result;
    }

    private static boolean isPalindrome(long x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            long l = x / div;
            long r = x % 10;
            if (l != r)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    @Override
    public long getLargestPalindrome(long N) {
        long i;
        for (i = N;; i--) {
            if (isPalindrome(i))
                return i;
        }
    }

    @Override
    public int[] histogram(short[][] image) {
        int[] result = new int[256];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                result[image[i][j]]++;
            }
        }
        return result;
    }

    @Override
    public long pow(int a, int b) {
        if (b < 0)
            return 1 / pow(a, -b);
        if (b == 0)
            return 1;
        long halfPow = pow(a, b / 2);
        if (b % 2 == 0)
            return halfPow * halfPow;
        return halfPow * halfPow * a;
    }

    @Override
    public int getOddOccurrence(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        int counter;
        for (int i = 0; i < copy.length; i++) {
            counter = 0;
            for (int j = 0; j < array.length; j++) {
                if (copy[i] == array[j])
                    counter++;
            }
            if (counter % 2 != 0)
                return copy[i];
        }
        return 0;
    }

    public int getOddOccurrenceHashMap(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], map.get(array[i]) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) % 2 != 0) {
                return key;
            }
        }
        return 0;
    }

    @Override
    public long maximalScalarSum(int[] a, int[] b) {
        long scalarProduct = 0;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++)
            scalarProduct += a[i] * b[i];
        return scalarProduct;
    }

    @Override
    public int maxSpan(int[] array) {
        int maxSpan = 1;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j] && j - i + 1 > maxSpan) {
                    maxSpan = j - i + 1;
                }
            }
        }
        return maxSpan;
    }

    @Override
    public boolean canBalance(int[] array) {
        int leftSum = array[0];
        int rightSum = 0;
        for (int i = 1; i < array.length; i++) {
            rightSum += array[i];
        }
        int i = 1;
        while (rightSum != leftSum && i < array.length - 1) {
            leftSum += array[i];
            rightSum -= array[i];
            i++;
        }
        if (leftSum == rightSum)
            return true;
        return false;
    }

    @Override
    public int[][] rescale(int[][] original, int newWidth, int newHeight) {
        int[][] newArray = new int[newWidth][newHeight];
        int xRatio = original.length / newWidth;
        int yRatio = original[0].length / newHeight;
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                newArray[i][j] = original[i * xRatio][j * yRatio];
            }
        }
        return newArray;
    }

    @Override
    public String reverseMe(String argument) {
        return new StringBuilder(argument).reverse().toString();
    }

    // "tahW si siht" ->"What is this";
    @Override
    public String reverseEveryWord(String arg) {
        StringBuilder sb = new StringBuilder();
        String[] words = arg.split(" ");
        for (String word : words) {
            sb.append(reverseMe(word) + " ");
        }
        return sb.toString().trim();
    }

    @Override
    public boolean isPalindrome(String argument) {
        return reverseMe(argument).equals(argument);
    }

    @Override
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            long l = x / div;
            long r = x % 10;
            if (l != r)
                return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
    
    // copyEveryChar("What the hell", 2) -> "Wwhhaatt  tthhee  hheellll"
    @Override
    public String copyEveryChar(String input, int k) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            result.append(input.charAt(i));
            for (int j = 1; j < k; j++) {
                result.append((input.charAt(i) + "").toLowerCase());
            }
        }
        return result.toString();
    }

    @Override
    public int getPalindromeLength(String input) {
        char[] inputChars = input.toCharArray();
        int i = input.indexOf('*');
        int counter = 0;
        int j = 1;
        while (i - j >= 0 && i + j < input.length() && inputChars[i - j] == inputChars[i + j]) {
            j++;
            counter++;
        }
        return counter;
    }

    @Override
    public int countOcurrences(String needle, String haystack) {
        return needle == "" ? 0 : haystack.split(needle).length - 1;
    }

    @Override
    public String decodeURL(String input) {
        return input.replace("%20", " ").replace("%3A", ":").replace("%3D", "?").replace("%2F", "/");
    }
    
    // "samir1WhatAreY-ou2Doing3You-4" -> 2
    @Override
    public int sumOfNumbers(String input) {
        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i)) || input.charAt(i) == '-') {
                StringBuilder sb = new StringBuilder();
                sb.append(input.charAt(i));
                i++;
                while (i < input.length()) {
                    if (Character.isDigit(input.charAt(i))) {
                        sb.append(input.charAt(i));
                        i++;
                    } else
                        break;
                }
                if (!sb.toString().equals("-")) {
                    String number = sb.toString();
                    sum += Integer.parseInt(number);
                }
            }
        }
        return sum;
    }

    @Override
    public boolean areAnagrams(String A, String B) {
        char[] word1 = A.toCharArray();
        char[] word2 = B.toCharArray();
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);

    }

    @Override
    public boolean hasAnagramOf(String sub, String string) {
        for (int i = 0; i <= string.length() - sub.length(); i++) {
            if (areAnagrams(string.substring(i, sub.length() + i), sub))
                return true;
        }
        return false;
    }

}
