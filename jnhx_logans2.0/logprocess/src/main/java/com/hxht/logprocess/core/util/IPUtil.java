package com.hxht.logprocess.core.util;

public class IPUtil {

    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    public static long ipToLong(String strIp) {
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /**
     * 字符串的长度 0.0.0.0 7位 ~ 000.000.000.000 15位
     * 将字符串拆分成四段
     * 检查每段是否都是纯数字
     * 检查每段是否都在0-255之间
     * 以上条件都满足的话返回true
     */
    public static boolean isIPAddress(String str) {
        // 如果长度不符合条件 返回false
        if (str.length() < 7 || str.length() > 15) return false;
        String[] arr = str.split("\\.");
        //如果拆分结果不是4个字串 返回false
        if (arr.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                char temp = arr[i].charAt(j);
                //如果某个字符不是数字就返回false
                if (!(temp > '0' && temp < '9')) return false;
            }
        }
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(arr[i]);
            //如果某个数字不是0到255之间的数 就返回false
            if (temp < 0 || temp > 255) return false;
        }
        return true;
    }


    /**
     * 用正则表达式进行判断
     */
    public static boolean isIPAddressByRegex(String str) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        // 判断ip地址是否与正则表达式匹配
        if (str.matches(regex)) {
            String[] arr = str.split("\\.");
            for (int i = 0; i < 4; i++) {
                int temp = Integer.parseInt(arr[i]);
                //如果某个数字不是0到255之间的数 就返回false
                if (temp < 0 || temp > 255) return false;
            }
            return true;
        } else return false;
    }


}
