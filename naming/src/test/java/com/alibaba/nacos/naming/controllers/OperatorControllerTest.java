/*
 *  Copyright 1999-2021 Alibaba Group Holding Ltd.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.alibaba.nacos.naming.controllers;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.naming.cluster.ServerStatus;
import com.alibaba.nacos.naming.cluster.ServerStatusManager;
import com.alibaba.nacos.naming.core.DistroMapper;
import com.alibaba.nacos.naming.core.v2.client.Client;
import com.alibaba.nacos.naming.core.v2.client.impl.IpPortBasedClient;
import com.alibaba.nacos.naming.core.v2.client.manager.ClientManager;
import com.alibaba.nacos.naming.core.v2.pojo.InstancePublishInfo;
import com.alibaba.nacos.naming.core.v2.pojo.Service;
import com.alibaba.nacos.naming.misc.SwitchDomain;
import com.alibaba.nacos.naming.misc.SwitchManager;
import com.alibaba.nacos.naming.monitor.MetricsMonitor;
import com.alibaba.nacos.sys.env.Constants;
import com.alibaba.nacos.sys.env.EnvUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Collection;
import java.util.HashSet;

/**
 * {@link OperatorController} unit test.
 *
 * @author chenglu
 * @date 2021-07-21 19:28
 */
@RunWith(MockitoJUnitRunner.class)
public class OperatorControllerTest {
    
    @InjectMocks
    private OperatorController operatorController;
    
    @Mock
    private SwitchDomain switchDomain;
    
    @Mock
    private SwitchManager switchManager;
    
    @Mock
    private ServerStatusManager serverStatusManager;
    
    @Mock
    private ClientManager clientManager;
    
    @Mock
    private DistroMapper distroMapper;
    
    @Before
    public void setUp() {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty(Constants.SUPPORT_UPGRADE_FROM_1X, "true");
        EnvUtil.setEnvironment(environment);
    }
    
    @Test
    public void testPushState() {
        MetricsMonitor.resetPush();
        JSONObject objectNode = operatorController.pushState(true, true);
        Assert.assertTrue(objectNode.toString().contains("succeed\":0"));
    }
    
    @Test
    public void testSwitchDomain() {
        SwitchDomain switchDomain = operatorController.switches(new MockHttpServletRequest());
        Assert.assertEquals(this.switchDomain, switchDomain);
    }
    
    @Test
    public void testUpdateSwitch() {
        try {
            String res = operatorController.updateSwitch(true, "test", "test");
            Assert.assertEquals("ok", res);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void testMetrics() {
        Mockito.when(serverStatusManager.getServerStatus()).thenReturn(ServerStatus.UP);
        Collection<String> clients = new HashSet<>();
        clients.add("1628132208793_127.0.0.1_8080");
        clients.add("127.0.0.1:8081#true");
        clients.add("127.0.0.1:8082#false");
        Mockito.when(clientManager.allClientId()).thenReturn(clients);
        Client client = new IpPortBasedClient("127.0.0.1:8081#true", true);
        client.addServiceInstance(Service.newService("", "", ""), new InstancePublishInfo());
        Mockito.when(clientManager.getClient("127.0.0.1:8081#true")).thenReturn(client);
        Mockito.when(clientManager.isResponsibleClient(client)).thenReturn(Boolean.TRUE);
        
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addParameter("onlyStatus", "false");
        JSONObject objectNode = operatorController.metrics(servletRequest);
        
        Assert.assertEquals(1, objectNode.getIntValue("responsibleInstanceCount"));
        Assert.assertEquals(ServerStatus.UP.toString(), objectNode.getString("status"));
        Assert.assertEquals(3, objectNode.getIntValue("clientCount"));
        Assert.assertEquals(1, objectNode.getIntValue("connectionBasedClientCount"));
        Assert.assertEquals(1, objectNode.getIntValue("ephemeralIpPortClientCount"));
        Assert.assertEquals(1, objectNode.getIntValue("persistentIpPortClientCount"));
        Assert.assertEquals(1, objectNode.getIntValue("responsibleClientCount"));
    }
    
    @Test
    public void testGetResponsibleServer4Client() {
        Mockito.when(distroMapper.mapSrv(Mockito.anyString())).thenReturn("test");
        JSONObject objectNode = operatorController.getResponsibleServer4Client("test", "test");
        Assert.assertEquals("test", objectNode.getString("responsibleServer"));
    }
    
    @Test
    public void testSetLogLevel() {
        String res = operatorController.setLogLevel("test", "info");
        Assert.assertEquals("ok", res);
    }
}
