package dynamicpool.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Trajectory;
import vn.edu.hust.student.dynamicpool.bll.model.TrajectoryFactory;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONTestAbtractTranjectory {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLine() {
		Trajectory trajectory = createLine();

		String s = new JSONSerializer().serialize(trajectory);
		System.out.println(s);

		Trajectory r = new JSONDeserializer<Trajectory>().deserialize(s);
		assertTrue(r.equals(trajectory));
		assertTrue(r instanceof LineTrajectory);

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testCycle() {
		Trajectory trajectory = createCycle();

		String s = new JSONSerializer().serialize(trajectory);
		System.out.println(s);

		Trajectory r = new JSONDeserializer<Trajectory>().deserialize(s);
		assertTrue(r.equals(trajectory));
		assertTrue(r instanceof CycleTrajectory);

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void testSin() {
		Trajectory trajectory = createSin();

		String s = new JSONSerializer().serialize(trajectory);
		System.out.println(s);

		Trajectory r = new JSONDeserializer<Trajectory>().deserialize(s);
		assertTrue(r.equals(trajectory));
		assertTrue(r instanceof SinTrajectory);

		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s.equals(s2));
	}

	@Test
	public void TestList() {
		List<Trajectory> arrayList = new ArrayList<Trajectory>();
		Trajectory trajectory1 = createLine();
		Trajectory trajectory2 = createCycle();
		Trajectory trajectory3 = createSin();
		Trajectory trajectory4 = createSin();
		Trajectory trajectory5 = createLine();
		arrayList.add(trajectory1);
		arrayList.add(trajectory2);
		arrayList.add(trajectory3);
		arrayList.add(trajectory4);
		arrayList.add(trajectory5);
		
		String s = new JSONSerializer().serialize(arrayList);
		System.err.println(s);
		
		List<Trajectory> r = new JSONDeserializer<List<Trajectory>>().deserialize(s);
		assertTrue(r instanceof ArrayList);
		assertTrue(r.size() == arrayList.size());
		assertTrue(r.get(0) instanceof LineTrajectory);
//		assertTrue(r.get(0).equals(trajectory1));
		assertTrue(r.get(1) instanceof CycleTrajectory);
//		assertTrue(r.get(1).equals(trajectory2));
		assertTrue(r.get(2) instanceof SinTrajectory);
//		assertTrue(r.get(2).equals(trajectory3));
		assertTrue(r.get(3) instanceof SinTrajectory);
//		assertTrue(r.get(3).equals(trajectory4));
		assertTrue(r.get(4) instanceof LineTrajectory);
//		assertTrue(r.get(4).equals(trajectory5));
		
		String s2 = new JSONSerializer().serialize(r);
		assertTrue(s2.equals(s));
	}

	private Trajectory createLine() {
		Point point = new Point(400, 240);
		Trajectory trajectory = TrajectoryFactory.createLineTrajectory(point);
		trajectory.updateLocation(point, 12.403f);
		return trajectory;
	}

	private Trajectory createCycle() {
		Point point = new Point(400, 240);
		Trajectory trajectory = TrajectoryFactory.createCycleTrajectory(point);
		trajectory.updateLocation(point, 0.403f);
		return trajectory;
	}

	private Trajectory createSin() {
		Point point = new Point(400, 240);
		Trajectory trajectory = TrajectoryFactory.createSinTrajectory(point);
		trajectory.updateLocation(point, 0.956f);
		return trajectory;
	}

}
