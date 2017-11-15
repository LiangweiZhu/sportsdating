package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.DynamicsServiceDao;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.DynamicsService;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.util.DaoResponseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lawrence on 2017/11/11.
 */
@Service
public class DynamicsServiceImpl implements DynamicsService {

    @Resource
    private DynamicsServiceDao dynamicsDao;

    @Resource
    private FileService fileService;

    @Override
    public ResponseJson estabDynamic(HttpServletRequest request, DynamicNews dynamicNews) throws IOException {
        DynamicNews results = dynamicsDao.estabDynamicNews(dynamicNews);
        results.setDynamicPic(fileService.upLoadPic(request,"DynamicNews",results.getDynamicId()));;
        return DaoResponseUtil.isNull(results);
    }

    @Override
    public ResponseJson getAllDynamicNews() {
        List<DynamicNews> dynamicNewsList = dynamicsDao.getAllDynamicNews();
        return DaoResponseUtil.isNull(dynamicNewsList);
    }
}
