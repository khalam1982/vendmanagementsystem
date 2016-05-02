
/**
 * SmartMeterManageSCNInServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package smartmeterprocessing.vend.uk.co.britishgas;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.ws.sap.service.VMSSAPService;
import com.centrica.vms.ws.service.VMSServiceImpl;
    /**
     *  SmartMeterManageSCNInServiceSkeleton java skeleton for the axisService
     */
    public class SmartMeterManageSCNInServiceSkeleton implements SmartMeterManageSCNInServiceSkeletonInterface{
        
    	private Logger logger = ESAPI.getLogger(this.getClass());
        /**
         * Auto generated method signature
         * 
                                     * @param asynActivationandDeactivation0
         */
        
                 public void SmartMeterManageSCNIn
                  (
                  com.centrica.vms.ws.pi.service.AsynActivationandDeactivation asynActivationandDeactivation0
                  )
            {
                logger.debug(Logger.EVENT_SUCCESS,"Entering SmartMeterManageSCNIn");
       			VMSSAPService vmsSAPService = new VMSServiceImpl();
       			vmsSAPService.handleSCNRegistrationService(asynActivationandDeactivation0);
       			logger.debug(Logger.EVENT_SUCCESS,"Leaving SmartMeterManageSCNIn");
                
        }
     
    }
    
