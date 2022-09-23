package am.tank.vo;

public class Tank {
	private int memberNo;
	private int tankNo;
	private String tankName;
	private String freshSalt;
	private String tankSize;
	private String tankLight;
	private String tankFilter;
	private String tankAddictive;
	private String tankSubstrate;
	private int tankMate;
	
	public Tank() {
		// TODO Auto-generated constructor stub
	}

	public Tank(int memberNo, String tankName, String freshSalt, String tankSize, String tankLight, String tankFilter,
			String tankAddictive, String tankSubstrate) {
		super();
		this.memberNo = memberNo;
		this.tankName = tankName;
		this.freshSalt = freshSalt;
		this.tankSize = tankSize;
		this.tankLight = tankLight;
		this.tankFilter = tankFilter;
		this.tankAddictive = tankAddictive;
		this.tankSubstrate = tankSubstrate;
	}
	
	

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getTankNo() {
		return tankNo;
	}

	public void setTankNo(int tankNo) {
		this.tankNo = tankNo;
	}
		
	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public String getFreshSalt() {
		return freshSalt;
	}

	public void setFreshSalt(String freshSalt) {
		this.freshSalt = freshSalt;
	}

	public String getTankSize() {
		return tankSize;
	}

	public void setTankSize(String tankSize) {
		this.tankSize = tankSize;
	}

	public String getTankLight() {
		return tankLight;
	}

	public void setTankLight(String tankLight) {
		this.tankLight = tankLight;
	}

	public String getTankFilter() {
		return tankFilter;
	}

	public void setTankFilter(String tankFilter) {
		this.tankFilter = tankFilter;
	}

	public String getTankAddictive() {
		return tankAddictive;
	}

	public void setTankAddictive(String tankAddictive) {
		this.tankAddictive = tankAddictive;
	}

	public String getTankSubstrate() {
		return tankSubstrate;
	}

	public void setTankSubstrate(String tankSubstrate) {
		this.tankSubstrate = tankSubstrate;
	}
	

	public int getTankMate() {
		return tankMate;
	}

	public void setTankMate(int tankMate) {
		this.tankMate = tankMate;
	}

	@Override
	public String toString() {
		return "tank [tankNo=" + tankNo + ", freshSalt=" + freshSalt + ", tankSize=" + tankSize + ", tankLight="
				+ tankLight + ", tankFilter=" + tankFilter + ", tankAddictive=" + tankAddictive + ", tankSubstrate="
				+ tankSubstrate + "]";
	}
	
	
	

}
