package mr.shravan.examples.spring;

import java.util.List;


import mr.shravan.examples.spring.domain.Book;
import mr.shravan.examples.spring.domain.BookLibrary;
import mr.shravan.examples.spring.domain.BookReader;
import mr.shravan.examples.spring.domain.JavaBookLibrary;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
	
	public static void main(String[] args) {
//		withOutFramework();
//		withCustomFramework();
		withSpringFramework();
	} 
	public static void withOutFramework() {

		BookLibrary library = new JavaBookLibrary();

		BookReader reader = new BookReader(library);

		List<Book> books = reader.read();

		System.out.printf("Client read: %s books%n", books.size());

	}

	public static void withCustomFramework() {

		ServiceProviderFramework framework = new ServiceProviderFramework();

		BookReader client = framework.getBookReaderInstance();

		List<Book> books = client.read();

		System.out.printf("Client read: %s books%n", books.size());
	}

	public static void withSpringFramework() {

		BeanFactory beanFactory = new ClassPathXmlApplicationContext(
				"mr/shravan/examples/spring/applicationContext.xml");

		BookReader client = (BookReader) beanFactory.getBean("bookReader");

		List<Book> books = client.read();

		System.out.printf("Client read: %s books%n", books.size());

	}

}
