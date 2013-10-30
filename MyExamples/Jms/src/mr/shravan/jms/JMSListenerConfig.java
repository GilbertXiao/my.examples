package mr.shravan.jms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jms.listener.DefaultMessageListenerContainer;


/**
 * 
 * @author kpathuru
 * This class represents the configuration of a JMS listener in the configuration file.
 */
public class JMSListenerConfig {
	
	private static final long serialVersionUID = -5781272564592060857L;

	
	public static Map<String, JMSListenerConfig> listenerConfigs = new HashMap<String, JMSListenerConfig>();
	
	private String listenerName = null;
	private String ctxFactory = null;
	private String providerURL = null;
	private String jndiUserName = null;
	private String jndiPassword = null;
	
	private String qcFactory = null;
	private String queueName = null;
	private int retryLimit = 0;
	private int retryInterval = 0;
	private boolean stopListenerOnRetryFail = false;
	private String successResponseQueue = null;
	
	private boolean dlqConfigured = true;
	private boolean enableOnStartup = false;
	
	private String key = null;
	
	
	public JMSListenerConfig() {
	}

	public JMSListenerConfig(String key, String listenerName, String ctxFactory, String providerURL, String jndiUsername, String jndiPassword, 
			String qcFactory, String queueName, int retryLimit, int retryInterval, boolean stopListenerOnRetryFail, String successResponseQueue,
			boolean dlqConfigured, boolean enableOnStartup) {
		this.key = key;
		this.listenerName = listenerName;
		this.ctxFactory = ctxFactory;
		this.providerURL = providerURL;
		this.jndiUserName = jndiUsername;
		this.jndiPassword = jndiPassword;
		this.qcFactory = qcFactory;
		this.queueName = queueName;
		this.retryLimit = retryLimit;
		this.retryInterval = retryInterval;
		this.stopListenerOnRetryFail = stopListenerOnRetryFail;
		this.successResponseQueue = successResponseQueue;
		this.dlqConfigured = dlqConfigured;
		this.enableOnStartup = enableOnStartup;
	}
	
	public String getListenerName() {
		return listenerName;
	}
	public void setListenerName(String listenerName) {
		this.listenerName = listenerName;
	}
	
	public String getCtxFactory() {
		return ctxFactory;
	}
	public void setCtxFactory(String ctxFactory) {
		this.ctxFactory = ctxFactory;
	}
	
	public String getProviderURL() {
		return providerURL;
	}
	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}
	
	public String getJndiUserName() {
		return jndiUserName;
	}
	public void setJndiUserName(String jndiUserName) {
		this.jndiUserName = jndiUserName;
	}
	
	public String getJndiPassword() {
		return jndiPassword;
	}
	public void setJndiPassword(String jndiPassword) {
		this.jndiPassword = jndiPassword;
	}
	
	public String getQcFactory() {
		return qcFactory;
	}
	public void setQcFactory(String qcFactory) {
		this.qcFactory = qcFactory;
	}
	
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	
	public int getRetryLimit() {
		return retryLimit;
	}
	public void setRetryLimit(int retryLimit) {
		this.retryLimit = retryLimit;
	}
	
	public int getRetryInterval() {
		return retryInterval;
	}
	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}
	
	public boolean isStopListenerOnRetryFail() {
		return stopListenerOnRetryFail;
	}
	public void setStopListenerOnRetryFail(boolean stopListenerOnRetryFail) {
		this.stopListenerOnRetryFail = stopListenerOnRetryFail;
	}

	public String getSuccessResponseQueue() {
		return successResponseQueue;
	}

	public void setSuccessResponseQueue(String successResponseQueue) {
		this.successResponseQueue = successResponseQueue;
	}

	public boolean isDlqConfigured() {
		return dlqConfigured;
	}

	public void setDlqConfigured(boolean dlqConfigured) {
		this.dlqConfigured = dlqConfigured;
	}

	public boolean isEnableOnStartup() {
		return enableOnStartup;
	}

	public void setEnableOnStartup(boolean enableOnStartup) {
		this.enableOnStartup = enableOnStartup;
	}


	
	
	public static void addListenerConfig(JMSListenerConfig config) {
		if(config != null) {
			JMSListenerConfig.listenerConfigs.put(config.getKey(), config);
		}
	}
	
	public String getKey() {
		return this.key;
		/*
		StringBuffer key = new StringBuffer();
		if(getCtxFactory() != null && getCtxFactory().trim().length() > 0) {
			key.append(getCtxFactory());
		}
		if(getProviderURL() != null && getProviderURL().trim().length() > 0) {
			key.append(getProviderURL());
		}
		else {
			key.append("");
		}
		key.append(getQcFactory()).append(getQueueName());
		return key.toString();
		*/
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	

	public String getSerializePK() {
		return this.key;
	}

	public void setSerializePK(String key) {
		this.key = key;
	}
}
