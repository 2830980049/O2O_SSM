package util;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 19:15
 */
public class PathUtil {

    // file.seperator 文件分隔符
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        // os.name 获取操作系统名称
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/O2O/image/";
        }
        else
            basePath = "/home/O2O/image/";
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    // 店铺存储路径
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
