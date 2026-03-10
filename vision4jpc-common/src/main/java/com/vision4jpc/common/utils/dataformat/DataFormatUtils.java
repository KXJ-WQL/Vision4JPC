package com.vision4jpc.common.utils.dataformat;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.targetdrone.utils
 * @className: DataFormatUtils
 * @author: WQL-KXJ
 * @description: TODO 设备发送和响应的信息格式转化
 * @date: 2024/10/16 12:21
 * @version: v1.0
 */
public class DataFormatUtils {

    // 将HEX字符串转换为字节数组
    public static byte[] hexStringToByteArray(String hex) {
        // 获取字符串的长度
        int len = hex.length();
        // 初始化一个字节数组，长度为字符串长度的一半（两个字符代表一个字节）
        byte[] data = new byte[len / 2];
        // 每两个字符转换为一个字节
        for (int i = 0; i < len; i += 2) {
            // 将两个字符解析为对应的16进制数，再将其合并为一个字节
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    // 将字节数组转换为HEX字符串
    public static String byteArrayToHexString(byte[] bytes) {
        // 使用StringBuilder提高字符串拼接的效率
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        // 遍历字节数组，将每个字节转换为对应的16进制表示
        for (byte b : bytes) {
            // 将字节转换为无符号整数并转为16进制字符串
            String hex = Integer.toHexString(0xFF & b);
            // 若16进制字符串长度为1，前面补0确保是两位
            if (hex.length() == 1) hexString.append('0');
            // 追加到StringBuilder
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 二进制字符串转HEX字符串
    public static String binStrTohexStr(String input) {
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        // 遍历每4位二进制字符串，转换为1位16进制字符
        for (int i = 0; i < len; i += 4) {
            // 截取4位二进制字符串
            String temp = input.substring(i, Math.min(i + 4, len));
            // 将4位二进制转换为整数
            int tempInt = Integer.parseInt(temp, 2);
            // 转换为16进制字符并追加到结果中
            sb.append(Integer.toHexString(tempInt).toUpperCase());
        }
        return sb.toString();
    }

}
