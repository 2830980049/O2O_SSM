package util;

import com.edu.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 18:58
 */
public class ImageUtil {
    private static String basePath = "D:/IDEA Files/O2O_SSM/O2O_Web/src/main/webapp/images";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     *  CommonsMultpartFile转化为File
     * @param cfile
     * @return
     */
    public static File transferCommonsMultpartFileToFile(CommonsMultipartFile cfile){
        File newFile = new File(cfile.getOriginalFilename());
        try {
            cfile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 处理缩略图，并返回新生成图片的相对值路径
     * @param shopImgInputStream
     * @param targetAddr
     * @return
     * @throws IOException
     */
    // targetAddr文件存储路径
    public static String generateThumbnail(InputStream shopImgInputStream, String targetAddr,String fileName) throws IOException {
        // 获取文件名
        String realFileName = getRandomFileName();
        // 获取后缀名
        String extension = getFileExtension(fileName);
        // 创建保存文件夹
        makeDirPath(targetAddr);
        // 得到文件相对路径  /N/666.hpg
        String relativeAddr = targetAddr + realFileName + extension;
        // 写入日志
        logger.debug("current relativeAddr is :"+ relativeAddr);
        // 文件路径 D:/image/N/666.hpg
        File dest = new File(relativeAddr);
        // 写入日志
        logger.debug("current complete addr is:"+PathUtil.getImgBasePath() + relativeAddr);
        try {
            /**
             *  size(width,height) 若图片横比200小，高比300小，不变
             *  若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
             *  若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
             *
             *  加水印 watermark(位置，水印图，透明度)
             *  outputQuality 压缩
             */
            Thumbnails.of(shopImgInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")),
                            0.25f)
                    .outputQuality(0.8f).toFile(dest);
        }
        catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 处理详情图，并返回新生产图片相对路径
     * @param targetAddr
     * @param
     * @return
     * @throws IOException
     */
    // targetAddr文件存储路径
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) throws IOException {
        // 获取文件名
        String realFileName = getRandomFileName();
        // 获取后缀名
        String extension = getFileExtension(thumbnail.getImageName());
        // 创建保存文件夹
        makeDirPath(targetAddr);
        // 得到文件相对路径  /N/666.hpg
        String relativeAddr = targetAddr + realFileName + extension;
        // 写入日志
        logger.debug("current relativeAddr is :"+ relativeAddr);
        // 文件路径 D:/image/N/666.hpg
        File dest = new File(relativeAddr);
        // 写入日志
        logger.debug("current complete addr is:"+PathUtil.getImgBasePath() + relativeAddr);
        try {
            /**
             *  size(width,height) 若图片横比200小，高比300小，不变
             *  若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
             *  若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
             *
             *  加水印 watermark(位置，水印图，透明度)
             *  outputQuality 压缩
             */
            Thumbnails.of(thumbnail.getImage()).size(350,550)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")),
                            0.25f)
                    .outputQuality(0.9f).toFile(dest);
        }
        catch (IOException e){
            logger.error(e.toString());
            throw new RuntimeException("创建缩略图失败："+e.toString());
        }
        return relativeAddr;
    }

    /**
     *  生成随机文件名，当前年月日小时秒 + 五位随机数
     * @return
     */
    public static String getRandomFileName(){
        //获取随机5位数
        int randoms = r.nextInt(89999) + 10000;
        String nowTime = sdf.format(new Date());
        return nowTime + randoms;
    }

    /**
     * 获取输入文件流扩展名
     * @return
     */
    public static String getFileExtension(String cfile){
        //获取文件名
        String originalFileName = cfile;
        return  originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及到的目录，如 D:/SUSU/N/
     * 那么 home work xiangze 这三个文件夹都自动创建
     */
    public static void makeDirPath(String targetAddr){
        File dirPath = new File(targetAddr);
        if (!dirPath.exists())
            dirPath.mkdirs();
    }

    /**
     * path 是文件的路径则删除该文件
     * path 是目录路径则删除该目录下的所有文件
     * @param path
     */
    public static void deleteFileOrPath(String path){
        File fileOrPath = new File( path);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++)
                    files[i].delete();
            }
            fileOrPath.delete();
            System.out.println("fileOrPath "+fileOrPath);
        }
    }



    public static void main(String[] args) {
        System.out.println(basePath);
        try {
            Thumbnails.of(new File("C:/Users/asus/Pictures/1.jpg"))
                    .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File("D:/IDEA Files/O2O_SSM/O2O_Web/src/main/webapp/images"+"/watermark.jpg")),
                    0.25f).outputQuality(0.8f)
            .toFile("C:/Users/asus/Pictures/1_new.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
