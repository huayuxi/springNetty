package com.air.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class StringUtil {

    /**
     * 查找2个字符串之间的字符串 SubString方法
     * @param startString 要查找的开始字符串
     * @param endString 要查找的结束字串
     * @param str 要查找的字串
     * @return 返回2个字串之间的字符串 没查找到返回 str
     */
    public static String findTo2CharSubString(String startString, String endString, String str) {
        Boolean a = str.contains(startString);
        Boolean b = str.contains(endString);
        if (a && b) {
            int s = str.indexOf(startString) + startString.length();
            int e = str.indexOf(endString);
            String resStr = str.substring(s, e);
            return resStr;
        } else {
            return str;
        }

    }

    /**
     * 查找2个字符串之间的字符串 正则表达式方法
     * @param startString 要查找的开始字符串
     * @param endString 要查找的结束字串
     * @param str 要查找的字串
     * @return 返回2个字串之间的字符串 没查找到返回 str
     */
    public static String findTo2Char(String startString, String endString, String str) {
        Pattern pattern = Pattern.compile("(" + startString + ")(.*)(" + endString + ")");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(2);
        }
        return str;
    }

    /**
     * 查找2个字符串之间的字符串
     * @param startString 要查找的开始字符串
     * @param endString 要查找的结束字串
     * @param oldstr 要查找替换的字串
     * @param newStr 要替换的新内容
     * @return 替换为新的字串 startString+newStr+endString 没查找到返回 oldstr
     */

    public static String replaceTo2Char(String startString, String endString, String oldstr, String newStr) {
        Pattern pattern = Pattern.compile("(" + startString + ")(.*)(" + endString + ")");
        Matcher matcher = pattern.matcher(oldstr);
        while (matcher.find()) {
            String temp = matcher.group(2);
            oldstr = oldstr.replace(temp, newStr);
            return oldstr;
        }
        return oldstr;
    }

    /**
     * 从后像前截取字符字符串 从后往前截 第一个出现非 给定的字符为止
     * @param c c
     * @param str str
     * @return str
     */
    public String endSubString(String c, String str) {
        String regexStr = c + "*$";
        return str.replaceAll(regexStr, str);
    }

    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static String getHexString(byte[] b) {
        StringBuffer rs = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            rs.append(hex.toUpperCase());
        }
        return rs.toString();
    }

    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 获得整数的16进制表示的方法
     * @param num
     * @return
     */
    public static String getHexString(int num) {
        String str = "";
        if (num < 0) {
            num = Math.abs(num);
            str = "-0x" + Integer.toHexString(num);
        } else {
            str = "0x" + Integer.toHexString(num);
        }
        return str;
    }

    /**
     * 获得整数的16进制表示的方法不加0x，小于一位前面补0
     * @param num
     * @return
     */
    public static String getHexString2(int num) {
        String str = "";
        if (num == 0) {
            return "00";
        }
        if (num < 16) {
            num = Math.abs(num);
            str = "0" + Integer.toHexString(num);
        } else {
            num = Math.abs(num);
            str = "" + Integer.toHexString(num);
        }
        return str;
    }

}
