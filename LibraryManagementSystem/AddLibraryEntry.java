

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class AddLibraryEntry
 */
@WebServlet("/AddLibraryEntry")
public class AddLibraryEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLibraryEntry() {
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
		
		HttpSession session = request.getSession();

		List<String> typelist=new ArrayList<String>();
        if(session.getAttribute("user")==null)
		 {
			 response.sendRedirect("Login");
		 }
		 else
		 {
			 request.setAttribute("logout",session);
			 
			 Connection c=null;
				
				try
				{
					String url="jdbc:mysql://cs3.calstatela.edu/cs320stu54";
					String username="cs320stu54";
					String password= "pMVb.2us";
					
					c=DriverManager.getConnection(url,username,password);
					Statement stmt=c.createStatement();
					
					ResultSet rs=stmt.executeQuery("select * from typelist group by type");
					
					while(rs.next())
					{
						//LibraryEntry entry=new LibraryEntry(rs.getInt("tid"),rs.getString("type"));
										
						typelist.add(rs.getString("type"));
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
		       
		    request.setAttribute("typelist", typelist);
			 request.getRequestDispatcher("/WEB-INF/AddLibraryEntry.jsp").forward(request, response);
		 }
		 }
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String additionalinfo=request.getParameter("additionalinfo");
		Integer noofcopies=Integer.valueOf(request.getParameter("copies"));
		Integer tid=0;
		
		Connection c=null;
		for(int i=0;i<noofcopies;i++)
		{
		try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
            String username = "cs320stu54";
            String password = "pMVb.2us";
            
            
            String sql="select id from typelist where type=?";
            String sql1 = "insert into libraryentry (type,name,additionalinfo,available,overdue,noofcopies) values (?,?,?,?,?,?)";
            c = DriverManager.getConnection( url, username, password );
          
			PreparedStatement pstmt1=c.prepareStatement(sql);
			pstmt1.setString(1,type);
			ResultSet rs=pstmt1.executeQuery();
			if(rs.next())
			{	
			tid=rs.getInt("id");
			}
			
			
			PreparedStatement pstmt = c.prepareStatement( sql1 );
            pstmt.setInt( 1,tid );
            pstmt.setString( 2, name );
            pstmt.setString(3, additionalinfo);
            pstmt.setBoolean(4, true);
            pstmt.setBoolean(5,false);
            pstmt.setInt(6,noofcopies);
            pstmt.executeUpdate();
            
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
        }
		
		}
		response.sendRedirect("DispalyLibraryLog");
	}
}