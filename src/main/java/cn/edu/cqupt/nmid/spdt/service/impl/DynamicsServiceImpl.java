package cn.edu.cqupt.nmid.spdt.service.impl;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.dao.DynamicsServiceDao;
import cn.edu.cqupt.nmid.spdt.dao.UserServiceDao;
import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.DynamicNewsLike;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;
import cn.edu.cqupt.nmid.spdt.service.ActivityService;
import cn.edu.cqupt.nmid.spdt.service.DynamicsService;
import cn.edu.cqupt.nmid.spdt.service.FileService;
import cn.edu.cqupt.nmid.spdt.service.UserService;
import cn.edu.cqupt.nmid.spdt.util.DaoResponseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
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
    @Resource
    private UserServiceDao userServiceDao;

    /**
     * 创建圈子里的消息
     * @param request
     * @param dynamicNews
     * @return
     * @throws IOException
     */
    @Override
    public ResponseJson estabDynamic(HttpServletRequest request, DynamicNews dynamicNews) throws IOException {
        DynamicNews results = dynamicsDao.estabDynamicNews(dynamicNews);
        results.setDynamicPic(fileService.upLoadPic(request,"DynamicNews",results.getDynamicId()));;
        return DaoResponseUtil.isNull(results);
    }

    /**
     * 点赞
     * @param dynamicNewsLike
     * @return
     */
    @Override
    public ResponseJson like(DynamicNewsLike dynamicNewsLike) {
        //寻找有无旧的点赞记录
        dynamicNewsLike.setUserName(userServiceDao.findUserByID(dynamicNewsLike.getUserId()).getUserName());
        DynamicNewsLike oldHistory = dynamicsDao.findHistory(dynamicNewsLike);
        //没有则新建记录
        if (oldHistory==null) {
            oldHistory = dynamicsDao.newLike(dynamicNewsLike);
            DynamicNews dynamicNews = dynamicsDao.getDynamicNewsById(oldHistory.getDynamicId());
            dynamicsDao.updateLikeNum(dynamicNews.getDynamicId(),dynamicNews.getLikeNumber()+1);
        } else {
            //有责更改点赞状态
            oldHistory = dynamicsDao.changeLikeStatus(oldHistory);
            if ("like".equals(oldHistory.getUserLike())) {
                DynamicNews dynamicNews = dynamicsDao.getDynamicNewsById(oldHistory.getDynamicId());
                dynamicsDao.updateLikeNum(dynamicNews.getDynamicId(),dynamicNews.getLikeNumber()+1);
            }
            else {
                DynamicNews dynamicNews = dynamicsDao.getDynamicNewsById(oldHistory.getDynamicId());
                dynamicsDao.updateLikeNum(dynamicNews.getDynamicId(),dynamicNews.getLikeNumber()-1);
            }
        }
        return DaoResponseUtil.isNull(oldHistory);
    }

    /**
     * 获取点赞列表
     * @return
     */
    @Override
    public ResponseJson getAllDynamicNews() {
        List<DynamicNews> dynamicNewsList = dynamicsDao.getAllDynamicNews();
        Iterator<DynamicNews> iterator = dynamicNewsList.iterator();
        while (iterator.hasNext()) {
            DynamicNews dynamicNews = iterator.next();
            dynamicNews.setWhoLikes(dynamicsDao.getLikedPeole(dynamicNews.getDynamicId()));
        }
        return DaoResponseUtil.isNull(dynamicNewsList);
    }
}
