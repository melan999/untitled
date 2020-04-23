package com.company;

import java.util.TreeMap;

public class RomeConverter {
    private  TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    int[] decimal = {10, 9, 5, 4, 1};
    String[] roman = {"X", "IX", "V", "IV", "I"};

    public RomeConverter() {
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public String toRoman(int number) {
        if(number==0) {
            return 0+"";
        }
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

    public boolean checkIsRome(String input) throws Exception {
        if (input.charAt(0) == 'I' || input.charAt(0) == 'V' || input.charAt(0) == 'X') {
            if (!(input.replaceAll("[\\*\\/\\+-IVX]", "").length() == 0)) {
                throw new Exception("regex rome error" + input + " " + input.replaceAll("[\\*\\/\\+-IVX]", "*"));
            }
            return true;
        } else {
            return false;
        }
    }

    public int toArabic(String in) {
        int result = 0;
        for (int i = 0; i < decimal.length; i++) {
            while (in.indexOf(roman[i]) == 0) {
                result += decimal[i];
                in = in.substring(roman[i].length());
            }
        }
        return result;
    }
}
