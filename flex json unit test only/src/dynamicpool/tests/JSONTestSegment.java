package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.EDirection;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestSegment {
	
	@Test
	public void test() {
		Segment segment = new Segment(EDirection.RIGHT, 883.8324f, 423.234f);
		segment.setNeighbourClientName("just a test");
		
		String s = new JSONSerializer().serialize(segment);
		System.out.println(s);
		
		Segment r = new JSONDeserializer<Segment>().deserialize(s);
		assertTrue(r.equals(segment));
		
		String s2 = new JSONSerializer().serialize(segment);
		assertTrue(s.equals(s2));
	}
	
	@Test
	public void testNull() {
		Segment segment = new Segment(EDirection.RIGHT, 883.8324f, 423.234f);
		
		String s = new JSONSerializer().serialize(segment);
		System.out.println(s);
		
		Segment r = new JSONDeserializer<Segment>().deserialize(s);
		assertTrue(r.equals(segment));
		
		String s2 = new JSONSerializer().serialize(segment);
		assertTrue(s.equals(s2));
	}

}
