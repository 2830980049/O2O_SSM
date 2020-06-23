package com.edu.o2o.service.impl;

import com.edu.o2o.dao.AreaDao;
import com.edu.o2o.entity.Area;
import com.edu.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 14:15
 */
@Service("AreaServiceImpl")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    public List<Area> getAreal() {
        return areaDao.queryArea();
    }
}
