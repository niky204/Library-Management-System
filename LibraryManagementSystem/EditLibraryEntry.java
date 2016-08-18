

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
 * Servlet implementation class EditLibraryEntry
 */
@WebServlet("/EditLibraryEntry")
public class EditLibraryEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditLibraryEntry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	 private LibraryEntry getEntry( Integer id ) throws ServletException
	    {
	        LibraryEntry entry = null;
	        Connection c = null;
	        try
	        {
	        	 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
		         String username = "cs320stu54";
		         String password = "pMVb.2us";

	          //  String sql = "select lb.id, tl.type, lb.name, lb.additionalinfo, lb.availabel,lb.noofcopies from libraryentry as lb where lb.id=?  inner join typelist tl on lb.type = tl.id";
	            String sql="select lb.id, tl.type, lb.name, lb.additionalinfo, lb.available,lb.overdue,lb.noofcopies from libraryentry as lb inner join typelist tl on lb.type = tl.id where lb.id=?";

	            c = DriverManager.getConnection( url, username, password );
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setInt(1,id);
	            
	           ResultSet rs = pstmt.executeQuery();

	            if( rs.next() )
	                entry = new LibraryEntry( rs.getInt( "id" ),
	                  rs.getString("type")  , rs.getString( "name" ),rs.getString( "additionalinfo" ),rs.getBoolean("available"),rs.getBoolean("overdue"),rs.getInt("noofcopies") );
	            
	           
	            
	            
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
	        }

	        return entry;
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
			 	Integer id = Integer.valueOf( request.getParameter( "id" ) );
			 	LibraryEntry libentry = getEntry( id );
			 	
			 	Connection c=null;
			    try
			    {
			    	 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
			         String username = "cs320stu54";
			         String password = "pMVb.2us";
		            
			         c=DriverManager.getConnection(url,username,password);
			         Statement stmt=c.createStatement();
		            
		            
			         ResultSet rs1=stmt.executeQuery("select * from typelist group by type");
		           
		            while(rs1.next())
		            {
		            	typelist.add(rs1.getString("type"));
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
		        
		            request.setAttribute("typelist",typelist);
		            request.setAttribute("libentry", libentry);
				 	request.getRequestDispatcher("/WEB-INF/EditLibraryEntry.jsp").forward(request,response);
			
		        }
			 
			 	 } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer tid=0;
		Integer id=Integer.valueOf(request.getParameter("id"));
		String type=request.getParameter("type");
	    String name= request.getParameter( "name" ) ;
	    String additionalinfo= request.getParameter("additionalinfo");
	    Connection c=null;
	    try
	    {
	    	 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
	         String username = "cs320stu54";
	         String password = "pMVb.2us";
            
           
	        String sql1="select id from typelist where type=?"; 
	        String sql = "update libraryentry set type = ?, name = ?, additionalinfo = ? where id = ?";
            
            c = DriverManager.getConnection( url, username, password );
            
            PreparedStatement pstmt1 = c.prepareStatement(sql1);
            pstmt1.setString(1,type);
            ResultSet rs2=pstmt1.executeQuery();
            if(rs2.next())
            {	
            tid=rs2.getInt("id");
            }
            
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt( 1, tid );
            pstmt.setString( 2, name );
            pstmt.setString( 3, additionalinfo );
            pstmt.setInt( 4, id );
            
            pstmt.executeUpdate();
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
        }
	    response.sendRedirect("DispalyLibraryLog");
	}

}
