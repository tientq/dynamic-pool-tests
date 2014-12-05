package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.ClientSetting;
import vn.edu.hust.student.dynamicpool.bll.model.EDirection;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestClientSetting {
	
	@Test
	public void testNullSegment() {
		ClientSetting setting = new ClientSetting();
		setting.setScale(0.3245f);
		setting.setWidth(1366f);
		setting.setHeight(768f);
		
		String s = new JSONSerializer().serialize(setting);
		System.out.println(s);
		
		ClientSetting r = new JSONDeserializer<ClientSetting>().deserialize(s);
		assertTrue(r.equals(setting));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}
	
	@Test
	public void testEmptySegments() {
		ClientSetting setting = new ClientSetting();
		setting.setScale(0.3245f);
		setting.setWidth(1366f);
		setting.setHeight(768f);
		setting.setClientName("client 2");
		List<Segment> segments = new ArrayList<Segment>();
		setting.setSegments(segments);
		
		String s = new JSONSerializer().serialize(setting);
		System.out.println(s);
		
		ClientSetting r = new JSONDeserializer<ClientSetting>().deserialize(s);
		assertTrue(r.equals(setting));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}
	
	@Test
	public void testSegments() {
		ClientSetting setting = new ClientSetting();
		setting.setScale(0.3245f);
		setting.setWidth(1366f);
		setting.setHeight(768f);
		setting.setClientName("client 2");
		List<Segment> segments = new ArrayList<Segment>();
		Segment segment1 = new Segment(EDirection.LEFT, 0, 720);
		Segment segment2 = new Segment(EDirection.TOP, 10, 100);
		segments.add(segment1);
		segments.add(segment2);
		setting.setSegments(segments);
		
		String s = new JSONSerializer().include("segments").serialize(setting);
		System.out.println(s);
		
		ClientSetting r = new JSONDeserializer<ClientSetting>().deserialize(s);
		assertTrue(r.equals(setting));
		
		String s2 = new JSONSerializer().include("segments").serialize(r);
		assertTrue(s.equals(s2));
	}
}
