import com.edu.o2o.dao.ProductCategoryDao;
import com.edu.o2o.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/30 17:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void testQueryByShopId() throws Exception{
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(29L);
        System.out.println(productCategories);
    }
}
