package dynamicpool.tests;

import junit.framework.TestCase;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestLineTrajectory extends TestCase {
	
	@Test
	public void test() {
		Point point = new Point(100.123f, -123.1234f);
		LineTrajectory lineTrajectory = new LineTrajectory();
		lineTrajectory.setTimeState(134445.342524);
		lineTrajectory.init(2.1412f, 3.1234f);
		lineTrajectory.updateLocation(point, 2.434f);
		
		String s = new JSONSerializer().serialize(lineTrajectory);
		System.out.println(s);
		
		LineTrajectory r = new JSONDeserializer<LineTrajectory>().deserialize(s);
		assertTrue(r.equals(lineTrajectory));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}
}
