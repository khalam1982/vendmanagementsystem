package com.centrica.vms.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;



/**
 * @author nagarajk
 *
 */
public class VendReportMenuBean {

	private Logger logger = ESAPI.getLogger(this.getClass());

	/**
	 * Constructor
	 */
	public VendReportMenuBean() {

	}
	
	/**
	 * @return
	 */
	public ArrayList<String> menuHourList() {
		
		logger.debug(Logger.EVENT_SUCCESS,"Entering menuHourList method ");
		
		ArrayList<String> menuList = new ArrayList<String>();

		String menu = null;

		menu = new String("01");

		menuList.add(menu);

		menu = new String("02");

		menuList.add(menu);

		menu = new String("03");

		menuList.add(menu);

		menu = new String("04");

		menuList.add(menu);

		menu = new String("05");

		menuList.add(menu);
		
		menu = new String("06");

		menuList.add(menu);

		menu = new String("07");

		menuList.add(menu);

		menu = new String("08");

		menuList.add(menu);

		menu = new String("09");

		menuList.add(menu);

		menu = new String("10");

		menuList.add(menu);

		menu = new String("11");

		menuList.add(menu);

		menu = new String("12");

		menuList.add(menu);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving menuHourList method ");
		
		return menuList;

	}


	/**
	 * @return
	 */
	public ArrayList<String> menuMinuteList() {

		logger.debug(Logger.EVENT_SUCCESS,"Entering menuMinuteList method ");
		
		ArrayList<String> menuList = new ArrayList<String>();

		String menu = null;

		menu = new String("00");

		menuList.add(menu);

		menu = new String("15");

		menuList.add(menu);

		menu = new String("30");

		menuList.add(menu);

		menu = new String("45");

		menuList.add(menu);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving menuMinuteList method ");
		
		return menuList;

	}
	
	/**
	 * @return
	 */
	public ArrayList<String> menuMeridList() {
		
		logger.debug(Logger.EVENT_SUCCESS,"Entering menuMeridList method ");

		ArrayList<String> menuList = new ArrayList<String>();

		String menu = null;

		menu = new String("AM");

		menuList.add(menu);

		menu = new String("PM");

		menuList.add(menu);

		logger.debug(Logger.EVENT_SUCCESS,"Leaving menuMeridList method ");
		
		return menuList;

	}
	
	/**
	 * @param sourceLst
	 * @return
	 */
	public Map<String, String> setSelectedSourceDetails(List<SourceDetails> sourceLst) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setSelectedSourceDetails method ");
		Iterator <SourceDetails> itr = sourceLst.iterator();
        SourceDetails source = null;
        Map<String, String> menuMap = new HashMap<String, String>();
        while (itr.hasNext())
        {
        	source = itr.next();
        	menuMap.put(source.getSource(), source.getSourceDescription());
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving setSelectedSourceDetails method ");
        return menuMap;
	}

	
	/**
	 * @param statusLst
	 * @return
	 */
	public Map<Integer, String> setSelectedStatusDetails(List<StatusDetails> statusLst) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setSelectedStatusDetails method ");
		Iterator <StatusDetails> itr = statusLst.iterator();
		StatusDetails status = null;
		Map<Integer, String> menuLst = new HashMap<Integer, String>();
        while (itr.hasNext())
        {
        	status = itr.next();
        	menuLst.put(status.getStatusCode(), status.getStatusDesc());
        }
        logger.debug(Logger.EVENT_SUCCESS,"Leaving setSelectedStatusDetails method ");
        return menuLst;
	}


}

