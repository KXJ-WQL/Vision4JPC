package com.vision4jpc.common.utils.crc;

import java.util.Arrays;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.targetdrone.utils
 * @className: CRC16Util
 * @author: WQL-KXJ
 * @description: TODO CRC16效验工具类
 * @date: 2024/10/18 16:11
 * @version: v1.0
 */
public class CRC16Util {

    // 生成CRC效验码
    public static String getCRC16(byte[] bytes) {
        // 初始化CRC寄存器，全为1
        int crc = 0xFFFF;
        // CRC多项式
        final int POLYNOMIAL = 0xA001;
        // 遍历输入字节数组
        for (byte b : bytes) {
            // 将字节与当前CRC进行异或运算
            crc ^= (b & 0xFF);
            // 循环处理每个字节的每一位
            for (int j = 0; j < 8; j++) {
                // 检查最低位是否为1
                if ((crc & 0x0001) != 0) {
                    // 右移一位并与多项式进行异或
                    crc >>= 1;
                    crc ^= POLYNOMIAL;
                } else {
                    // 仅右移一位
                    crc >>= 1;
                }
            }
        }
        // 将CRC值转换为16进制字符串
        String result = Integer.toHexString(crc).toUpperCase();
        // 保证结果长度为4位，不足的部分用'0'填充
        while (result.length() < 4) result = "0" + result;
        // 将结果按小端字节顺序（低位在前，高位在后）调整输出
        return result.substring(2, 4) + " " + result.substring(0, 2);
    }


    // 校验 CRC
    public static boolean verifyCRC(byte[] frame) {
        int len = frame.length;

        // 帧长度不能小于 3
        if (len < 3) return false;

        // 取出帧中的 CRC（低字节在前，高字节在后）
        int crcLow = frame[len - 2] & 0xFF;
        int crcHigh = frame[len - 1] & 0xFF;

        // 计算除 CRC 外的所有字节的 CRC
        byte[] data = Arrays.copyOf(frame, len - 2);
        String crc = CRC16Util.getCRC16(data);     // 例如： "58 79"

        // 解析字符串 CRC
        String[] parts = crc.split(" ");
        int calcLow = Integer.parseInt(parts[0], 16);
        int calcHigh = Integer.parseInt(parts[1], 16);

        return (crcLow == calcLow) && (crcHigh == calcHigh);
    }

}
