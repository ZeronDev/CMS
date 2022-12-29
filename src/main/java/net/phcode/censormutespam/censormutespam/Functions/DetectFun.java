package net.phcode.censormutespam.censormutespam.Functions;

import static net.phcode.censormutespam.censormutespam.Events.Chatev.containsIgnoreCase;

public class DetectFun {
    public static boolean detect(char[] det, String warn, int num) {
        if (String.valueOf(warn.charAt(num)).equalsIgnoreCase(String.valueOf(det[0]))) {
            return true;
        }
        return false;
    }
}
