package cn.edu.cqupt.nmid.spdt.service;

import cn.edu.cqupt.nmid.spdt.model.DynamicNews;
import cn.edu.cqupt.nmid.spdt.model.DynamicNewsLike;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Lawrence on 2017/11/11.
 */
public interface DynamicsService {

    public ResponseJson estabDynamic(HttpServletRequest request, DynamicNews dynamicNews) throws IOException;

    public ResponseJson like(DynamicNewsLike dynamicNewsLike);

    public ResponseJson getAllDynamicNews();


//    public ResponseJson updateDynamic();
}
