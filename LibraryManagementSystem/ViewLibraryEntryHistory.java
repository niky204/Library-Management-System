

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewLibraryEntryHistory
 */
@WebServlet("/ViewLibraryEntryHistory")
public class ViewLibraryEntryHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewLibraryEntryHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
			
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null)
		 {
			 response.sendRedirect("Login");
		 }
		 else
		 {
		
		
		
		
		Integer id=Integer.parseInt(request.getParameter("id"));
		List<LibraryEntry> subentry = new ArrayList<LibraryEntry>();
		List<LibraryEntry>	entry=new ArrayList<LibraryEntry>();
		Connection c=null;
		
		try
		{
			 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
	         String username = "cs320stu54";
	         String password = "pMVb.2us";
			
			c=DriverManager.getConnection(url,username,password);
			Statement stmt=c.createStatement();
		
			ResultSet rs=stmt.executeQuery("select * from studententry s inner join libraryentry l on s.sid=l.id where l.id="+id);
			
			
			while(rs.next())
			{
				LibraryEntry sentry=new LibraryEntry(rs.getInt("sid"),rs.getInt("id"),rs.getString("cin"),rs.getString("nameofstudent"),rs.getString("dateborrowed"),rs.getString("duedate"),rs.getString("datereturned"));
				subentry.add(sentry);
				Collections.reverse(subentry);
			}
			
			ResultSet rs1=stmt.executeQuery("select * from libraryentry l where l.id="+id);
			while(rs1.next())
			{
				LibraryEntry lentry=new LibraryEntry(rs1.getInt("id"),rs1.getString("type"),rs1.getString("name"),rs1.getString("additionalinfo"),rs1.getBoolean("available"),rs1.getBoolean("overdue"),rs1.getInt("noofcopies"));
				entry.add(lentry);
				
			}
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
	            request.setAttribute( "subentry", subentry );
	            request.getRequestDispatcher( "/WEB-INF/ViewLibraryEntryHistory.jsp" ).forward(request, response );

	    	
	        
	        }
		
		 }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
