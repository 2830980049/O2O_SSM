import com.edu.o2o.dao.AreaDao;
import com.edu.o2o.entity.Area;
import com.edu.o2o.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 14:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml"})
public class Areservice {
    @Autowired
    private AreaService areaService;
    @Test
    public void testquery(){
        List<Area> areas = areaService.getAreal();
        System.out.println(areas);
    }
}
