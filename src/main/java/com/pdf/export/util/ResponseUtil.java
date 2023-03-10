package com.pdf.export.util;

import com.pdf.export.data.model.vo.ResponseVo;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class ResponseUtil {

    private ResponseUtil() {
        super();
    }

    public static <T> ResponseVo setResponse(int code, String message, T data) {
        return new ResponseVo(code, setMessage(code, message), data);
    }

    public static String setMessage(int code, String msg) {
        return HttpStatus.valueOf(code).getReasonPhrase() + " : " + msg;
    }

    public static JSONObject setJsonResponse(int code, String message) {
        try {
            JSONObject res = new JSONObject();
            res.put("code", code);
            res.put("message", setMessage(code, message));
            return res;
        } catch (Exception e) {
            return ResponseUtil.setJsonResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
