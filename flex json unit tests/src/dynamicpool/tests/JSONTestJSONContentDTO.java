package dynamicpool.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dynamicpool.bll.model.ClientSetting;
import dynamicpool.bll.model.EDirection;
import dynamicpool.bll.model.Fish;
import dynamicpool.bll.model.FishFactory;
import dynamicpool.bll.model.FishPackage;
import dynamicpool.bll.model.FishType;
import dynamicpool.bll.model.IFish;
import dynamicpool.bll.model.Segment;
import dynamicpool.dal.dto.JSONContentDTO;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestJSONContentDTO {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFish() {
		IFish fish = createSin();
		JSONContentDTO dto = JSONContentDTO.fromFish(fish);
		
		String s = new JSONSerializer().serialize(dto);
		System.out.println(s);
		
		JSONContentDTO r = new JSONDeserializer<JSONContentDTO>().deserialize(s);
		assert(r.equals(dto));
		
		try {
			IFish fishResult = JSONContentDTO.toFish(r);
			assertTrue(Fish.equals(fish, fishResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}

	private IFish createSin() {
		IFish fish = FishFactory.createFishWithSinTrajectory(45, 20, FishType.FISH3);
		fish.updateLocation(6.653f);
		return fish;
	}
	
	@Test
	public void testFishPackage() {
		IFish fish = createSin();
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
}
