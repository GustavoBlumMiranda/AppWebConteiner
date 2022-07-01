package commons.util;



import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessagesUtil {

	private static Logger logger = LoggerFactory.getLogger(MessagesUtil.class);
	private static Configuration messages;
	private static Configuration labels;

	public static String getMessage(String key) {
		if (messages == null) {
			init();
		}

		return messages.getString(key);
	}

	public static String getMessage(String key, String param) {
		if (messages == null) {
			init();
		}
		return String.format(messages.getString(key),getLabel((String) param));
		
	}
	
	public static String getMessage(String key, String param, String param1) {
		if (messages == null) {
			init();
		}
		return String.format(messages.getString(key),getLabel((String) param),getLabel((String) param1));
		
	}
	
	public static String getLabel(String key) {
		if(labels == null) {
			init();
		}
		
		if(labels.getString(key.toLowerCase()) == null) 
			return key;
		
		return labels.getString(key.toLowerCase());
	}
	
	private static void init() {
		try {
			labels = new PropertiesConfiguration("label.properties");
			messages = new PropertiesConfiguration("messages.properties");
		} catch (ConfigurationException e) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				logger.error(stackTraceElement.toString());
			}
		}
	}
}
