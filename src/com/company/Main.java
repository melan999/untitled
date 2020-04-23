package com.company;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private boolean isRome = false;
    private String input = "";
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    public static void main(String[] args) {
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        log("start");
        Scanner scan = new Scanner(System.in);
        Main cal = new Main();
        while (true) {
            String inputConsole = scan.next();
            System.out.println("Input " + inputConsole);
            try {
                String res = cal.calculator(inputConsole);
                System.out.println("Res " + res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    String calculator(String in) throws Exception {
        input = in.replaceAll(" ", "");
        checkCorrect();
        checkIsRome();
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
                String res = toRoman(c1);
                return "-" + res;
            } else {
                return toRoman(c);
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


    private String toRoman(int number) {

        if (number == 0) {
            return "" + 0;
        }
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }


    void checkIsRome() throws Exception {
        if (input.charAt(0) == 'I' || input.charAt(0) == 'V' || input.charAt(0) == 'X') {
            if (!(input.replaceAll("[\\*\\/\\+-IVX]", "").length() == 0)) {
                throw new Exception("regex rome error" + input + " " + input.replaceAll("[\\*\\/\\+-IVX]", "*"));
            }
            isRome = true;
        } else {
            isRome = false;
        }
    }

    int getNumber(String in) throws Exception {
        if ((in.charAt(0) == 'I' || in.charAt(0) == 'V' || in.charAt(0) == 'X') && isRome) {

            int result = 0;
            int[] decimal = {10, 9, 5, 4, 1};
            String[] roman = {"X", "IX", "V", "IV", "I"};

            for (int i = 0; i < decimal.length; i++) {
                while (in.indexOf(roman[i]) == 0) {
                    result += decimal[i];
                    in = in.substring(roman[i].length());
                }
            }
            return result;

        } else if (!isRome) {
            return Integer.parseInt(in);
        }
        throw new Exception("Incorrect number " + in + isRome);
    }

    static void log(String in) {
        System.out.println(in);

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
