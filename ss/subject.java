package ss;

public class subject {
	private String subjectID;
	private String subjectName;
	private String teacherID;
	private int maxCapacity;
	private int capacity;
	
	public String getTeacherID(){
		return teacherID;
	}
	
	public void setTeacherID(String teacherID1){
		teacherID=teacherID1;
	}
	
	public String getSubjectName(){
		return subjectName;
	}
	
	public void setSubjectName(String subjectName1){
		subjectName=subjectName1;
	}
	
	public String getSubjectID(){
		return subjectID;
	}
	
	public void setSubjectID(String subjectID1){
		subjectID=subjectID1;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public void setCapacity(int capacity1){
		capacity=capacity1;
	}
	
	public int getMaxCapacity(){
		return maxCapacity;
	}
	
	public void setMaxCapacity(int maxCapacity1){
		maxCapacity=maxCapacity1;
	}
}
