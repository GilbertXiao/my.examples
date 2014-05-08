package mr.shravan.examples.spring.domain;

import java.util.List;

public interface BookLibrary {

  public List<Book> search(String title);

}
