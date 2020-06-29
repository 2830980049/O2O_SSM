import com.edu.o2o.dao.ShopDao;
import com.edu.o2o.entity.Area;
import com.edu.o2o.entity.PersonInfo;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 15:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ShopDaoTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        personInfo.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(14L);
        shop.setPersonInfo(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(41L);
        shop.setShopDesc("test123");
        shop.setShopAddr("test435");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testSelectI(){
        Shop shop = new Shop();
        shop = shopDao.queryByShopId(1L);
        System.out.println(shop);
    }

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setPersonInfo(owner);
        List<Shop> shoplist = shopDao.queryShopList(shopCondition,1,5);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println(shoplist.size());
        System.out.println(count);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(22L);
        shopCondition.setShopCategory(shopCategory);
        shoplist = shopDao.queryShopList(shopCondition,0,2);
        System.out.println(shoplist.size());
        count = shopDao.queryShopCount(shopCondition);
        System.out.println(count);
    }

}
