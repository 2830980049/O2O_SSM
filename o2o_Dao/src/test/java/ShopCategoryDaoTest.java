import com.edu.o2o.dao.ShopCategoryDao;
import com.edu.o2o.entity.Shop;
import com.edu.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/28 22:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ShopCategoryDaoTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testq(){
        ShopCategory s = new ShopCategory();
        ShopCategory p = new ShopCategory();
        p.setShopCategoryId(10L);
        s.setParent(p);
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(s);
        System.out.println(shopCategories);

    }
}
