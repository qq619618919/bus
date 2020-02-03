package com.paic.gbd.client.impl;

import com.paic.gbd.client.GbdBusClient;
import com.paic.gbd.client.auth.Account;
import com.paic.gbd.client.http.HttpClient;
import com.paic.gbd.client.http.HttpConfig;
import com.paic.gbd.client.http.InterceptorWrapper;
import com.paic.gbd.client.impl.interceptor.GbdBusAuthInterceptor;
import com.paic.gbd.client.impl.interceptor.GbdBusResponseInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/30
 * @Description: com.paic.gbd.client.impl
 * @version: 1.0
 */
@Getter @Setter
public abstract class AbstractGbdBusClient implements GbdBusClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGbdBusClient.class);

    protected static final int MAX_FETCH_SIZE = 1000;
    protected static final int MIN_FETCH_SIZE = 1;
    protected static final String MAX_SHARD_ID = String.valueOf(0xffffffffL); // "4294967295"
    protected static final int MAX_WAITING_TIME_IN_MS = 30000;

    private String endpoint;
    private HttpConfig httpConfig;
    private Account account;
    private InterceptorWrapper interceptorWrapper;

    public AbstractGbdBusClient(String endpoint, Account account, HttpConfig httpConfig, String userAgent) {
        this.endpoint = endpoint;
        this.account = account;
        this.httpConfig = httpConfig;
        this.interceptorWrapper = new InterceptorWrapper()
                .setAuth(new GbdBusAuthInterceptor(account, userAgent))
                .setResponse(new GbdBusResponseInterceptor());
    }

    protected GbdBusService getService() {
        return getService(GbdBusService.class);
    }

    protected GbdBusService getService(Class<? extends GbdBusService> cls) {
        return HttpClient.createClient(endpoint, httpConfig, interceptorWrapper).create(cls);
    }

    final protected <T> T callWrapper(Call<T> call) {
        return retryExecute(call);
    }

    final private <T> T retryExecute(Call<T> call) {
        int count = 1;
        while (true) {
            try {
                Response<T> response = call.execute();
//                if (response.body() instanceof BaseResult) {
//                    String requestId = response.raw().header(DatahubConstant.X_DATAHUB_REQUEST_ID);
//                    ((BaseResult) response.body()).setRequestId(requestId);
//                }
                return response.body();
            } catch (IOException ex) {
                if (count >= httpConfig.getMaxRetryCount()) {
                    LOGGER.error("请求失败");
                }
                ++count;
                call = call.clone();
            }
        }
    }
}
