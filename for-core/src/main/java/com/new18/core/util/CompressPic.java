package com.new18.core.util;

/**
 *  缩略图实现，将图片(jpg、bmp、png、gif等等)真实的变成想要的大小
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/*******************************************************************************
 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
 */
public class CompressPic {

    public String compressPic(String iFileUrl, String oFileUrl, int width, int height, boolean proportion) throws IOException {
        File file = new File(iFileUrl);
        if (!file.exists()) {
            return "Not Find";
        }
        Image img = ImageIO.read(file);
        return this.compressPic(img, oFileUrl, width, height, proportion);
    }

    public String compressPic(Image img, String oFileUrl, int width, int height, boolean proportion) throws IOException {
        // 判断图片格式是否正确
        if (img.getWidth(null) == -1) {
            System.out.println(" can't read,retry!" + "<BR>");
            return "no";
        } else {
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion == true) {
                newWidth = img.getWidth(null);
                newHeight = img.getHeight(null);
                if(newWidth > width){
                    double rateW = (double) width / (double) newWidth;
                    double rateH = (double) height / (double) newHeight;
                    double rate = Math.max(rateW, rateH);
                    newWidth = (int) (newWidth*rate);
                    newHeight = (int) (newHeight*rate);
                }
            } else {
                newWidth = width; // 输出的图片宽度
                newHeight = height; // 输出的图片高度
            }
            BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            /*
             * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
             * 优先级比速度高 生成的图片质量比较好 但速度慢
             */
            tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

            //保存新图片
            FileOutputStream out = new FileOutputStream(oFileUrl);
            ImageIO.write(tag, "JPEG", out);
            out.flush();
            out.close();
        }
        return "ok";
    }

    // main测试
    // compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
    public static void main(String[] arg) {
        CompressPic mypic = new CompressPic();
        try {
            mypic.compressPic("e:\\1.jpg", "e:\\test\\1.jpg", 752, 0, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
