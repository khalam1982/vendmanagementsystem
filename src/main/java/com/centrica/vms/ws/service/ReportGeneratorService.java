/**
 * 
 */
package com.centrica.vms.ws.service;



import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.dao.VendReportTransactionDAO;
import com.centrica.vms.model.ReportGeneratorDetails;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.VendReportDetails;
import com.centrica.vms.model.VendTxnStatus;

/**
 * @author nagarajk
 *
 */
public class ReportGeneratorService {
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private ReportGeneratorDetails reportGeneratorDetails = null;
	private Logger logger = ESAPI.getLogger(this.getClass());

	public ReportGeneratorService(ReportGeneratorDetails reportGeneratorDetails) {
		this.reportGeneratorDetails = reportGeneratorDetails;
	}
	
	/**
	 * Method used to write the data on to the excel sheet
	 * @param data
	 * @throws IOException
	 * @throws WriteException
	 */
	public void write(ArrayList<VendReportDetails> data, OutputStream out) throws ParseException, WriteException, IOException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering write method ");
		//File file = new File(inputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		//WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		WritableWorkbook workbook = Workbook.createWorkbook(out, wbSettings);
		workbook.createSheet("Report", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet);
		createContent(excelSheet,data);
		workbook.write();
		workbook.close();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving write method ");
	}

	/**
	 * Method used to create the label
	 * @param sheet
	 * @throws WriteException
	 */
	private void createLabel(WritableSheet sheet)
	throws WriteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createLabel method ");
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);
		// Create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);
		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(true);
		// Write a few headers
		addCaption(sheet, 0, 0, "TRANSACTION ID");
		addCaption(sheet, 1, 0, "PAN");
		addCaption(sheet, 2, 0, "TRANSACTION TYPE");
		addCaption(sheet, 3, 0, "CREDIT VALUE(PENCE)");
		addCaption(sheet, 4, 0, "SOURCE");
		addCaption(sheet, 5, 0, "STATUS");
		addCaption(sheet, 6, 0, "CREATED TIMESTAMP");
		addCaption(sheet, 7, 0, "UPDATED TIMESTAMP");
		addCaption(sheet, 8, 0, "AVERAGE VEND TIME(Mins)");
		addCaption(sheet, 9, 0, "AVERAGE VEND TIME(Secs)");
		addCaption(sheet, 10, 0, "RETRY COUNT");
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createLabel method ");
	}

	/**
	 * Method used to create the content
	 * @param sheet
	 * @param data
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void createContent(WritableSheet sheet,ArrayList<VendReportDetails> data) throws WriteException,
	RowsExceededException, ParseException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createContent method ");
		VendReportDetails vendReportDetails = null;
		String triggerName = null;
		Date updatedTimestamp = null;
		String statusDesc = null;
		Set <VendTxnStatus> vendTxnStatus = null;
		String retryCount = null;
		String sourceDesc = null;
		if (data != null) {
			Iterator<VendReportDetails> iterator = data.iterator();
			int i=0;
			while(iterator.hasNext()){
				sourceDesc = null;
				retryCount = null;
				vendReportDetails = iterator.next();
				vendTxnStatus = vendReportDetails.getTxnStatusDetails();
				VendTxnStatus status = vendTxnStatus.iterator().next();
				statusDesc = status.getStatusDetails().getStatusDesc();
				updatedTimestamp = status.getUpdatedTimeStamp();
				triggerName = vendReportDetails.getTriggerName();
				if (triggerName != null) {
					retryCount = triggerName.substring(triggerName.length()-1);
				}
				SourceDetails sourceDetails = vendReportDetails.getSourceDetails();
				if (sourceDetails != null) {
					sourceDesc = sourceDetails.getSourceDescription();
				}
				addLabel(sheet, 0, ++i, vendReportDetails.getTransactionID());
				addLabel(sheet, 1, i, vendReportDetails.getPan());
				addLabel(sheet, 2, i, vendReportDetails.getTransactionType());
				addLabel(sheet, 3, i, vendReportDetails.getCreditValue());
				addLabel(sheet, 4, i, sourceDesc);
				addLabel(sheet, 5, i, statusDesc);
				addLabel(sheet, 6, i, vendReportDetails.getCreatedTimeStamp().toString());
				addLabel(sheet, 7, i, updatedTimestamp.toString());
				addLabel(sheet, 8, i, getAverageVendTime_Min(vendReportDetails.getCreatedTimeStamp().toString(),updatedTimestamp.toString()));
				addLabel(sheet, 9, i, getAverageVendTime_Sec(vendReportDetails.getCreatedTimeStamp().toString(),updatedTimestamp.toString()));
				addLabel(sheet, 10, i, retryCount);
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createContent method ");
	}

	/**
	 * Method used to add the caption
	 * @param sheet
	 * @param data
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void addCaption(WritableSheet sheet, int column, int row, String s)
	throws RowsExceededException, WriteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering addCaption method ");
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving addCaption method ");
	}

	/**
	 * Method used to add the label
	 * @param sheet
	 * @param column
	 * @param row
	 * @param s
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void addLabel(WritableSheet sheet, int column, int row, String s)
	throws WriteException, RowsExceededException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering addLabel method ");
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving addLabel method ");
	}



	/**
	 * Main class
	 * @param args
	 */
	public void invoke(OutputStream out) throws ParseException, WriteException, IOException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering invoke method ");
		Date startDateRange = this.reportGeneratorDetails.getStartDate();
		Date endDateRange = this.reportGeneratorDetails.getEndDate();
		String source = this.reportGeneratorDetails.getSource();
		String status = this.reportGeneratorDetails.getStatus();
		
		try{
			ArrayList<VendReportDetails> resultList = null;
			ArrayList<VendReportDetails> arrayList = new VendReportTransactionDAO().getReportData(
					startDateRange,endDateRange, source);
			if (status != null && !status.isEmpty()) {
				resultList = new ArrayList<VendReportDetails>();
				Integer statusCode = Integer.parseInt(status);
				Iterator<VendReportDetails> itr = arrayList.iterator();
				VendReportDetails vendReportDetails = null;
				if(itr != null) {
					while(itr.hasNext()) {
						vendReportDetails = itr.next();
						if (vendReportDetails.getStatusCode().equals(statusCode)) {
							resultList.add(vendReportDetails);
						}
					}
				}
			} else {
				resultList = arrayList;
			}
			write(resultList, out);
		} catch (WriteException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving invoke method ");
	}

	/**
	 * Method used to find the vend average time in seconds
	 * @param createdDate
	 * @param updatedDate
	 * @return
	 */
	private String getAverageVendTime_Sec(String createdDate,String updatedDate) throws ParseException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAverageVendTime_Sec method ");
		long diffSec = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try{
			long startDateRange = dateFormat.parse(createdDate).getTime();
			long endDateRange = dateFormat.parse(updatedDate).getTime();
			diffSec = (endDateRange-startDateRange)/1000;
		}catch(ParseException ex){
			logger.error(Logger.EVENT_FAILURE,"ParseException " + ex.getMessage());
			throw ex;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAverageVendTime_Sec method ");
		return new Long(diffSec).toString();
	}
	/**
	 * Method used to find the vend average time in minutes
	 * @param createdDate
	 * @param updatedDate
	 * @return
	 */
	private String getAverageVendTime_Min(String createdDate,String updatedDate) throws ParseException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAverageVendTime_Min method ");
		long diffSec = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try{
			long startDateRange = dateFormat.parse(createdDate).getTime();
			long endDateRange = dateFormat.parse(updatedDate).getTime();
			diffSec = (endDateRange-startDateRange)/(1000*60);
		}catch(ParseException ex){
			logger.error(Logger.EVENT_FAILURE,"ParseException " + ex.getMessage());
			throw ex;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAverageVendTime_Min method ");
		return new Long(diffSec).toString();
	}
}
