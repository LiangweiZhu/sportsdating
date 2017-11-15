package cn.edu.cqupt.nmid.spdt.util;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;
import cn.edu.cqupt.nmid.spdt.model.json.ResponseJson;

/**
 * Created by Lawrence on 2017/11/15.
 */
public class DaoResponseUtil {

    public static ResponseJson isNull(Object o) {
        if (o != null) {
            ResponseJson responseJson = new ResponseJson(StatusCodeConstant.OK);
            responseJson.setBody(o);
            return responseJson;
        }
        return new ResponseJson(StatusCodeConstant.INTERNAL_SERVER_ERROR);
    }
}
