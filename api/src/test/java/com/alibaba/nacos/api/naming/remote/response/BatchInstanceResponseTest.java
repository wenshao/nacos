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
import com.alibaba.nacos.api.naming.remote.NamingRemoteConstants;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BatchInstanceResponseTest {
    
    @BeforeClass
    public static void setUp() throws Exception {
    }
    
    @Test
    public void testSerialize() {
        BatchInstanceResponse response = new BatchInstanceResponse(NamingRemoteConstants.REGISTER_INSTANCE);
        String json = JSON.toJSONString(response);
        assertTrue(json.contains("\"type\":\"" + NamingRemoteConstants.REGISTER_INSTANCE + "\""));
    }
    
    @Test
    public void testDeserialize() {
        String json = "{\"resultCode\":200,\"errorCode\":0,\"type\":\"registerInstance\",\"success\":true}";
        BatchInstanceResponse response = JSON.parseObject(json, BatchInstanceResponse.class);
        assertEquals(NamingRemoteConstants.REGISTER_INSTANCE, response.getType());
    }
}