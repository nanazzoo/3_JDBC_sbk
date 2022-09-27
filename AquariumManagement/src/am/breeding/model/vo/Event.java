package am.breeding.model.vo;


//댓글 1개에 대한 값을 저장하는 VO
public class Event {
	private int memberNo;
	private int breedingNo;
	private int eventNo;
	private String eventContent;
	private String regDate;
	
	
	
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
	
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
		
	
	
	
	
}
