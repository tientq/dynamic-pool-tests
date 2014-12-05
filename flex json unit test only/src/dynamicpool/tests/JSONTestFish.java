package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestFish {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLine() {
		Fish fish = createLine();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		Fish r = new JSONDeserializer<Fish>().deserialize(s);
		assertTrue(r instanceof Fish);
		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testCycle() {
		Fish fish = createCycle();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		Fish r = new JSONDeserializer<Fish>().deserialize(s);
		assertTrue(r instanceof Fish);
		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testSin() {
		Fish fish = createSin();

		String s = new JSONSerializer().serialize(fish);
		System.out.println(s);

		Fish r = new JSONDeserializer<Fish>().deserialize(s);
		assertTrue(r instanceof Fish);
		assertTrue(r.equals(fish));

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void TestList() {
		List<Fish> arrayList = new ArrayList<Fish>();
		Fish f1 = createLine();
		Fish f2 = createCycle();
		Fish f3 = createSin();
		Fish f4 = createSin();
		Fish f5 = createLine();
		arrayList.add(f1);
		arrayList.add(f2);
		arrayList.add(f3);
		arrayList.add(f4);
		arrayList.add(f5);
		
		String s = new JSONSerializer().serialize(arrayList);
		System.err.println(s);
		
		List<Fish> r = new JSONDeserializer<List<Fish>>().deserialize(s);
		assertTrue(r instanceof ArrayList);
		assertTrue(r.size() == arrayList.size());
		assertTrue(f1.equals(r.get(0)));
		assertTrue(f2.equals(r.get(1)));
		assertTrue(f3.equals(r.get(2)));
		assertTrue(f4.equals(r.get(3)));
		assertTrue(f5.equals(r.get(4)));
		
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}

	private Fish createLine() {
		Fish fish = FishFactory.createFishWithLineTrajectory(100, 51, FishType.FISH1);
		fish.updateLocation(0.235f);
		return fish;
	}

	private Fish createCycle() {
		Fish fish = FishFactory.createFishWithCycleTrajectory(40, 15, FishType.FISH2);
		fish.updateLocation(0.4345f);
		return fish;
	}

	private Fish createSin() {
		Fish fish = FishFactory.createFishWithSinTrajectory(45, 20, FishType.FISH3);
		fish.updateLocation(6.653f);
		return fish;
	}

}
