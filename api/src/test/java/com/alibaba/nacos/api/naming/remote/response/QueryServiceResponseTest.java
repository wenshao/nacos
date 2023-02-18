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

package com.alibaba.nacos.api.naming.remote.response;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryServiceResponseTest {
    
    @BeforeClass
    public static void setUp() throws Exception {
    }
    
    @Test
    public void testSerializeSuccessResponse() {
        QueryServiceResponse response = QueryServiceResponse.buildSuccessResponse(new ServiceInfo());
        String json = JSON.toJSONString(response);
        assertTrue(json.contains("\"serviceInfo\":{"));
        assertTrue(json.contains("\"resultCode\":200"));
        assertTrue(json.contains("\"errorCode\":0"));
        assertTrue(json.contains("\"success\":true"));
    }
    
    @Test
    public void testSerializeFailResponse() {
        QueryServiceResponse response = QueryServiceResponse.buildFailResponse("test");
        String json = JSON.toJSONString(response);
        assertTrue(json.contains("\"resultCode\":500"));
        assertTrue(json.contains("\"errorCode\":0"));
        assertTrue(json.contains("\"message\":\"test\""));
        assertTrue(json.contains("\"success\":false"));
    }
    
    @Test
    public void testDeserialize() {
        String json = "{\"resultCode\":200,\"errorCode\":0,\"serviceInfo\":{\"cacheMillis\":1000,\"hosts\":[],"
                + "\"lastRefTime\":0,\"checksum\":\"\",\"allIPs\":false,\"reachProtectionThreshold\":false,"
                + "\"valid\":true},\"success\":true}";
        QueryServiceResponse response = JSON.parseObject(json, QueryServiceResponse.class);
        assertNotNull(response.getServiceInfo());
    }
    
}