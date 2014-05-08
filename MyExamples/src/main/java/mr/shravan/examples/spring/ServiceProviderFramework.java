package mr.shravan.examples.spring;

import mr.shravan.examples.spring.domain.BookReader;
import mr.shravan.examples.spring.domain.JavaBookLibrary;

public class ServiceProviderFramework {

  private BookReader bookReader;
  
  public ServiceProviderFramework(){
    this.bookReader = new BookReader(new JavaBookLibrary());    
  }
  
  public BookReader getBookReaderInstance(){
    return this.bookReader;
  }
}
