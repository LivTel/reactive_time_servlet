package ngat.web;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class RequestUtil {

	public static String createDocumentId(String appendedId) {
		String iaPrefix = RequestProperties.getInstance().getProperty(RequestProperties.IA_PREFIX);
		String id = iaPrefix + RequestUtil.getTimeString() + "_" + appendedId;
		return id;
	}

	public static String getTimeString() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = RequestUtil.zeroPad(String.valueOf(c.get(Calendar.MONTH) + 1), 2);
		String day = RequestUtil.zeroPad(String.valueOf(c.get(Calendar.DAY_OF_MONTH)), 2);
		String hour = RequestUtil.zeroPad(String.valueOf(c.get(Calendar.HOUR_OF_DAY)), 2);
		String minute = RequestUtil.zeroPad(String.valueOf(c.get(Calendar.MINUTE)), 2);
		String second = RequestUtil.zeroPad(String.valueOf(c.get(Calendar.SECOND)), 2);

		return year + "-" + month + "-" + day + "-" + hour + ":" + minute + ":" + second;
	}

	public static String zeroPad(String s, int totalNumOfChars) {
		int originalLength = s.length();
		int numZerosNeeded = totalNumOfChars - originalLength;
		String zerosString = "";
		for (int i = 0; i < numZerosNeeded; i++) {
			zerosString += "0";
		}
		return zerosString + s;
	}

	public static void populateBean(Object formBean, HttpServletRequest request) {
		populateBean(formBean, request.getParameterMap());
	}

	/**
	 * Populates a bean based on a Map: Map keys are the bean property names;
	 * Map values are the bean property values. Type conversion is performed
	 * automatically as described above.
	 */

	public static void populateBean(Object bean, Map propertyMap) {
		try {
			BeanUtils.populate(bean, propertyMap);
		} catch (Exception e) {
			// Empty catch. The two possible exceptions are
			// java.lang.IllegalAccessException and
			// java.lang.reflect.InvocationTargetException.
			// In both cases, just skip the bean operation.
		}
	}

}
