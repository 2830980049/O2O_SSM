package com.edu.o2o.controller;

import com.edu.o2o.entity.Area;
import com.edu.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wuqili
 * @version 1.0
 * @date 2020/6/23 14:24
 */
@Controller("AreaController")
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);


    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody //转化为JSON
    private Map<String, Object> listArea(){
        logger.info("======start======");
        long startTime = System.currentTimeMillis();
        Map<String, Object> modeMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try {
            list = areaService.getAreal();
            modeMap.put("rows", list);
            modeMap.put("total", list.size());
        }
        catch (Exception e){
            e.printStackTrace();
            modeMap.put("success", false);
            modeMap.put("message",e.toString());
        }
        logger.error("test error!");
        long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]", endTime - startTime);
        logger.info("======end======");
        return modeMap;
    }
}
