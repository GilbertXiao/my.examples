package mr.shravan.examples.jms.context;

import javax.resource.spi.CommException;

import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IFWApplicationContext {

	/**
	 * This method loads all the bootstrap configuration for LIF.
	 * 
	 * @param factoryKey
	 * @return
	 * @throws CommException
	 */
	public static BeanFactoryReference getBeanFactoryReference(String factoryKey)
			throws Exception {
		BeanFactoryReference ejbBeanFactoryRef = null;
		try {
			
			ejbBeanFactoryRef = ContextSingletonBeanFactoryLocator
					.getInstance().useBeanFactory(factoryKey);

		} catch (Exception ex) {
			log("Exception while looking up spring config factory bean::<FactoryKey>:"
					+ factoryKey);
			log("And the exception is :" + ex);
			throw new Exception("Exception Initializing LIFApplicationContext",
					ex);
		}
		return ejbBeanFactoryRef;
	}

	public static BeanFactoryReference getJMSListenerBeanFactoryReference()
			throws Exception {
		return getBeanFactoryReference("jmsContainer");
	}

	private static void log(String entry) {
		System.out.println(entry);
	}
	public static void main(String[] args) throws Exception {
		
		System.out.println(IFWApplicationContext.getJMSListenerBeanFactoryReference());
	}
}
