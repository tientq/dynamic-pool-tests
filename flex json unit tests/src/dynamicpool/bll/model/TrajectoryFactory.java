package dynamicpool.bll.model;


public class TrajectoryFactory {
	public static LineTrajectory createLineTrajectory(Point location) {
		return new LineTrajectory(location);
	}
	
	public static SinTrajectory createSinTrajectory(Point location) {
		return new SinTrajectory(location);
	}
	
	public static CycleTrajectory createCycleTrajectory(Point location) {
		return new CycleTrajectory(location);
	}
}
