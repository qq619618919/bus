package com.paic.gbd.client.auth;

import com.paic.gbd.common.GbdBusConstant;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Request;
import org.apache.http.HttpHeaders;

import java.util.Objects;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/30
 * @Description: com.paic.gbd.client.auth
 * @version: 1.0
 */
@Getter
@Setter
public class GbdBusAccount implements Account {
    private String username;
    private String pwd;
    private String securityToken;

    public GbdBusAccount(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public GbdBusAccount(String username, String pwd, String securityToken) {
        this.username = username;
        this.pwd = pwd;
        this.securityToken = securityToken;
    }

    @Override
    public void addAuthHeaders(Request.Builder reqBuilder) {
        if (securityToken != null) {
            reqBuilder.addHeader(GbdBusConstant.X_GBDBUS_SECURITY_TOKEN, securityToken);
        }

        // Authorization must be last
//        if (isNeedAuth()) {
//            Request copyRequest = reqBuilder.build();
//            reqBuilder.addHeader(HttpHeaders.AUTHORIZATION, signer.genAuthSignature(copyRequest));
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GbdBusAccount that = (GbdBusAccount) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(pwd, that.username) &&
                Objects.equals(securityToken, that.securityToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, pwd, securityToken);
    }
}
