package util;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 14:45
 */
public class PageCalculator {
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
