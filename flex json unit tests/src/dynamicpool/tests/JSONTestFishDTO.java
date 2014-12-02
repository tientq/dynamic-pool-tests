package dynamicpool.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dynamicpool.bll.model.Fish;
import dynamicpool.bll.model.FishFactory;
import dynamicpool.bll.model.FishType;
import dynamicpool.bll.model.IFish;
import dynamicpool.dal.dto.FishDTO;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestFishDTO {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLine() {
		IFish fish = createLine();
		FishDTO fishDTO = FishDTO.fromFish(fish);

		String s = new JSONSerializer().serialize(fishDTO);
		System.out.println(s);

		FishDTO dtoResult = new JSONDeserializer<FishDTO>().deserialize(s);
		assertTrue(dtoResult.equals(fishDTO));
		IFish r = FishDTO.toFish(dtoResult);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);

		String s2 = new JSONSerializer().serialize(FishDTO.fromFish(r));
		assertTrue(s.equals(s2));
	}

	@Test
	public void testCycle() {
		IFish fish = createCycle();
		FishDTO fishDTO = FishDTO.fromFish(fish);

		String s = new JSONSerializer().serialize(fishDTO);
		System.out.println(s);

		FishDTO dtoResult = new JSONDeserializer<FishDTO>().deserialize(s);
		assertTrue(dtoResult.equals(fishDTO));
		IFish r = FishDTO.toFish(dtoResult);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);

		String s2 = new JSONSerializer().serialize(FishDTO.fromFish(r));
		assertTrue(s.equals(s2));
	}

	@Test
	public void testSin() {
		IFish fish = createSin();
		FishDTO fishDTO = FishDTO.fromFish(fish);

		String s = new JSONSerializer().serialize(fishDTO);
		System.out.println(s);

		FishDTO dtoResult = new JSONDeserializer<FishDTO>().deserialize(s);
		assertTrue(dtoResult.equals(fishDTO));
		IFish r = FishDTO.toFish(dtoResult);
		assertTrue(r instanceof IFish);
		assertTrue(r instanceof Fish);

		String s2 = new JSONSerializer().serialize(FishDTO.fromFish(r));
		assertTrue(s.equals(s2));
	}

	@Test
	public void TestList() {
		List<FishDTO> arrayList = new ArrayList<FishDTO>();
		IFish f1 = createLine();
		IFish f2 = createCycle();
		IFish f3 = createSin();
		IFish f4 = createSin();
		IFish f5 = createLine();
		FishDTO dto1 = FishDTO.fromFish(f1);
		FishDTO dto2 = FishDTO.fromFish(f2);
		FishDTO dto3 = FishDTO.fromFish(f3);
		FishDTO dto4 = FishDTO.fromFish(f4);
		FishDTO dto5 = FishDTO.fromFish(f5);
		arrayList.add(dto1);
		arrayList.add(dto2);
		arrayList.add(dto3);
		arrayList.add(dto4);
		arrayList.add(dto5);
		
		String s = new JSONSerializer().serialize(arrayList);
		System.err.println(s);
		
		List<FishDTO> r = new JSONDeserializer<List<FishDTO>>().deserialize(s);
		assertTrue(r instanceof ArrayList);
		assertTrue(r.size() == arrayList.size());
		FishDTO r1 = r.get(0);
		FishDTO r2 = r.get(1);
		FishDTO r3 = r.get(2);
		FishDTO r4 = r.get(3);
		FishDTO r5 = r.get(4);
		assertTrue(r1.equals(dto1));
		assertTrue(r2.equals(dto2));
		assertTrue(r3.equals(dto3));
		assertTrue(r4.equals(dto4));
		assertTrue(r5.equals(dto5));
		assertTrue(Fish.equals(f1, FishDTO.toFish(dto1)));
		assertTrue(Fish.equals(f2, FishDTO.toFish(dto2)));
		assertTrue(Fish.equals(f3, FishDTO.toFish(dto3)));
		assertTrue(Fish.equals(f4, FishDTO.toFish(dto4)));
		assertTrue(Fish.equals(f5, FishDTO.toFish(dto5)));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}

	private IFish createLine() {
		IFish fish = FishFactory.createFishWithLineTrajectory(100, 51, FishType.FISH1);
		fish.updateLocation(0.235f);
		return fish;
	}

	private IFish createCycle() {
		IFish fish = FishFactory.createFishWithCycleTrajectory(40, 15, FishType.FISH2);
		fish.updateLocation(0.4345f);
		return fish;
	}

	private IFish createSin() {
		IFish fish = FishFactory.createFishWithSinTrajectory(45, 20, FishType.FISH3);
		fish.updateLocation(6.653f);
		return fish;
	}
}
