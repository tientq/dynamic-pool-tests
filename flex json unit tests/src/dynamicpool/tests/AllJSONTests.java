package dynamicpool.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JSONTestAbtractTranjectory.class, JSONTestBoundary.class,
		JSONTestCycleTrajectory.class, JSONTestDiviceInfo.class,
		JSONTestLineTrajectory.class, JSONTestPoint.class,
		JSONTestSegment.class, JSONTestSinTrajectory.class, JSONTestFish.class,
		JSONTestFishDTO.class, JSONTestFishPackage.class,
		JSONTestJSONContentDTO.class, JSONTestClientSetting.class })
public class AllJSONTests {

}
