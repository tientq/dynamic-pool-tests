package dynamicpool.tests;

import junit.framework.TestCase;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestSinTrajectory extends TestCase {
	
	@Test
	public void test() {
		Point point = new Point(100.123f, -123.1234f);
		SinTrajectory sinTrajectory = new SinTrajectory(point);
		sinTrajectory.setTimeState(134445.342524);
		sinTrajectory.init(1335.345f, -23423f, 10.32f);
		
		String s = new JSONSerializer().serialize(sinTrajectory);
		System.out.println(s);
		
		SinTrajectory r = new JSONDeserializer<SinTrajectory>().deserialize(s);
		assertTrue(r.equals(sinTrajectory));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}
}
