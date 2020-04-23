package com.company;

import java.util.Scanner;

public class Calc {

    private boolean isRome = false;
    private String input = "";
    private RomeConverter romeConverter = new RomeConverter();



    String calculator(String in) throws Exception {
        input = in.replaceAll(" ", "");
        checkCorrect();
        isRome = romeConverter.checkIsRome(input);
        int pos = findActionPosition(input);
        checkCorrectPosition(pos);

        char action = input.charAt(pos);
        int a = getNumber(input.substring(0, pos));
        int b = getNumber(input.substring(pos + 1));
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("incorrect input a<1 || a> 10 || b<1 || b>10 -> " + a + b);
        }
        int c = performAction(action, a, b);

        return convertToRome(c);
    }

    private String convertToRome(int c) {
        if (isRome) {
            if (c < 0) {
                int c1 = c * -1;
                String res = romeConverter.toRoman(c1);
                return "-" + res;
            } else {
                return romeConverter.toRoman(c);
            }
        } else {
            return c + "";
        }
    }

    private int performAction(char action, int a, int b) throws Exception {
        int res = -1;
        if (action == '+') {
            res = a + b;
        } else if (action == '-') {
            res = a - b;
        } else if (action == '*') {
            res = a * b;
        } else if (action == '/') {
            if (b == 0) {
                throw new Exception("Delenie na 0");
            }
            res = a / b;
        }
        return res;
    }

    private void checkCorrectPosition(int pos) throws Exception {
        if (pos == -1) {
            throw new Exception("not found action");
        }
        if (pos == 0 || pos == input.length() - 1) {
            throw new Exception("incorrect position action. pos == 0 || pos == in.length()-1 =>" + pos);
        }
    }

    private void checkCorrect() throws Exception {
        if (!(input.replaceAll("[0123456789\\*\\/\\+-IVX]", "").length() == 0)) {
            throw new Exception("not correct input " + input + input.replaceAll("[0123456789\\*\\/\\+-IVX]", "*"));
        }
        if (input.length() < 3) {
            throw new Exception("input.length() <3");
        }
    }

    int getNumber(String in) throws Exception {
        if ((in.charAt(0) == 'I' || in.charAt(0) == 'V' || in.charAt(0) == 'X') && isRome) {
            return romeConverter.toArabic(in);
        } else if (!isRome) {
            return Integer.parseInt(in);
        }
        throw new Exception("Incorrect number " + in + isRome);
    }

    int findActionPosition(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //log("check p:" + i + " val:" + c);
            if (c == '*' || c == '+' || c == '-' || c == '/') {
                //log("found p:" + i + " val:" + c);
                return i;
            }
        }
        return -1;
    }


}
