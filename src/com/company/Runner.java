package com.company;

import java.util.Scanner;

import static com.company.Log.log;

public class Runner {
   static Calc calcul = new Calc();


    public static void main(String[] args) {

        log("start");
        Scanner scan = new Scanner(System.in);
        Calc cal = new Calc();
        while (true) {
            String inputConsole = scan.next();
            log("Input " + inputConsole);
            try {
                String res = cal.calculator(inputConsole);
                log("Res " + res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
