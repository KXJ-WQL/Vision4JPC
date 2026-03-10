package com.vision4jpc.common.utils.file;

import com.vision4jpc.common.exceptions.system.SysServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils
 * @className: FileImageUtile
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/23 14:30
 * @version: v1.0
 */
public class FileImageUtil {

    private static final Logger log = LoggerFactory.getLogger(FileImageUtil.class);

    // 保存图像为文件
    public static void saveBufferedImage(BufferedImage image, String filePath) {
        // 创建文件对象
        File file = new File(filePath);
        // 尝试创建父目录
        createParentDirectory(file);
        try {
            // 将图像写入文件
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            // 捕获并打印保存图像时的异常信息
            log.error("保存图像时出错:image为null " + e.getMessage());
        }
    }

    // 读取图像文件
    public static BufferedImage readBufferedImage(String filePath) {
        // 创建文件对象
        File file = new File(filePath);
        // 检查文件是否存在
        if (!file.exists()) {throw new SysServiceException("shootlaser3");}
        // 尝试读取图像
        try {
            BufferedImage image = ImageIO.read(file);
            if (image == null) {throw new SysServiceException("shootlaser4");}
            return image;
        } catch (IOException e) {
            throw new SysServiceException("shootlaser4");
        }
    }

    // 创建父目录
    private static boolean createParentDirectory(File file) {
        // 获取父目录
        File parentDir = file.getParentFile();
        // 如果父目录不存在，则创建
        return parentDir == null || parentDir.exists() || parentDir.mkdirs();
    }
}
