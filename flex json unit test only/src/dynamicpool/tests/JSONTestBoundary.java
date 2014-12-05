package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestBoundary {
	
	@Test
	public void test() {
		Point point = new Point(100.123f, -123.1234f);
		Boundary boundary = new Boundary(point, 883.8324f, 423.234f);
		
		String s = new JSONSerializer().serialize(boundary);
		System.out.println(s);
		
		Boundary r = new JSONDeserializer<Boundary>().deserialize(s);
		assertTrue(r.equals(boundary));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

}
