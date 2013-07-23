import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import java.util.*;
import java.io.*;

public class JDOMTest {

  public static void showBooks( Element root ) {
       List books = root.getChildren( "book" );
       for( Iterator i=books.iterator(); i.hasNext(); ) {
         Element book = ( Element )i.next();
         System.out.println( "Book: " + book.getAttributeValue( "category" ) + ", " +
                             book.getChildTextTrim( "title" ) + ", " +
                             book.getChildTextTrim( "author" ) + ", " + 
                             book.getChildTextTrim( "price" ) );
       }
  }


  public static void main( String[] args ) throws Exception{ 
       SAXBuilder builder = new SAXBuilder();
       Document doc = builder.build( "book.xml" );
       Element root = doc.getRootElement();
       System.out.println( "Book List Before: " );
       showBooks( root );

       // Add a new book
       Element newBook = new Element( "book" );
       newBook.setAttribute( "category", "fiction" );
       Element newTitle = new Element( "title" );
       newTitle.addContent( "Desecration" );
       Element newAuthor = new Element( "author" );
       newAuthor.addContent( "Tim LaHaye" );
       Element newPrice = new Element( "price" );
       newPrice.addContent( "19.95" );
       newBook.addContent( newTitle );
       newBook.addContent( newAuthor );
       newBook.addContent( newPrice );
       root.addContent( newBook );

       System.out.println( "Book List After: " );
       showBooks( root );

       XMLOutputter out = new XMLOutputter( "  ", true );
       out.output( root, System.out );
  }
}
