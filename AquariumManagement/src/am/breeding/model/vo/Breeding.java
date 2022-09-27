package am.breeding.model.vo;

import java.util.List;

public class Breeding {
	private int memberNo;
	private int breedingNo;
	private String breedSpecies1;
	private int fromTankNo1;
	private String breedSpecies2;
	private int fromTankNo2;
	private String startDate;
	private String endFl;
	
	private List<Event> eventList;
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBreedingNo() {
		return breedingNo;
	}
	public void setBreedingNo(int breedingNo) {
		this.breedingNo = breedingNo;
	}
	
	public String getBreedSpecies1() {
		return breedSpecies1;
	}
	public void setBreedSpecies1(String breedSpecies1) {
		this.breedSpecies1 = breedSpecies1;
	}
	public int getFromTankNo1() {
		return fromTankNo1;
	}
	public void setFromTankNo1(int fromTankNo1) {
		this.fromTankNo1 = fromTankNo1;
	}
	public String getBreedSpecies2() {
		return breedSpecies2;
	}
	public void setBreedSpecies2(String breedSpecies2) {
		this.breedSpecies2 = breedSpecies2;
	}
	public int getFromTankNo2() {
		return fromTankNo2;
	}
	public void setFromTankNo2(int fromTankNo2) {
		this.fromTankNo2 = fromTankNo2;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	public String getEndFl() {
		return endFl;
	}
	public void setEndFl(String endFl) {
		this.endFl = endFl;
	}
	
	
	
	

}
