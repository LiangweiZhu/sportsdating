package cn.edu.cqupt.nmid.spdt.model.json;

import cn.edu.cqupt.nmid.spdt.constant.StatusCodeConstant;

/**
 * Created by Lawrence on 2017/11/4.
 */
public class ResponseJson {
    private Integer code;
    private String message;
    private Object body=null;

    public ResponseJson() {
    }

    public ResponseJson(StatusCodeConstant statusCodeConstant){
        this.code = statusCodeConstant.getCode();
        this.message = statusCodeConstant.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResponseJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
