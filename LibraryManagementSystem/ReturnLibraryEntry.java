

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnLibraryEntry
 */
@WebServlet("/ReturnLibraryEntry")
public class ReturnLibraryEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnLibraryEntry() {
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
		Integer subid=Integer.parseInt(request.getParameter("subid"));
		Integer libid=Integer.parseInt(request.getParameter("libid"));
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date=new Date();
		String datereturned=dateFormat.format(date);
		
		Connection c=null;
		
		try
		{
			 String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
	         String username = "cs320stu54";
	         String password = "pMVb.2us";
			
			
			String sql = "update studententry set datereturned=? where id = ?";
			c=DriverManager.getConnection(url,username,password);
			
			PreparedStatement pstmt = c.prepareStatement( sql );
	        pstmt.setString( 1, datereturned );
            pstmt.setInt( 2, subid );
            pstmt.executeUpdate();
            
            String sql1 = "update libraryentry set available=? where id = ?";
            PreparedStatement pstmt1 = c.prepareStatement(sql1);
            pstmt1.setBoolean(1,true);
            pstmt1.setInt(2, libid);
            pstmt1.executeUpdate();
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
