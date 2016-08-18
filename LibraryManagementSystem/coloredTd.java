package cs320Tag;


	
	import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

	public class coloredTd extends SimpleTagSupport {

	   private Boolean available;
	   private Boolean  overdue;
	   

	    public coloredTd()
	    {
	        
	    }
	    


	    public Boolean getAvailable() {
			return available;
		}



		public void setAvailable(Boolean available) {
			this.available = available;
		}



		


		public Boolean getOverdue() {
			return overdue;
		}



		public void setOverdue(Boolean overdue) {
			this.overdue = overdue;
		}



		@Override
	    public void doTag() throws JspException, IOException
	    {
			PageContext pageContext = (PageContext) getJspContext();
			JspWriter out = pageContext.getOut();
			
			
			if(available)
			{
				out.print("<td style='background-color: rgb(0,255,0);'>YES</td>");
			}
			else if(overdue)
			{
				out.print("<td style='background-color: rgb(255,0,0);'>No</td>");
			}
			else
			{
				out.print("<td style='background-color: rgb(255,255,0);'>No</td>");
			}
					
			
	    
					
			
			
		
		
		

	}

	}

