package dynamicpool.tests;

import junit.framework.TestCase;

import org.junit.Test;

import dynamicpool.bll.model.CycleTrajectory;
import dynamicpool.bll.model.Point;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestCycleTrajectory extends TestCase {
	
	@Test
	public void test() {
		Point point = new Point(100.123f, -123.1234f);
		CycleTrajectory cycleTrajectory = new CycleTrajectory(point);
		cycleTrajectory.setTimeState(134445.342524);
		cycleTrajectory.init(1335.345f, -23423f, 10.32f, 123.1234f);
		
		String s = new JSONSerializer().serialize(cycleTrajectory);
		System.out.println(s);
		assertTrue(s.equals("{\"a\":10.32,\"centerX\":1335.345,\"centerY\":-23423.0,\"class\":\"dynamicpool.bll.model.CycleTrajectory\",\"d\":123.1234,\"location\":{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234},\"timeState\":134445.342524}"));
		
		CycleTrajectory r = new JSONDeserializer<CycleTrajectory>().deserialize(s);
		assertTrue(r.equals(cycleTrajectory));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals("{\"a\":10.32,\"centerX\":1335.345,\"centerY\":-23423.0,\"class\":\"dynamicpool.bll.model.CycleTrajectory\",\"d\":123.1234,\"location\":{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234},\"timeState\":134445.342524}"));
	}
}
