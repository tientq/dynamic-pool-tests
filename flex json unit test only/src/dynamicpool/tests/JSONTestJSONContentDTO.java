package dynamicpool.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.ClientSetting;
import vn.edu.hust.student.dynamicpool.bll.model.EDirection;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishPackage;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.JSONContentDTO;
import vn.edu.hust.student.dynamicpool.bll.model.Segment;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestJSONContentDTO {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFish() {
		Fish fish = createSin();
		JSONContentDTO dto = JSONContentDTO.fromFish(fish);
		
		String s = new JSONSerializer().serialize(dto);
		System.out.println(s);
		
		JSONContentDTO r = new JSONDeserializer<JSONContentDTO>().deserialize(s);
		assert(r.equals(dto));
		
		try {
			Fish fishResult = JSONContentDTO.toFish(r);
			assertTrue(fish.equals(fishResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	private Fish createSin() {
		Fish fish = FishFactory.createFishWithSinTrajectory(45, 20, FishType.FISH3);
		fish.updateLocation(6.653f);
		return fish;
	}
	
	@Test
	public void testFishPackage() {
		Fish fish = createSin();
		FishPackage fishPackage = new FishPackage();
		fishPackage.setClientName("just a client name");
		fishPackage.setFish(fish);
		JSONContentDTO dto = JSONContentDTO.fromFishPackage(fishPackage);
		
		String s = new JSONSerializer().serialize(dto);
		System.out.println(s);
		
		JSONContentDTO r = new JSONDeserializer<JSONContentDTO>().deserialize(s);
		assert(r.equals(dto));
		
		try {
			FishPackage fishPackageResult = JSONContentDTO.toFishPackage(r);
			assertTrue(fishPackage.equals(fishPackageResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testClientSetting() {
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
		
		JSONContentDTO dto = JSONContentDTO.fromClientSetting(setting);
		
		String s = new JSONSerializer().serialize(dto);
		System.out.println(s);
		
		JSONContentDTO r = new JSONDeserializer<JSONContentDTO>().deserialize(s);
		assert(r.equals(dto));
		
		try {
			ClientSetting clientSetting = JSONContentDTO.toClientSetting(r);
			assertTrue(clientSetting.equals(setting));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSendFishError() {
		String jsString = "{\"jsonContent\":\"{\\u0022boundary\\u0022:{\\u0022class\\u0022:\\u0022vn.edu.hust.student.dynamicpool.bll.model.Boundary\\u0022,\\u0022height\\u0022:51.0,\\u0022location\\u0022:{\\u0022class\\u0022:\\u0022vn.edu.hust.student.dynamicpool.bll.model.Point\\u0022,\\u0022x\\u0022:400.0,\\u0022y\\u0022:240.0},\\u0022width\\u0022:100.0},\\u0022class\\u0022:\\u0022vn.edu.hust.student.dynamicpool.bll.model.Fish\\u0022,\\u0022fishId\\u0022:\\u0022eaee606e-1fe5-4308-8e2c-96577604087f\\u0022,\\u0022fishState\\u0022:\\u0022INSIDE\\u0022,\\u0022fishType\\u0022:\\u0022FISH1\\u0022,\\u0022trajectory\\u0022:{\\u0022class\\u0022:\\u0022vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory\\u0022,\\u0022dx\\u0022:1.0,\\u0022dy\\u0022:1.0,\\u0022location\\u0022:{\\u0022class\\u0022:\\u0022vn.edu.hust.student.dynamicpool.bll.model.Point\\u0022,\\u0022x\\u0022:400.0,\\u0022y\\u0022:240.0},\\u0022timeState\\u0022:0.0}}\"}";
		try {
			JSONContentDTO dto = JSONContentDTO.createDTOfromJSONString(jsString);
			Fish fish = JSONContentDTO.toFish(dto);
			assertTrue(fish != null);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
//	@Test
//	public void testSendFishE2() {
//		String jsString = "{\"class\":\"vn.edu.hust.student.dynamicpool.bll.model.FishPackage\",\"clientName\":\"a15ea103-c5cb-4535-85d6-6d12d42f5dff\",\"fish\":{\"boundary\":{\"class\":\"vn.edu.hust.student.dynamicpool.bll.model.Boundary\",\"height\":51.0,\"location\":{\"class\":\"vn.edu.hust.student.dynamicpool.bll.model.Point\",\"x\":-98.63092,\"y\":120.4139},\"width\":100.0},\"class\":\"vn.edu.hust.student.dynamicpool.bll.model.Fish\",\"fishId\":\"17c19759-30c5-4183-90dd-c11f5b3e36c5\",\"fishState\":\"RETURN\",\"fishType\":\"FISH1\",\"passingDirection\":\"UNKNOWN\",\"trajectory\":{\"class\":\"vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory\",\"dx\":1.0,\"dy\":1.0,\"timeState\":295.50542836729437}}}";
//		try {
//			JSONContentDTO dto = JSONContentDTO.createDTOfromJSONString(jsString);
//			Fish fish = JSONContentDTO.toFish(dto);
//			assertTrue(fish != null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail();
//		}
//	}
}
