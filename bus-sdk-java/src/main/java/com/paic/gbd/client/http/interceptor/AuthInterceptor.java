package com.paic.gbd.client.http.interceptor;

import com.paic.gbd.client.auth.Account;
import okhttp3.Interceptor;
import okhttp3.Response;

import java.io.IOException;

public class AuthInterceptor extends HttpInterceptor {
    protected Account account;

    public AuthInterceptor(Account account) {
        this.account = account;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return super.intercept(chain);
    }

    public Account getAccount() {
        return account;
    }
}
