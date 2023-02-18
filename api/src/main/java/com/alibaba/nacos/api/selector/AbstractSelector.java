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

package com.alibaba.nacos.api.selector;

import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;

import java.io.Serializable;

/**
 * Abstract selector that only contains a type.
 *
 * @author nkorange
 * @since 0.7.0
 */
@JSONType(typeKey = "type", seeAlso = NoneSelector.class)
public abstract class AbstractSelector implements Serializable {
    
    private static final long serialVersionUID = 4530233098102379229L;
    
    /**
     * The type of this selector, each child class should announce its own unique type.
     */
    @JSONField(serialize = false, deserialize = false)
    private final String type;
    
    protected AbstractSelector(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
