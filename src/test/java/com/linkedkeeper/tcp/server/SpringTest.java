package com.linkedkeeper.tcp.server;

import com.google.protobuf.ByteString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-tcp-test.xml"}) //加载配置文件

public class SpringTest {
    @Test
    public void testNotify() {
        ApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"classpath:spring-tcp-test.xml"});
      TestNotify testNotify = (TestNotify) context.getBean("testNotify");
        Map data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", "value3");
        ByteString byteString = ByteString.copyFrom(SerializationUtils.serialize((Serializable) data));
        try {
            testNotify.send(1l, "abc", 1002, byteString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
