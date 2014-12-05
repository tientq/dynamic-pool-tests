package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Point;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestPoint {
	
	@Test
	public void testPoint() {
		Point point = new Point(100.123f, -123.1234f);
		
		String s = new JSONSerializer().serialize(point);
		System.out.println(s);
		
		Point r = new JSONDeserializer<Point>().deserialize(s);
		assertTrue(r.equals(point));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

}
