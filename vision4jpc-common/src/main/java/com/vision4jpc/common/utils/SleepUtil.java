package com.vision4jpc.common.utils;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.targetdrone.utils
 * @className: SleepUtil
 * @author: WQL-KXJ
 * @description: TODO 休眠工具类
 * @date: 2024/10/18 18:03
 * @version: v1.0
 */
public class SleepUtil {

    // 休眠指定的毫秒数，并处理中断异常
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 恢复线程的中断状态
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
