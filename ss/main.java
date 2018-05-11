package ss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.ResultSet;

public class main {
	 public static void main(String[] args){
		 connectMysql a=new connectMysql();
		 LinkedList<subject> b=a.searchSub();
		 student c=new student();
		 c.setStudentID("1153710219");
		 c.setPassword("123456");
		 student p=a.studentLogin(c);
		 
		 //a.chooseSubject(p,"00001");
		 b = a.teaSearchSub("1");
		 for(subject d : b)
		 {
			 System.out.println(d.getSubjectName());
		 }
		
	 }
}
