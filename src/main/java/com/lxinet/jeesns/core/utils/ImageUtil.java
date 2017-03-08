package com.lxinet.jeesns.core.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
public class ImageUtil {
    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;
    private static int DEFAULT_WIDTH = 150;
    private static int DEFAULT_HEIGHT = 100;


    /**
     * 根据图片路径生成缩略图
     * @param imgFile 原图片
     * @param w       缩略图宽
     * @param h       缩略图高
     * @param prevfix 生成缩略图的前缀
     * @param force   是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    private String thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force) {
        String thumbPath = "";
        String fileName = "";
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    return thumbPath;
                }
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
//                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                fileName = prevfix + imgFile.getName();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
                ImageIO.write(bi, suffix, new File(thumbPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String thumbnailImage(File imgFile, int w, int h, boolean force) {
        return thumbnailImage(imgFile, w, h, DEFAULT_PREVFIX, force);
    }

    public String thumbnailImage(File imgFile) {
        return thumbnailImage(imgFile, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FORCE);
    }
}
