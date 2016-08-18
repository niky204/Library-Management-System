

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOutLibraryEntry
 */
@WebServlet("/CheckOutLibraryEntry")
public class CheckOutLibraryEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutLibraryEntry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("id"));
		List<LibraryEntry>	entry=new ArrayList<LibraryEntry>();
		String dateborrowed = null;
		Connection c=null;
		
		try
		{
			String url="jdbc:mysql://cs3.calstatela.edu/cs320stu54";
			String username="cs320stu54";
			String password= "pMVb.2us";
			
			c=DriverManager.getConnection(url,username,password);
			Statement stmt=c.createStatement();
			ResultSet rs1=stmt.executeQuery("select * from libraryentry l where l.id="+id);
			while(rs1.next())
			{
				LibraryEntry lentry=new LibraryEntry(rs1.getInt("id"),rs1.getString("type"),rs1.getString("name"),rs1.getString("additionalinfo"),rs1.getBoolean("available"),rs1.getBoolean("overdue"),rs1.getInt("noofcopies"));
				entry.add(lentry);
			}
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date=new Date();
			 dateborrowed=dateFormat.format(date);
			
			
		}	
			catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
		finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
       
            request.setAttribute("entry", entry);
            request.setAttribute("dateborrowed", dateborrowed);
            
            request.getRequestDispatcher("/WEB-INF/CheckOutLibraryEntry.jsp").forward(request, response);
        
        }
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c=null;
		
		 Integer index=Integer.valueOf(request.getParameter("hidden"));
		 String cin=request.getParameter("cin");
		 String nameofstudent=request.getParameter("nameofstudent");
		 String dateborrowed =request.getParameter("dateborrowed");
		 String duedate = request.getParameter("duedate");
		 String datereturned=null;
		 
		 try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
	            String username = "cs320stu54";
	            String password = "pMVb.2us";

	            String sql = "insert into studententry (sid,cin,nameofstudent,dateborrowed,duedate,datereturned) values (?,?,?,?,?,?)";
	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setInt(1,index);
	            pstmt.setString( 2, cin );
	            pstmt.setString(3, nameofstudent);
	            pstmt.setString(4, dateborrowed);
	            pstmt.setString(5, duedate);
	            pstmt.setString(6,datereturned);
	            pstmt.executeUpdate();
	            
	            c.close();
	            
	            
	            
	        }
	        catch(SQLException e )
	        {
	            throw new ServletException( e );
	        }
		 
		 try
	        {
			 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
	            String username = "cs320stu54";
	            String password = "pMVb.2us";
	            
	            String sql = "update libraryentry set available=?,overdue=? where id=?";
	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            
	            
	            if(duedate.equals(""))
	            {	
	           
	            pstmt.setBoolean(1, false);
	            pstmt.setBoolean(2, false);
	            pstmt.setInt(3,index);
	            pstmt.executeUpdate();
	            }
	            else
	            {
	            	
		          pstmt.setBoolean(1, false);
		          pstmt.setBoolean(2, true);
		          pstmt.setInt(3,index);
		          pstmt.executeUpdate();
	            }
	            c.close();
	            
	            
	            
	        }
	        catch(SQLException e )
	        {
	            throw new ServletException( e );
	        }
	    
		 finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	       
	            
	           response.sendRedirect("DispalyLibraryLog");
	            
	            
	        
	        }
			
			
	
		
		 
	}	

}
