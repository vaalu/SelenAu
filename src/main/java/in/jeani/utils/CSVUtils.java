package in.jeani.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import in.jeani.tests.utils.IndividualTest;

@Component
public class CSVUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);
	
	@Autowired
	PropertiesUtil props;
	
	private List<IndividualTest> testSequence;
	
	public List<IndividualTest> readInputCSVFile() {
		String inputCsvPath = props.getInputCsv();
		testSequence = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(inputCsvPath);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			int index = 0;
			int colIndex = 0;
			IndividualTest test = null;
			while((nextRecord = csvReader.readNext()) != null) {
				LOGGER.info("Functionality check: " + nextRecord);
				test = new IndividualTest();
				for (String cell : nextRecord) {
					if (index > 0) {
						switch(colIndex) {
						case 0:
							test.setName(cell);
							break;
						case 1:
							LOGGER.info("Type: " + cell);
							test.setType(cell);
							break;
						case 2:
							test.setSelector(cell);
							break;
						case 3:
							test.setAction(cell);
							break;
						case 4:
							test.setInputValue(cell);
							break;
						case 5:
							test.setExpectedResult(cell);
							break;
						case 6:
							test.setComment(cell);
							break;
						}
					}
					colIndex++;
				}
				testSequence.add(test);
				index++;
				colIndex = 0;
			}
			csvReader.close();
		} catch (CsvValidationException | IOException e) {
			LOGGER.error("Unable to read CSV file", e);
		}
		return testSequence;
	}
}
