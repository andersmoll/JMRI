package apps.DispatcherPro;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

/**
 * This is more of an acceptance test than a unit test. It confirms that the entire
 * application can start up and configure itself.
 * 
 * @author Paul Bender Copyright (C) 2017
 * @author Bob Jacobsen Copyright (C) 2017
 */
public class DispatcherProTest extends apps.LaunchJmriAppBase {

    protected void launch(String[] args) {
        DispatcherPro.main(args);
    }
    
    @Test
    @Ignore("Replaced with a Cucumber test")
    public void testLaunchLocoNet() throws IOException {
        runOne("LocoNet_Simulator", "DispatcherPro", "DispatcherPro version");
    }

    @Test
    @Ignore("Replaced with a Cucumber test")
    public void testLaunchEasyDcc() throws IOException {
        runOne("EasyDcc_Simulator", "DispatcherPro", "DispatcherPro version");
    }

    @Test
    @Ignore("Replaced with a Cucumber test")
    public void testLaunchGrapevine() throws IOException {
        runOne("Grapevine_Simulator", "DispatcherPro", "DispatcherPro version");
        jmri.util.JUnitAppender.suppressWarnMessage("Timeout can't be handled due to missing node (index 1)");
        jmri.util.JUnitAppender.suppressWarnMessage("Timeout can't be handled due to missing node (index 0)");
    }

    @Test
    public void testLaunchTmcc() throws IOException {
        runOne("TMCC_Simulator", "DispatcherPro", "DispatcherPro version");
    }

    @Test
    @Ignore("Replaced with a Cucumber test")
    public void testLaunchInitLoop() throws IOException {
        runOne("Prevent_Init_Loop", "DispatcherPro", "DispatcherPro version");
    }
}
