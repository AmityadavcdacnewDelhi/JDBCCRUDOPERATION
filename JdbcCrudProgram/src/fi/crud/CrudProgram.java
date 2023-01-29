package fi.crud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class CrudProgram {
 public static void main (String[] args)
 {
	 
	 Scanner sc= new Scanner(System.in);
	 
	 try {
			Properties props = new Properties();
			props.load(new FileInputStream("database.properties"));
			String driverClassName = props.getProperty("db.driverClass");
			String url = props.getProperty("db.url");
			String userName = props.getProperty("db.username");
			String password = props.getProperty("db.password");

		  Class.forName(driverClassName);
		
		try(Connection connection = DriverManager.getConnection(url,userName,password))						
		{
			do {
			 System.out.println("Crud Operation Using JDBC Java");
			 System.out.println("Enter 1 for Register");
			 System.out.println("Enter 2 for Delete");
			 System.out.println("Enter 3 for Change_Name");
			 System.out.println("Enter 4 for Update all detail");
			 System.out.println("Enter 5 for Show All Record");
			 System.out.println("Enter 0 for Exit");
			 int choice = sc.nextInt();
			if(choice==1)
			{
			String sql = "INSERT INTO  emp_detail (id, name, salary, city) VALUES (?, ?, ?, ?)";
			  PreparedStatement ps = connection.prepareStatement(sql);
			    			     
			      System.out.println("Enter the Employee ID");
			      int empId= sc.nextInt();
			      System.out.println("Enter the Employee Name");
			      String name= sc.next();
			      System.out.println("Enter the salary of Employee");
			      String salary= sc.next();
			      System.out.println("Enter the Employee city");
			      String city= sc.next();
			      ps.setInt(1,empId);
			      ps.setString(2,name);
			      ps.setString(3,salary);
			      ps.setString(4,city);
			      int rowsInserted = ps.executeUpdate();
			      System.out.println("Record inserted Successfully");

			     
			}
			else if(choice==2)	
		{
				
				PreparedStatement ps=connection.prepareStatement("delete from emp_detail where id=?");
				
				System.out.println("Enter the id which you want to delete");
				int id= sc.nextInt();
				ps.setInt(1,id);   
				int i=ps.executeUpdate();  
				System.out.println(i+" records deleted");
				
			     
			     
				
			}
			else if(choice==3)
			{
				PreparedStatement ps=connection.prepareStatement("update emp_detail set name=? where Id=?");
				
				System.out.println("Enter the Employee_Id");
				int id= sc.nextInt();
				System.out.println("Enter the updated Name");
				String name= sc.next();
		        ps.setString(1, name);
		        ps.setInt(2,id);
		        ps.executeUpdate();
		        System.out.println("Name Successfully Updated");
		       
			}
			else if(choice==4)
			{
				PreparedStatement ps=connection.prepareStatement("update emp_detail set name=?,salary=?,city=? where Id=?");
			
				System.out.println("Enter the Employee_Id");
				int id= sc.nextInt();
				System.out.println("Enter the updated Name");
				String name= sc.next();
				System.out.println("Enter the updated Salary");
				String salary= sc.next();
				System.out.println("Enter the updated City");
				String city= sc.next();
		        ps.setString(1, name);
		        ps.setString(2,salary);
		        ps.setString(3,city);
		        ps.setInt(4,id);
		        ps.executeUpdate();
		        System.out.println("All Detail Successfully Updated");
		       
			    
			}
			
			else if(choice==5)
			{
			  System.out.println("Show all Record");
			  String sql= "select * from emp_detail";
			  PreparedStatement ps=connection.prepareStatement(sql);
			  ResultSet result = ps.executeQuery();
			  while(result.next())
					{
						System.out.print(result.getInt(1) + "   ");
						System.out.print(result.getString(2)+ "   ");
						System.out.print(result.getString(3)+  "   ");
						System.out.println(result.getString(4)+ "   ");
						
						
					}
			}
			else if(choice==0)
			{
				System.exit(0);
			}
			else
			{
				System.out.println("Kindly enter valid input to see Magic");
				System.out.println("DO you want to continue Crud_Operation   Yes|NO");
			      String choice1 = sc.next();
			      if(choice1.equalsIgnoreCase("No"))
			       {
			         break;
			       }
			}
			
			 System.out.println("DO you want to continue Crud_Operation Yes|NO");
			 String choice1 = sc.next();
			 {
				 if(choice1.equalsIgnoreCase("No"))
			       {
			         break;
			       }	 
			 }
			}
			while(!false);
		
				
		}catch(SQLException sql)
		{
			sql.printStackTrace();
		 }
          }catch(ClassNotFoundException e)
         {
           e.printStackTrace();
              }catch(FileNotFoundException e)
             {
          e.printStackTrace();
          }catch(IOException e)
           {
         e.printStackTrace();
      }		
		
 }
}