package com.edu.o2o.dao;

import com.edu.o2o.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 13:37
 */
@Repository("AreaDao")
public interface AreaDao {
    List<Area> queryArea();
}
