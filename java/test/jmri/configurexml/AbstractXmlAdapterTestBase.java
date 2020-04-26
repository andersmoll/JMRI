package jmri.configurexml;

import org.junit.*;

/**
 * Base class for tests of classes inheriting and implementing AbstractXmlAdapter
 *
 * @author Paul Bender Copyright (C) 2018 
 */
abstract public class AbstractXmlAdapterTestBase {

    protected AbstractXmlAdapter xmlAdapter = null;

    @Test
    public void testCtor() {
        Assert.assertNotNull(xmlAdapter);
    }

    // The minimal setup for log4J
    @Before
    abstract public void setUp();

    @After
    abstract public void tearDown();

}
