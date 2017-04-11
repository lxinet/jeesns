package com.lxinet.jeesns.core.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by zchuanzhao on 2017/3/7.
 */
public class ImageUtil {
    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;
    private static int DEFAULT_WIDTH = 160;
    private static int DEFAULT_HEIGHT = 160;
    private File targetFile;

    public String dealImage(File imgFile){
        //先缩小图片
        thumbnailImage(imgFile);
        //再裁剪图片
        return cutImage(targetFile);
    }



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
                }
                // 类型和图片后缀全部小写，然后判断后缀是否合法
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
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                        }else {
                            w = width;
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                        }else {
                            h = height;
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
                targetFile = new File(thumbPath);
                ImageIO.write(bi, suffix, targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String thumbnailImage(File imgFile, int w, int h, boolean force) {
        return thumbnailImage(imgFile, w, h, DEFAULT_PREVFIX, force);
    }

    private String thumbnailImage(File imgFile) {
        return thumbnailImage(imgFile, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FORCE);
    }


    /**
     * 裁剪图片
     * @param imgFile
     * @param w
     * @param h
     * @return
     */
    private String cutImage(File imgFile, int w, int h) {
        FileInputStream is = null;
        ImageInputStream iis = null;
        String fileName = "";
        String suffix = "";
        String thumbPath = "";
        try {

            String types = Arrays.toString(ImageIO.getReaderFormatNames());
            // 获取图片后缀
            if (imgFile.getName().indexOf(".") > -1) {
                suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            }
            // 类型和图片后缀全部小写，然后判断后缀是否合法
            if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                return thumbPath;
            }
            // 读取图片文件
            is = new FileInputStream(imgFile);

            Image img = ImageIO.read(imgFile);
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            int x;
            int y;
            if(width > w){
                x = (width - w) / 2;
            }else {
                x = 0;
                w = width;
            }
            if(height > h){
                y = (height - h) / 2;
            }else {
                y = 0;
                h = height;
            }
            /**
             *
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
             *
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             *
             * (例如 "jpeg" 或 "tiff")等 。
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffix);

            ImageReader reader = it.next();

            // 获取图片流
            iis = ImageIO.createImageInputStream(is);

            /**
             *
             * <p>
             * iis:读取源。true:只向前搜索
             * </p>
             * .将它标记为 ‘只向前搜索’。
             *
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             *
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true);

            /**
             *
             * <p>
             * 描述如何对流进行解码的类
             * <p>
             * .用于指定如何在输入时从 Java Image I/O
             *
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             *
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
             *
             * ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();

            /**
             *
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             *
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
             */
            Rectangle rect = new Rectangle(x, y, w, h);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /**
             *
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             *
             * 它作为一个完整的 BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0,param);

            // 保存新图片
            fileName = imgFile.getName();
            String p = imgFile.getPath();
            thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
            ImageIO.write(bi, suffix, new File(thumbPath));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (iis != null) {
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    private String cutImage(File imgFile) {
        return cutImage(imgFile, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
