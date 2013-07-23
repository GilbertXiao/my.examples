package tagdemo;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ColorTag extends BodyTagSupport
{  
  String myColor = "red";
 
  public void setColor(String col) 
	{ 
		myColor = col;
	}

  public int doStartTag() 
		throws javax.servlet.jsp.JspException 
	{
      return BodyTag.EVAL_BODY_BUFFERED;
  }
  
  public int doAfterBody() throws javax.servlet.jsp.JspException
	{
    //Get the processed body content of the tag
    String body = getBodyContent().getString();
         
    //Write output to the parent's out stream. 
    try 
		{
			String str = "<font color='" + myColor 
										+ "'>" + body + "</font>";

			getPreviousOut().print(str);
    } 
		catch (IOException e) {}

    return SKIP_BODY;
  }  
}


