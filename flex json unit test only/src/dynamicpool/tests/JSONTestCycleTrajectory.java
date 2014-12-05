package dynamicpool.tests;

import junit.framework.TestCase;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
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
		
		CycleTrajectory r = new JSONDeserializer<CycleTrajectory>().deserialize(s);
		assertTrue(r.equals(cycleTrajectory));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}
}
