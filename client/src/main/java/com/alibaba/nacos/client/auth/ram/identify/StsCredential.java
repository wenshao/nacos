/*
 * Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.client.auth.ram.identify;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;

/**
 * Sts credential for aliyun RAM.
 *
 * @author xiweng.yy
 */
public class StsCredential {
    
    @JSONField(name = "AccessKeyId")
    private String accessKeyId;
    
    @JSONField(name = "AccessKeySecret")
    private String accessKeySecret;
    
    @JSONField(name = "Expiration")
    private Date expiration;
    
    @JSONField(name = "SecurityToken")
    private String securityToken;
    
    @JSONField(name = "LastUpdated")
    private Date lastUpdated;
    
    @JSONField(name = "Code")
    private String code;
    
    public String getAccessKeyId() {
        return accessKeyId;
    }
    
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }
    
    public String getAccessKeySecret() {
        return accessKeySecret;
    }
    
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
    
    public Date getExpiration() {
        return expiration;
    }
    
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    
    public String getSecurityToken() {
        return securityToken;
    }
    
    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }
    
    public Date getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return "STSCredential{" + "accessKeyId='" + accessKeyId + '\'' + ", accessKeySecret='" + accessKeySecret
                + '\'' + ", expiration=" + expiration + ", securityToken='" + securityToken + '\''
                + ", lastUpdated=" + lastUpdated + ", code='" + code + '\'' + '}';
    }
}
