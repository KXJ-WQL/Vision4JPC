package com.vision4jpc.common.utils;
/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils
 * @className: StringUtil
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/19 14:01
 * @version: v1.0
 */
public class StringUtils {

    // 从SocketAddress中提取IP(字符串处理:/192.168.1.*:60521)
    public static String SocketAddressIPStr(String socketAddressStr){
        return socketAddressStr.replaceAll("/(.*):.*", "$1");
    }

    // 从serialPort串口对象的模糊名称中提取具体名称(//./COM5)
    public static String SerialNameExtract(String serialName){
        return serialName.replaceAll(".*(COM\\d+).*", "$1");
    }

    // 给定ip地址和端口号转化为http://IP:Port/的格式
    public static String formatUrl(String ip, int port) {
        return String.format("http://%s:%d/", ip.trim(), port);
    }

    // 将字节数组转为HEX格式的字符串
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02X ", b));
        return sb.toString();
    }

    // 去除base64编码字符串的前缀
    public static String removeBase64Prefix(String base64Str){
        return base64Str.replaceFirst("^data:image/[^;]+;base64,", "");
    }

    // 判断IP是否为回路本地地址
    public static Boolean isLocalAddress(String ip){
        return "127.0.0.1".equals(ip);
    }

}