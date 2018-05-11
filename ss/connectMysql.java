package ss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.ResultSet;

public class connectMysql {
	private Connection conn;
	private Statement stmt;
	
	public connectMysql(){
		//String dbUrl = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DB"));
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//System.out.println("f*ck");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/subjectsystem?useSSL=false&characterEncoding=UTF-8", "root", "");
			//conn = DriverManager.getConnection("jdbc:mysql:// w.rdc.sae.sina.com.cn:3307/app_hitwd", "1jny5oxj0n", "zlxiwi24kk5m5zkjzh23l2h25zhxxihhw40l2k00");
			//conn = DriverManager.getConnection(dbUrl, username, password);
			System.out.println("Mysql succeed!");
			//System.out.println("f*ck");
			stmt = conn.createStatement();
		}
		catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
		}
	}
	
	//查询所有课程
	public LinkedList<subject> searchSub(){
		String sql = "select * from subject";
	    LinkedList<subject> list = new LinkedList<subject>();
	    try{
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				subject newSub = new subject();
				if(null != rs.getString(1))
					newSub.setSubjectID(new String(rs.getString(1).trim()));
				if(null != rs.getString(2))
					newSub.setSubjectName(new String(rs.getString(2).trim()));
				if(null != rs.getString(3))
					newSub.setTeacherID(new String(rs.getString(3).trim()));
				if(null != rs.getString(4))
					newSub.setCapacity(Integer.parseInt(new String(rs.getString(4).trim())));
				if(null != rs.getString(5))
					newSub.setMaxCapacity(Integer.parseInt(new String(rs.getString(5).trim())));
				list.add(newSub);
			}
			return list;
	    }
	    catch(Exception ex){
	    	System.out.println(ex);
	    	System.exit(0);
	    }
	    return null;
	}
	
	//查询某个科目
	public subject searchSub(String subjectID){
		String sql = "select * from subject where subjectID='" + subjectID + "'";
		subject newSub = new subject();
	    try{
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				if(null != rs.getString(1))
					newSub.setSubjectID(new String(rs.getString(1).trim()));
				if(null != rs.getString(2))
					newSub.setSubjectName(new String(rs.getString(2).trim()));
				if(null != rs.getString(3))
					newSub.setTeacherID(new String(rs.getString(3).trim()));
				if(null != rs.getString(4))
					newSub.setCapacity(Integer.parseInt(new String(rs.getString(4).trim())));
				if(null != rs.getString(5))
					newSub.setMaxCapacity(Integer.parseInt(new String(rs.getString(5).trim())));
			}
			return newSub;
	    }
	    catch(Exception ex){
	    	System.out.println(ex);
	    	System.exit(0);
	    }
	    return null;
	}
	
	//学生登录
	public student studentLogin(student newStudent)
	{
		if(newStudent.getStudentID() == "") return null;
		String sql = "select * from student where studentID='" + newStudent.getStudentID() + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);//find if the book exits in the database
			if(rs.next()) 
			{
				if(newStudent.getPassword().equals(rs.getString(7)))
				{
					student a=new student();	
					if(null != rs.getString(1))
						a.setStudentID(new String(rs.getString(1).trim()));
					if(null != rs.getString(2))
						a.setStudentName(new String(rs.getString(2).trim()));
					if(null != rs.getString(3))
						a.setBirthday(new String(rs.getString(3).trim()));
					if(null != rs.getString(4))
						a.setSex(new String(rs.getString(4).trim()));
					if(null != rs.getString(5))
						a.setPhoneNum(new String(rs.getString(5).trim()));
					if(null != rs.getString(6))
						a.setFieldID(new String(rs.getString(6).trim()));
					if(null != rs.getString(7))
						a.setPassword(new String(rs.getString(7).trim()));
					return a;
				}
			}
			return null;
		}
		catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
		}
		return null;
	}
	
	//老师登录
	public teacher TeacherLogin(teacher newTeacher)
	{
		if(newTeacher.getTeacherID() == "") return null;
		String sql = "select * from teacher where teacherID='" + newTeacher.getTeacherID() + "'";
		try{
			ResultSet rs = stmt.executeQuery(sql);//find if the book exits in the database
			if(rs.next()) 
			{
				if(newTeacher.getPassword().equals(rs.getString(7)))
				{
					teacher a=new teacher();	
					if(null != rs.getString(1))
						a.setTeacherID(new String(rs.getString(1).trim()));
					if(null != rs.getString(2))
						a.setTeacherName(new String(rs.getString(2).trim()));
					if(null != rs.getString(3))
						a.setBirthday(new String(rs.getString(3).trim()));
					if(null != rs.getString(4))
						a.setSex(new String(rs.getString(4).trim()));
					if(null != rs.getString(5))
						a.setPhoneNum(new String(rs.getString(5).trim()));
					if(null != rs.getString(6))
						a.setFieldID(new String(rs.getString(6).trim()));
					if(null != rs.getString(7))
						a.setPassword(new String(rs.getString(7).trim()));
					return a;
				}
			}
			return null;
		}
		catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
		}
		return null;
	}
	
	//选课
	public boolean chooseSubject(student newStudent,String subjectID) {
		subject newSubject=searchSub(subjectID);
		if(newSubject.getCapacity()==0 || newSubject==null)
			return false;
		String sql = "insert into choose (subjectID, studentID,grade)"
				+ "values ('" + subjectID + "','" + newStudent.getStudentID() + 
				"','" + "0" +  "')";
		try{
			stmt.executeUpdate(sql);//insert the book into database
		}
		catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
			return false;
		}
		int c=newSubject.getCapacity()-1;
		sql = "update subject set capacity = "+c+" where subjectID ='"+subjectID+"'" ;
		try{
			stmt.executeUpdate(sql);//insert the book into database
		}
		catch(Exception ex){
			System.out.println(ex);
			System.exit(0);
			return false;
		}
		return true;
	}
	
	//取消选课
	public boolean delSubject(String studentID,String subjectID) {
		String sql = "select * from choose where subjectID='" + subjectID + "' and studentID='"+studentID+"'" ;
		//subject newSub = new subject();
	    try{
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				return delChoose(studentID,subjectID);
			}
	    }
	    catch(Exception ex){
	    	System.out.println("sdasda");
	    	System.out.println(ex);
	    	System.exit(0);
	    }
		return false;
	}
	
	//删除选课的某项记录并把课程容量+1
	public boolean delChoose(String studentID,String subjectID) {
		String sql = "delete from choose where subjectID='" + subjectID + "' and studentID='"+studentID+"'";
		try{
			stmt.executeUpdate(sql);
			//System.out.println("2");
			subject a = searchSub(subjectID);
			int c=a.getCapacity()+1;
			sql = "update subject set capacity = "+c+" where subjectID ='"+subjectID+"'" ;
			try{
				//System.out.println("3");
				stmt.executeUpdate(sql);//insert the book into database
			}
			catch(Exception ex){
				System.out.println(ex);
				System.exit(0);
				return false;
			}
			return true;
		}
		catch(Exception ex){
			//System.out.println("sdasda");
	    	System.out.println(ex);
	    	System.exit(0);
	    }
		return false;
	}
	
	//学生查询选课
	public LinkedList<subject> stuSearchChoose(String studentID)
	{
		String sql = "select * from choose where studentID='" + studentID + "'";
		LinkedList<String> a=new LinkedList<String>();
	    try{
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				if(null != rs.getString(1))
					a.add(new String(rs.getString(1).trim()));
			}
	    }
	    catch(Exception ex){
	    	System.out.println(ex);
	    	System.exit(0);
	    	return null;
	    }
	    LinkedList<subject> b = new LinkedList<subject>();
	    subject b1;
	    for(String a1 : a)
	   	{
	    	b1=searchSub(a1);
	    	b.add(b1);
	   	}
	    return b;
	}
	
	//老师查看自己要教授什么课
	public LinkedList<subject> teaSearchSub(String teacherID){
		String sql = "select * from subject where teacherID='" + teacherID + "'";
		LinkedList<subject> list = new LinkedList<subject>();
	    try{
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				subject newSub = new subject();
				if(null != rs.getString(1))
					newSub.setSubjectID(new String(rs.getString(1).trim()));
				if(null != rs.getString(2))
					newSub.setSubjectName(new String(rs.getString(2).trim()));
				if(null != rs.getString(3))
					newSub.setTeacherID(new String(rs.getString(3).trim()));
				if(null != rs.getString(4))
					newSub.setCapacity(Integer.parseInt(new String(rs.getString(4).trim())));
				if(null != rs.getString(5))
					newSub.setMaxCapacity(Integer.parseInt(new String(rs.getString(5).trim())));
				list.add(newSub);
			}
			return list;
	    }
	    catch(Exception ex){
	    	System.out.println(ex);
	    	System.exit(0);
	    }
	    return null;
	}

	/*public LinkedList<student> teaSearchStudents(String teacherID)
	{
		LinkedList<subject> a =teaSearchSub(teacherID);
		for(subject b : a)
		{
			
		}
	}
	
	//查询某个课谁选了
	public LinkedList<student> searchSubStu(String subjectID)
	{
		
	}*/
	
}

