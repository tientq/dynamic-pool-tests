package dynamicpool.tests;

import junit.framework.TestCase;

import org.junit.Test;

import dynamicpool.bll.model.LineTrajectory;
import dynamicpool.bll.model.Point;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestLineTrajectory extends TestCase {
	
	@Test
	public void test() {
		Point point = new Point(100.123f, -123.1234f);
		LineTrajectory lineTrajectory = new LineTrajectory(point);
		lineTrajectory.setTimeState(134445.342524);
		lineTrajectory.init(2.1412f, 3.1234f);
		
		String s = new JSONSerializer().serialize(lineTrajectory);
		System.out.println(s);
		assertTrue(s.equals("{\"class\":\"dynamicpool.bll.model.LineTrajectory\",\"dx\":2.1412,\"dy\":3.1234,\"location\":{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234},\"timeState\":134445.342524}"));
		
		LineTrajectory r = new JSONDeserializer<LineTrajectory>().deserialize(s);
		assertTrue(r.equals(lineTrajectory));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals("{\"class\":\"dynamicpool.bll.model.LineTrajectory\",\"dx\":2.1412,\"dy\":3.1234,\"location\":{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234},\"timeState\":134445.342524}"));
	}
}
