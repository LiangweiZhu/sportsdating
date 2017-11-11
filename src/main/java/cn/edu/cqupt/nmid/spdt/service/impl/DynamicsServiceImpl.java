package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.DynamicsServiceDao;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.DynamicsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Service
public class DynamicsServiceImpl implements DynamicsService {

    @Resource
    private DynamicsServiceDao dynamicsDao;

    @Override
    public ResponseJson estabDynamic(DynamicNews dynamicNews) {
        DynamicNews results = dynamicsDao.estabDynamicNews(dynamicNews);
        if (results != null) {
            ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(results);
        }
        return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
    }
}
