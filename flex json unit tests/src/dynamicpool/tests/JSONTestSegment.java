package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dynamicpool.bll.model.EDirection;
import dynamicpool.bll.model.Segment;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestSegment {
	
	@Test
	public void test() {
		Segment segment = new Segment(EDirection.RIGHT, 883.8324f, 423.234f);
		segment.setNeighbourClientName("just a test");
		
		String s = new JSONSerializer().serialize(segment);
		System.out.println(s);
		assertTrue(s.equals("{\"beginPoint\":883.8324,\"class\":\"dynamicpool.bll.model.Segment\",\"endPoint\":423.234,\"neighbourClientName\":\"just a test\",\"segmentDirection\":\"RIGHT\"}"));
		
		Segment r = new JSONDeserializer<Segment>().deserialize(s);
		assertTrue(r.equals(segment));
	}
	
	@Test
	public void testNull() {
		Segment segment = new Segment(EDirection.RIGHT, 883.8324f, 423.234f);
		
		String s = new JSONSerializer().serialize(segment);
		System.out.println(s);
		assertTrue(s.equals("{\"beginPoint\":883.8324,\"class\":\"dynamicpool.bll.model.Segment\",\"endPoint\":423.234,\"neighbourClientName\":null,\"segmentDirection\":\"RIGHT\"}"));
		
		Segment r = new JSONDeserializer<Segment>().deserialize(s);
		assertTrue(r.equals(segment));
	}

}
