package am.livestock.vo;

public class Livestock {
	private int memberNo;
	private int tankNo;
	private int livestockNo;
	private String livestockSpecies;
	private String livestockName;
	private int livestockPrice;
	private String livestockGender;
	private String dateOfPurchase;
	private String lifeOrDie;
	
	public Livestock() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Livestock(int memberNo, int tankNo, String livestockSpecies, String livestockName,
			int livestockPrice, String livestockGender, String dateOfPurchase) {
		super();
		this.memberNo = memberNo;
		this.tankNo = tankNo;
		this.livestockSpecies = livestockSpecies;
		this.livestockName = livestockName;
		this.livestockPrice = livestockPrice;
		this.livestockGender = livestockGender;
		this.dateOfPurchase = dateOfPurchase;
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
	public int getLivestockNo() {
		return livestockNo;
	}
	public void setLivestockNo(int livestockNo) {
		this.livestockNo = livestockNo;
	}
	public String getLivestockSpecies() {
		return livestockSpecies;
	}
	public void setLivestockSpecies(String livestockSpecies) {
		this.livestockSpecies = livestockSpecies;
	}
	public String getLivestockName() {
		return livestockName;
	}
	public void setLivestockName(String livestockName) {
		this.livestockName = livestockName;
	}
	public int getLivestockPrice() {
		return livestockPrice;
	}
	public void setLivestockPrice(int livestockPrice) {
		this.livestockPrice = livestockPrice;
	}
	public String getLivestockGender() {
		return livestockGender;
	}
	public void setLivestockGender(String livestockGender) {
		this.livestockGender = livestockGender;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	public String getLifeOrDie() {
		return lifeOrDie;
	}

	public void setLifeOrDie(String lifeOrDie) {
		this.lifeOrDie = lifeOrDie;
	}




	@Override
	public String toString() {
		return "Livestock [memberNo=" + memberNo + ", tankNo=" + tankNo + ", livestockNo=" + livestockNo
				+ ", livestockSpecies=" + livestockSpecies + ", livestockName=" + livestockName + ", livestockPrice="
				+ livestockPrice + ", livestockGender=" + livestockGender + ", dateOfPurchase=" + dateOfPurchase + "]";
	}
	
	
	
	
	

}
