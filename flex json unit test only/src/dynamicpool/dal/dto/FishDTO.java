package dynamicpool.dal.dto;

import java.util.UUID;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishState;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.NoneTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Trajectory;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class FishDTO {
	private String fishId = UUID.randomUUID().toString();
	private FishType fishType = FishType.FISH1;
	private Boundary boundary = new Boundary();
	private FishState fishState = FishState.INSIDE;
	private ETrajectoryType trajectoryType = ETrajectoryType.NONE;
	private String trajectoryString = "";

	public FishDTO() {

	}

	public String getFishId() {
		return fishId;
	}

	public void setFishId(String fishId) {
		this.fishId = fishId;
	}

	public FishType getFishType() {
		return fishType;
	}

	public void setFishType(FishType fishType) {
		this.fishType = fishType;
	}

	public Boundary getBoundary() {
		return boundary;
	}

	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	public FishState getFishState() {
		return fishState;
	}

	public void setFishState(FishState fishState) {
		this.fishState = fishState;
	}

	public ETrajectoryType getTrajectoryType() {
		return trajectoryType;
	}

	public void setTrajectoryType(ETrajectoryType trajectoryType) {
		this.trajectoryType = trajectoryType;
	}

	public String getTrajectoryString() {
		return trajectoryString;
	}

	public void setTrajectoryString(String trajectoryString) {
		this.trajectoryString = trajectoryString;
	}

	public boolean equals(FishDTO f) {
		return this.fishId.equals(f.fishId) && this.fishType == f.fishType
				&& this.fishState == f.fishState
				&& this.boundary.equals(f.boundary)
				&& this.trajectoryType == f.trajectoryType
				&& this.trajectoryString.equals(f.trajectoryString);
	}

	public static FishDTO fromFish(Fish fish) {
		FishDTO dto = new FishDTO();
		dto.setFishId(fish.getFishId());
		dto.setFishType(fish.getFishType());
		dto.setFishState(fish.getFishState());
		dto.setBoundary(fish.getBoundary());
		Trajectory trajectory = fish.getTrajectory();
		dto.setTrajectoryType(trajectory.getTrajectoryType());
		String s = new JSONSerializer().serialize(trajectory);
		dto.setTrajectoryString(s);
		return dto;
	}

	public static Fish toFish(FishDTO fishDTO) {
		Fish fish = new Fish();
		fish.setFishId(fishDTO.getFishId());
		fish.setFishType(fishDTO.getFishType());
		fish.setFishState(fishDTO.getFishState());
		fish.setBoundary(fishDTO.getBoundary());
		Trajectory trajectory = new NoneTrajectory();
		switch (fishDTO.getTrajectoryType()) {
		case LINE:
			trajectory = new JSONDeserializer<LineTrajectory>().deserialize(fishDTO.getTrajectoryString());
			break;
		case CYCLE:
			trajectory = new JSONDeserializer<CycleTrajectory>()
					.deserialize(fishDTO.getTrajectoryString());
			break;
		case SIN:
			trajectory = new JSONDeserializer<SinTrajectory>()
					.deserialize(fishDTO.getTrajectoryString());
			break;
		default:
			break;
		}
		fish.setTrajectory(trajectory);
		return fish;
	}
}
