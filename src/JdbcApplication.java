import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class JdbcApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ahire","ahire");
			System.out.println("Connection to DB"+con);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner sc=new Scanner(System.in);
		do
		{
			
		 PreparedStatement pstate=null;
		 Statement p=null;
			System.out.println("1-insert\n2-delete\n3-update\n4-search\n5-display record");
			int a=sc.nextInt();
			if(a==1)
			{
				try {
					System.out.println("Enter student id\n Student Name\nStudent percentage");
					int studid=sc.nextInt();
					String studname=sc.next();
					float studper=sc.nextFloat();
					pstate=con.prepareStatement("Insert into student values(?,?,?)");
					pstate.setInt(1, studid);
					pstate.setString(2, studname);
					pstate.setFloat(3, studper);
					int i=pstate.executeUpdate();
					if(i>0)
					{
						System.out.println("record inserted");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
			else if(a==2)
			{
				/*System.out.println("Enter Employee id to delete record");
				int studid=sc.nextInt();
				try {
					pstate=con.prepareStatement("delete from student where studid=?");
					pstate.setInt(1,studid);
					
			int i=pstate.executeUpdate();
			if(i>0)
			{
				System.out.println("record deleted");
			}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				try {
					System.out.println("Enter two number fro addition");
					int c=sc.nextInt();
					int d=sc.nextInt();
					CallableStatement cst=con.prepareCall("{call procedure1(?,?,?)}");
					cst.setInt(1, c);
					cst.setInt(2, d);
					cst.registerOutParameter(3, Types.INTEGER);
					cst.executeUpdate();
					int add=cst.getInt(3);
					System.out.println(add);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(a==3)
				
			{
				System.out.println("Enter employee id and percentage to update");
				int studid=sc.nextInt();
				float studper=sc.nextFloat();
				try {
					pstate=con.prepareStatement("update student set studper=? where studid=?");
					pstate.setFloat(1,studper);
					pstate.setInt(2,studid);
					int i=pstate.executeUpdate();
					if(i>0)
					{
						System.out.println("record updated");
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(a==4){
				
				System.out.println("Enter employee id to search record");
				int studid=sc.nextInt();
				try {
					//query is parametrized(parameter) that timr we can used prepareStatement method of connection class and object of PreaparedStatement class
					pstate=con.prepareStatement("select * from student where studid=?");
					pstate.setInt(1,studid);
					ResultSet c=pstate.executeQuery();
					if(c.next())
					{
						System.out.println(c.getInt(1)+"\t"+c.getString(2)+"\t"+c.getFloat(3));
					}
					else
					{
						System.out.println("record not found");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			else if(a==5)
			{
				//this is static query then we can used createStatement()method of connection class and create object of Statement class
				String studdata="select * from student";
				try {
					p=con.createStatement();
					//data pointer first point to first row of database means all column name
					ResultSet data=p.executeQuery(studdata);
					//ResultSetMetaData class used for print column name
					ResultSetMetaData rsmd=data.getMetaData();
					for(int i=1;i<=rsmd.getColumnCount();i++)
					{
					System.out.print(rsmd.getColumnName(i)+"\t");	
					}
					System.out.println("\n");
					while(data.next())
					{
						System.out.println(data.getInt(1)+"\t"+data.getString(2)+"\t\t"+data.getFloat(3));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			System.out.println("Do you want to continue press yes");
			
		}while(sc.next().equals("yes"));
		
	}

	private static Statement createStatement(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
