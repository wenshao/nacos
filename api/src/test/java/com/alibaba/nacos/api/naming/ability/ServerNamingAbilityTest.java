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

package com.alibaba.nacos.api.naming.ability;

import com.alibaba.fastjson2.JSON;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ServerNamingAbilityTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }
    
    @Test
    public void testDeserializeServerNamingAbilityForNonExistItem() {
        String nonExistItemJson = "{\"exampleAbility\":false}";
        ServerNamingAbility actual = JSON.parseObject(nonExistItemJson, ServerNamingAbility.class);
        assertFalse(actual.isSupportJraft());
    }
    
    @Test
    public void testEquals() {
        ServerNamingAbility expected = new ServerNamingAbility();
        expected.setSupportJraft(true);
        String serializeJson = JSON.toJSONString(expected);
        ServerNamingAbility actual = JSON.parseObject(serializeJson, ServerNamingAbility.class);
        assertEquals(expected, actual);
        actual = new ServerNamingAbility();
        assertNotEquals(expected, actual);
        actual.setSupportJraft(true);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEqualsForOneObject() {
        ServerNamingAbility ability = new ServerNamingAbility();
        assertTrue(ability.equals(ability));
    }
    
    @Test
    public void testEqualsForOtherAbility() {
        ServerNamingAbility ability = new ServerNamingAbility();
        assertFalse(ability.equals(new ClientNamingAbility()));
    }
    
    @Test
    public void testHashCode() {
        ServerNamingAbility expected = new ServerNamingAbility();
        expected.setSupportJraft(true);
        String serializeJson = JSON.toJSONString(expected);
        ServerNamingAbility actual = JSON.parseObject(serializeJson, ServerNamingAbility.class);
        assertEquals(expected, actual);
        actual = new ServerNamingAbility();
        assertNotEquals(expected, actual);
        actual.setSupportJraft(true);
        assertEquals(expected.hashCode(), actual.hashCode());
    }
}
