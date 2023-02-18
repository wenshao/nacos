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
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServiceListResponseTest {
    
    @BeforeClass
    public static void setUp() throws Exception {
    }
    
    @Test
    public void testSerializeSuccessResponse() {
        ServiceListResponse response = ServiceListResponse.buildSuccessResponse(10, Collections.singletonList("a"));
        String json = JSON.toJSONString(response);
        assertTrue(json.contains("\"count\":10"));
        assertTrue(json.contains("\"serviceNames\":[\"a\"]"));
        assertTrue(json.contains("\"resultCode\":200"));
        assertTrue(json.contains("\"errorCode\":0"));
        assertTrue(json.contains("\"success\":true"));
    }
    
    @Test
    public void testSerializeFailResponse() {
        ServiceListResponse response = ServiceListResponse.buildFailResponse("test");
        String json = JSON.toJSONString(response);
        assertTrue(json.contains("\"resultCode\":500"));
        assertTrue(json.contains("\"errorCode\":500"));
        assertTrue(json.contains("\"message\":\"test\""));
        assertTrue(json.contains("\"success\":false"));
    }
    
    @Test
    public void testDeserialize() {
        String json = "{\"resultCode\":200,\"errorCode\":0,\"count\":10,\"serviceNames\":[\"a\"],\"success\":true}";
        ServiceListResponse response = JSON.parseObject(json, ServiceListResponse.class);
        assertEquals(10, response.getCount());
        assertEquals(1, response.getServiceNames().size());
        assertEquals("a", response.getServiceNames().get(0));
    }
    
}