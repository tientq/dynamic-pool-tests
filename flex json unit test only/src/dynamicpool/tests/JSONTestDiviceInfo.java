package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestDiviceInfo {
	
	@Test
	public void test() {
		DeviceInfo deviceInfo = new DeviceInfo(13456.5f, 883.8324f, 23.234f);
		deviceInfo.setClientName("35412d56-1bc1-4a2d-9d5d-51fb648227fa");
		
		String s = new JSONSerializer().serialize(deviceInfo);
		System.out.println(s);
		
		DeviceInfo r = new JSONDeserializer<DeviceInfo>().deserialize(s);
		assertTrue(r.equals(deviceInfo));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testNullClientName() {
		DeviceInfo deviceInfo = new DeviceInfo(13456.5f, 883.8324f, 23.234f);
		deviceInfo.setClientName(null);
		
		String s = new JSONSerializer().serialize(deviceInfo);
		System.out.println(s);
		
		DeviceInfo r = new JSONDeserializer<DeviceInfo>().deserialize(s);
		assertTrue(r.equals(deviceInfo));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}
}
