package Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class LoginUtilTest {

    LoginUtil LoginUtil = new LoginUtil();

    @Test
    void getClientId() {
        Assertions.assertEquals(LoginUtil.getClientId(),"StrainGauge");
    }

    @Test
    void getUsername() {
        Assertions.assertEquals(LoginUtil.getUsername(0),"vjkulmgc");
        Assertions.assertEquals(LoginUtil.getUsername(3),"pcyhumbk");
    }

    @Test
    void getPassword() {
        Assertions.assertEquals(LoginUtil.getPassword(0),"FGJ6y0KrhOdG");
        Assertions.assertEquals(LoginUtil.getPassword(3),"xWP7Jbwp9Rts");
    }

    @Test
    void getPort() {
        Assertions.assertEquals(LoginUtil.getPort(0),"15561");
        Assertions.assertEquals(LoginUtil.getPort(3),"19596");
    }

    @Test
    void getTopic() {
        Assertions.assertEquals(LoginUtil.getTopic(0),"Sematek.Stjernelaks.StrainGauge0");
        Assertions.assertEquals(LoginUtil.getTopic(3),"Sematek.Stjernelaks.StrainGauge3");
    }

    @Test
    void getServerUrl() {
        Assertions.assertEquals(LoginUtil.getServerUrl(),"m20.cloudmqtt.com");


    }

    @Test
    void getURI() throws URISyntaxException {
        Assertions.assertEquals("tcp://m20.cloudmqtt.com:15561",LoginUtil.getURI(0).toString());
        Assertions.assertEquals("tcp://m20.cloudmqtt.com:19596",LoginUtil.getURI(3).toString());


    }
}