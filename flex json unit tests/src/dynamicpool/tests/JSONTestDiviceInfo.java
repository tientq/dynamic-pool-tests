package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dynamicpool.bll.model.DeviceInfo;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestDiviceInfo {
	
	@Test
	public void test() {
		DeviceInfo deviceInfo = new DeviceInfo(13456.5f, 883.8324f, 23.234f);
		deviceInfo.setClientName("35412d56-1bc1-4a2d-9d5d-51fb648227fa");
		
		String s = new JSONSerializer().serialize(deviceInfo);
		System.out.println(s);
		assertTrue(s.equals("{\"class\":\"dynamicpool.bll.model.DeviceInfo\",\"clientName\":\"35412d56-1bc1-4a2d-9d5d-51fb648227fa\",\"screenHeight\":883.8324,\"screenSize\":23.234,\"screenWidth\":13456.5}"));
		
		DeviceInfo r = new JSONDeserializer<DeviceInfo>().deserialize(s);
		assertTrue(r.equals(deviceInfo));
	}

	@Test
	public void testNullClientName() {
		DeviceInfo deviceInfo = new DeviceInfo(13456.5f, 883.8324f, 23.234f);
		deviceInfo.setClientName(null);
		
		String s = new JSONSerializer().serialize(deviceInfo);
		System.out.println(s);
		assertTrue(s.equals("{\"class\":\"dynamicpool.bll.model.DeviceInfo\",\"clientName\":null,\"screenHeight\":883.8324,\"screenSize\":23.234,\"screenWidth\":13456.5}"));
		
		DeviceInfo r = new JSONDeserializer<DeviceInfo>().deserialize(s);
		assertTrue(r.equals(deviceInfo));
	}
}
