import com.edu.o2o.dto.ShopExecution;
import com.edu.o2o.entity.Area;
import com.edu.o2o.entity.PersonInfo;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.entity.ShopCategory;
import com.edu.o2o.enums.ShopStateEnum;
import com.edu.o2o.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/24 0:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml"})
public class ShopServiceTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试店铺_3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:/Users/asus/Pictures/1.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,is,shopImg.getName());
        System.out.println(se.getStateInfo());
    }


    @Test
    public void updateTest() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(59L);
        shop.setShopName("VN666");
        File shopImg = new File("C:/Users/asus/Pictures/1.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.updateShop(shop,is,"1.jpg");
        System.out.println(shopExecution);
    }
}
