package dynamicpool.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dynamicpool.bll.model.Point;
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
		assertTrue(s.equals("{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234}"));
	}

}
