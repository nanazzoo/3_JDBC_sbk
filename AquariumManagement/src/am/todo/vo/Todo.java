package am.todo.vo;

public class Todo {
	private int memberNo;
	private int tankNo;
	private int todoNo;
	private String todoContent;
	private String regDate;
	private String todoTerm;
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
	public int getTodoNo() {
		return todoNo;
	}
	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getTodoTerm() {
		return todoTerm;
	}
	public void setTodoTerm(String todoTerm) {
		this.todoTerm = todoTerm;
	}
	
	

}
