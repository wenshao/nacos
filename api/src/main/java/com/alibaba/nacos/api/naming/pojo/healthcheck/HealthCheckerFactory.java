/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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

package com.alibaba.nacos.api.naming.pojo.healthcheck;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.nacos.api.exception.runtime.NacosDeserializationException;
import com.alibaba.nacos.api.exception.runtime.NacosSerializationException;
import com.alibaba.nacos.api.naming.pojo.healthcheck.AbstractHealthChecker.None;

/**
 * health checker factory.
 *
 * @author yangyi
 */
public class HealthCheckerFactory {
    
    /**
     * Register new sub type of health checker to factory for serialize and deserialize.
     *
     * @param extendHealthCheckerClass extend health checker
     * @param typeName                 typeName of health checker
     */
    public static void registerSubType(Class<? extends AbstractHealthChecker> extendHealthCheckerClass,
            String typeName) {
        JSON.registerSeeAlsoSubType(extendHealthCheckerClass, typeName);
    }
    
    /**
     * Create default {@link None} health checker.
     *
     * @return new none health checker
     */
    public static None createNoneHealthChecker() {
        return new None();
    }
    
    /**
     * Deserialize and create a instance of health checker.
     *
     * @param jsonString json string of health checker
     * @return new instance
     */
    public static AbstractHealthChecker deserialize(String jsonString) {
        try {
            return JSON.parseObject(jsonString, AbstractHealthChecker.class);
        } catch (JSONException ex) {
            throw new NacosDeserializationException(AbstractHealthChecker.class, ex);
        }
    }
    
    /**
     * Serialize a instance of health checker to json.
     *
     * @param healthChecker health checker instance
     * @return son string after serializing
     */
    public static String serialize(AbstractHealthChecker healthChecker) {
        try {
            return JSON.toJSONString(healthChecker);
        } catch (Throwable ex) {
            throw new NacosSerializationException(healthChecker.getClass(), ex);
        }
    }
}
