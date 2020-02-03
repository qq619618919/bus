package com.paic.gbd.client.impl.interceptor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paic.gbd.client.http.interceptor.HttpInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GbdBusResponseInterceptor extends HttpInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(GbdBusResponseInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        return execute(chain.request(), chain);
    }

    private Response execute(Request request, Chain chain) throws IOException {
        Response response = chain.proceed(request);
//        String requestId = response.header(DatahubConstant.X_DATAHUB_REQUEST_ID);
//        if (requestId == null) {
//            LOGGER.warn("requestId is null");
//        }
//        int status = response.code();
//        if (status >= 400) {
//            GbdBusResponseInterceptor.GbdBusResponseError error = new GbdBusResponseInterceptor.GbdBusResponseError();
//            String contentType = response.header(HttpHeaders.CONTENT_TYPE.toLowerCase());
//            ResponseBody body = response.body();
//            if (body != null) {
//                if ("application/json".equalsIgnoreCase(contentType)) {
//                    String bodyStr = body.string();
//                    error = JsonUtils.fromJson(bodyStr, GbdBusResponseInterceptor.GbdBusResponseError.class);
//                } else {
//                    error = new GbdBusResponseInterceptor.GbdBusResponseError();
//                    error.setMessage(body.string());
//                }
//            }
//            throw new DatahubClientException(status,
//                    requestId,
//                    error == null ? "" : error.getCode(),
//                    error == null ? "" : error.getMessage());
//        }
        return response;
    }

    private static class GbdBusResponseError {
        @JsonProperty("RequestId")
        private String requestId;

        @JsonProperty("ErrorCode")
        private String code;

        @JsonProperty("ErrorMessage")
        private String message;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
