package dynamicpool.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dynamicpool.bll.model.Boundary;
import dynamicpool.bll.model.Point;
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
		assertTrue(s.equals("{\"class\":\"dynamicpool.bll.model.Boundary\",\"height\":423.234,\"location\":{\"class\":\"dynamicpool.bll.model.Point\",\"x\":100.123,\"y\":-123.1234},\"width\":883.8324}"));
	}

}
