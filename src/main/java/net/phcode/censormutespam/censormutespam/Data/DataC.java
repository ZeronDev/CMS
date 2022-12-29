package net.phcode.censormutespam.censormutespam.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataC {
    //데이터
    static public String prefix = "[ §c§lCMS§f ] ";
    //final static public String prefixM = new String("[§c음소거§f] ");
    //final static public String prefixS = new String("[§c도배§f] ");
    static public HashMap<String, Double> ChattedPlayers = new HashMap<>();
    static public HashMap<String, Object> PwarnV = new HashMap<>();
    static public HashMap<String, List> SpamPlayer = new HashMap<>();
    static public List<String> CensorshipM = new ArrayList<>();
    static public List<String> MutedPlayer = new ArrayList<>();
    static public int MaxWarn = 5;
    static public int MaxMlength = 50;
    static public double Mdelay = 2;
}
