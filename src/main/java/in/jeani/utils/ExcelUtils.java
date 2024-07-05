package in.jeani.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.jeani.tests.utils.IndividualTest;

@Component
public class ExcelUtils {
private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);
	
	@Autowired
	PropertiesUtil props;
	
	private List<IndividualTest> testSequence;
	
	public static int sheetIndex = 0;
	
	public List<IndividualTest> readInputFile() {
		testSequence = new ArrayList<IndividualTest>();
		
		try {
			
			FileInputStream fis = new FileInputStream(new File(props.getInputExcel()));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			int totalSheets = wb.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < totalSheets; sheetIndex++) {
				XSSFSheet sheet = wb.getSheetAt(sheetIndex);
				Iterator<Row> rows = sheet.iterator();
				IndividualTest test = null;
				while(rows.hasNext()) {
					Row row = rows.next();
					Iterator<Cell> cells = row.cellIterator();
					test = new IndividualTest();
					while(cells.hasNext()) {
						Cell cell = cells.next();
						String cellValue = cell.toString();
						if (row.getRowNum() > 0) {
							switch(cell.getColumnIndex()) {
							case 0:
								test.setScreenItem(cellValue);
								break;
							case 1:
								test.setName(cellValue);
								break;
							case 2:
								test.setType(cellValue);
								break;
							case 3:
								test.setSelector(cellValue);
								break;
							case 4:
								test.setAction(cellValue);
								break;
							case 5:
								test.setInputValue(cellValue);
								break;
							case 6:
								test.setExpectedResult(cellValue);
								break;
							case 7:
								test.setComment(cellValue);
								break;
							}
						}
					}
					if(row.getRowNum() > 0) {
						testSequence.add(test);
						LOGGER.debug("Test item: " + test);
					}
				}
			}
			wb.close();
			fis.close();
			
		} catch (IOException e) {
			LOGGER.error("Unable to read excel file.", e);
		}
		return testSequence;
	}
	public void writeInputFile(String sheetName, List<IndividualTest> results) {
		String sheetId = (StringUtils.isBlank(sheetName) ? "sheet" : sheetName) + "-" + (sheetIndex++);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheetId);
			int rowIndex = 0;
			int colIndx = 0;
			/**
			 * Adding headers here
			 */
			Row row = sheet.createRow(rowIndex++);
			row.createCell(colIndx++).setCellValue("SCREEN");
			row.createCell(colIndx++).setCellValue("TYPE");
			row.createCell(colIndx++).setCellValue("ITEM");
			row.createCell(colIndx++).setCellValue("SELECTOR");
			row.createCell(colIndx++).setCellValue("INPUT");
			row.createCell(colIndx++).setCellValue("ACTION");
			row.createCell(colIndx++).setCellValue("EXPECTED RESULT");
			row.createCell(colIndx++).setCellValue("COMMENTS");
			row.createCell(colIndx++).setCellValue("ACTUAL RESULT");
			row.createCell(colIndx++).setCellValue("TEST RESULT");
			row.createCell(colIndx++).setCellValue("DURATION OF TEST");
			
			for (IndividualTest test : results) {
				row = sheet.createRow(rowIndex++);
				colIndx = 0;
				row.createCell(colIndx++).setCellValue(test.getName());
				row.createCell(colIndx++).setCellValue(test.getType());
				row.createCell(colIndx++).setCellValue(test.getScreenItem());
				row.createCell(colIndx++).setCellValue(test.getSelector());
				String inputValue = test.getInputValue() != null ? test.getInputValue() : "";
				row.createCell(colIndx++).setCellValue(inputValue);
				row.createCell(colIndx++).setCellValue(test.getAction());
				row.createCell(colIndx++).setCellValue(test.getExpectedResult());
				row.createCell(colIndx++).setCellValue(test.getComment());
				row.createCell(colIndx++).setCellValue(test.getComment());
				
				String assertionResult = test.isAssertionResult() ? "PASSED" : "FAIL"; 
				row.createCell(colIndx++).setCellValue(assertionResult);
				
				double durInSeconds = test.getDurationOfTest()/1000D;
				String duration = ""+(durInSeconds)+ " seconds" ;
				row.createCell(colIndx++).setCellValue(duration);
			}
			FileOutputStream outputStream = new FileOutputStream(props.getOutputExcel());
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			workbook.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
