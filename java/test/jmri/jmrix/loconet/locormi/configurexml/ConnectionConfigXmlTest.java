package jmri.jmrix.loconet.locormi.configurexml;

import jmri.util.JUnitUtil;
import jmri.util.JUnitAppender;
import org.junit.*;
import jmri.jmrix.loconet.locormi.ConnectionConfig;

/**
 * ConnectionConfigXmlTest.java
 *
 * Description: tests for the ConnectionConfigXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class ConnectionConfigXmlTest extends jmri.jmrix.configurexml.AbstractSerialConnectionConfigXmlTestBase {

    // The minimal setup for log4J
    @Before
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        xmlAdapter = new ConnectionConfigXml();
        cc = new ConnectionConfig();
    }

    @After
    @Override
    public void tearDown() {
        JUnitUtil.tearDown();
        xmlAdapter = null;
        cc = null;
    }

    @Test
    @Override
    public void getInstanceTest() {
       super.getInstanceTest();
       JUnitAppender.assertErrorMessage("unexpected call to getInstance");
    }

    @Test(timeout=5000)
    @Override
    public void loadTest() throws jmri.configurexml.JmriConfigureXmlException {
       super.loadTest();
       JUnitAppender.assertErrorMessageStartsWith("Exception while trying to connect: java.rmi.ConnectException: Connection refused to host: 127.0.0.1;");
       JUnitAppender.suppressErrorMessage("Error opening connection to");
    }

}
