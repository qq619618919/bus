/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.aliyun.datahub.auth;

import com.aliyun.datahub.common.transport.DefaultRequest;
import com.aliyun.datahub.common.transport.Headers;
import com.aliyun.datahub.rest.DatahubHttpHeaders;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Datahub请求签名工具
 */
public class AliyunRequestSigner implements RequestSigner {

    private static final Logger log = Logger.getLogger(AliyunRequestSigner.class
            .getName());

    private String accessId;
    private String accessKey;
    private String securityToken;

    public AliyunRequestSigner(String accessId, String accessKey) {
        if (accessId == null || accessId.length() == 0) {
            throw new IllegalArgumentException("AccessId should not be empty.");
        }
        if (accessKey == null || accessKey.length() == 0) {
            throw new IllegalArgumentException("AccessKey should not be empty.");
        }

        this.securityToken = null;

        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    public AliyunRequestSigner(String accessId, String accessKey, String securityToken) {
        if (accessId == null || accessId.length() == 0) {
            throw new IllegalArgumentException("AccessId should not be empty.");
        }
        if (accessKey == null || accessKey.length() == 0) {
            throw new IllegalArgumentException("AccessKey should not be empty.");
        }

        this.securityToken = securityToken;

        this.accessId = accessId;
        this.accessKey = accessKey;
    }

    @Override
    public void sign(String resource, DefaultRequest req) {
        if (securityToken != null && !securityToken.isEmpty()) {
            req.getHeaders().put(DatahubHttpHeaders.HEADER_DATAHUB_SECURITY_TOKEN, securityToken);
        }
        req.getHeaders().put(Headers.AUTHORIZATION, getSignature(resource, req));
    }

    public String getSignature(String resource, DefaultRequest req) {
        try {
            resource = URLDecoder.decode(resource, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        String strToSign = SecurityUtils.buildCanonicalString(resource, req, "x-datahub-");

        if (log.isLoggable(Level.FINE)) {
            log.fine("String to sign: " + strToSign);
        }

        byte[] crypto = SecurityUtils.hmacsha1Signature(strToSign.getBytes(),
                accessKey.getBytes());

        String signature = Base64.encodeBase64String(crypto).trim();

        return "DATAHUB " + accessId + ":" + signature;
    }
}
