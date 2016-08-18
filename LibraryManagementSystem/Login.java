

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	 List<User> users=new ArrayList<User>();
	       Connection c=null;
			
			try
			{
				String url="jdbc:mysql://localhost/cs320stu54";
				String username="root";
				String password= "root";
				
				c=DriverManager.getConnection(url,username,password);
				Statement stmt=c.createStatement();
				
				ResultSet rs=stmt.executeQuery("select * from users");
				
				while(rs.next())
				{
					User user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
					users.add(user);
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
	       
	            request.getSession().setAttribute("users", users);*/
	            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	        
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost( HttpServletRequest request,
		        HttpServletResponse response ) throws ServletException, IOException
		    {
		      
		  			String uname=request.getParameter("username");
		  			String pword=request.getParameter("password");
		  			Connection c=null;
		 			
		 			try
		 			{
		 				String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu54";
		 	            String username = "cs320stu54";
		 	            String password = "pMVb.2us";
		 				
		 				String sql="select * from users where username=? and password=?";
		 				c=DriverManager.getConnection(url,username,password);
		 				PreparedStatement pstmt = c.prepareStatement( sql ); 
		 				pstmt.setString(1,uname);
		 				pstmt.setString(2,pword);
		 				
		 				ResultSet rs=pstmt.executeQuery();
		 				
		  			if(rs.next())
		  			{
		  				request.getSession().setAttribute("user", rs.getString("username"));
		  				response.sendRedirect("DispalyLibraryLog");
		  			}
		  			else{
		  				response.sendRedirect("Login");
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
			        }

}
}