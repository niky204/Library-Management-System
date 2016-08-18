

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class DispalyLibraryLog
 */
@WebServlet("/DispalyLibraryLog")
public class DispalyLibraryLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispalyLibraryLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        if(session.getAttribute("user")==null)
        {
        	 request.setAttribute("login",session);
        }
        else
        {
        	request.setAttribute("logout", session);
        }
	
		
		List<LibraryEntry> libentry = new ArrayList<LibraryEntry>();
		List<LibraryEntry> sentry=new ArrayList<LibraryEntry>();
		Connection c=null;
		
		try
		{
			String url="jdbc:mysql://cs3.calstatela.edu/cs320stu54";
			String username="cs320stu54";
			String password= "pMVb.2us";
			
			c=DriverManager.getConnection(url,username,password);
			Statement stmt=c.createStatement();
			
			ResultSet rs=stmt.executeQuery("select lb.id, tl.type, lb.name, lb.additionalinfo, lb.available,lb.overdue,lb.noofcopies from libraryentry as lb inner join typelist as tl on lb.type = tl.id order by lb.id");
			
			while(rs.next())
			{
				LibraryEntry entry=new LibraryEntry(rs.getInt("id"),rs.getString("type"),rs.getString("name"),rs.getString("additionalinfo"),rs.getBoolean("available"),rs.getBoolean("overdue"),rs.getInt("noofcopies"));
								
				libentry.add(entry);
			}
			
			ResultSet rs1=stmt.executeQuery("select * from studententry");
			
			while(rs1.next())
			{
				LibraryEntry subentry=new LibraryEntry(rs1.getInt("sid"),rs1.getInt("id"),rs1.getString("cin"),rs1.getString("nameofstudent"),rs1.getString("dateborrowed"),rs1.getString("duedate"),rs1.getString("datereturned"));
				sentry.add(subentry);
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
       
            request.setAttribute("sentry", sentry);
            request.setAttribute( "libentry", libentry );
            request.getRequestDispatcher( "/WEB-INF/DisplayLibraryLog.jsp" ).forward(request, response );
        
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
