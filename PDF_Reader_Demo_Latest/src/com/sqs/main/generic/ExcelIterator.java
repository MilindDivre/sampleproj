package com.sqs.main.generic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelIterator 
{
	String flFile;
	int intSheetNumber;
	int intIndex = 0;
	int intNoOfRow;
	int intNoOfColumn;
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	FileInputStream excelFileIS;
	Row headerRow;
	Row testDataRow;

	public ExcelIterator()
	{}

	public ExcelIterator(String strExcelFile, int intSheetno, boolean ignoreHeaderRow)
	{
		flFile = strExcelFile;
		intSheetNumber = intSheetno;
		try
		{
			excelFileIS = new FileInputStream(flFile);
			//workbook = new XSSFWorkbook(new File(flFile));
			workbook = new XSSFWorkbook(excelFileIS);
			excelFileIS.close();
			sheet = workbook.getSheetAt(intSheetno);
			intNoOfRow = sheet.getPhysicalNumberOfRows();
			if(ignoreHeaderRow)
				intIndex++;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		} 
	}

	public boolean isDone() 
	{
		if(intIndex < intNoOfRow) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public String getColumn(int clmNo)
	{
		String strCellvalue = "";
		org.apache.poi.ss.usermodel.Row row = null;
		org.apache.poi.ss.usermodel.Cell cell = null;
		row = sheet.getRow(intIndex);
		intNoOfColumn = row.getPhysicalNumberOfCells();

		try
		{
			cell = row.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
			if (cell == null)
				strCellvalue = "";
			else 
				strCellvalue = cell.toString().trim();
			strCellvalue = cell.toString();
			return strCellvalue;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return strCellvalue;
	}


	public String getColumn(String columnHeader)
	{
		org.apache.poi.ss.usermodel.Row headerRow = sheet.getRow(0);
		org.apache.poi.ss.usermodel.Row testDataRow = sheet.getRow(this.getCurrentRowNumber());
		int clmNo = 0;
		String cellValue = "";
		try
		{
			do
			{
				String currentHeader = "";
				org.apache.poi.ss.usermodel.Cell headerCell = headerRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);
				if (headerCell == null)
					currentHeader = "";
				else 
					currentHeader = headerCell.toString().trim();
				if(currentHeader.equalsIgnoreCase(columnHeader))
				{
					org.apache.poi.ss.usermodel.Cell testDataCell = testDataRow.getCell(clmNo, Row.RETURN_BLANK_AS_NULL);

					if (testDataCell == null)
						cellValue = "";
					else
					{
						//
						if(testDataCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							switch(testDataCell.getCachedFormulaResultType()) {
							case Cell.CELL_TYPE_NUMERIC:
								cellValue = testDataCell.toString().trim();
								break;
							case Cell.CELL_TYPE_STRING:
								cellValue = testDataCell.getRichStringCellValue().toString().trim();
								break;
							}
						}
						if(testDataCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						{
							cellValue = testDataCell.getRichStringCellValue().toString().trim();
						}
						if(testDataCell.getCellType() == Cell.CELL_TYPE_STRING)
						{
							cellValue = testDataCell.getRichStringCellValue().toString().trim();
						}
					}
				}
				clmNo++;
			}
			while(clmNo < headerRow.getLastCellNum());
		}
		catch(Exception exception)
		{
		}
		return cellValue;
	}

	public void next()
	{
		intIndex++;
	}

	public int getCurrentRowNumber()
	{
		return intIndex;
	}

	public int getNumberOfRows()
	{
		return intNoOfRow; 
	}

	public int getNumberOfColumnForCurrentRow()
	{
		return intNoOfColumn;
	}

	/**
	 * @Method      : loadTestData(String testCaseID, String testDataFile)
	 * @Description : Load Data from Excel for the running testCase and return as Object array    
	 * @author      : Indrajeet Chavan (SQS) 
	 * @CreationDate: 04 December 2015  
	 * @ModifiedDate:
	 */
	public Object[][] loadTestData(String testCaseID, String testDataFilePath)
	{
		ArrayList<Hashtable<String, String>> hashTableList = new ArrayList<Hashtable<String, String>>();
		Object[][] objDataProvider = null;

		int lastRowNumber = 1;
		String bufferCell = "";
		// Logic to read the Excel workBook
		try 
		{
			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
			testDataRow = sheet.getRow(1);
			lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0;

			while( rowIndex <= lastRowNumber)
			{
				String cellData = this.getCellValue(sheet.getRow(rowIndex),0);
				if(cellData.equalsIgnoreCase("TestCaseId"))
				{
					headerRow = sheet.getRow(rowIndex);
					cellData = this.getCellValue(sheet.getRow(++rowIndex), 0);
				}
				if(cellData.equalsIgnoreCase(testCaseID))
				{
					bufferCell = this.getCellValue(sheet.getRow(rowIndex),0);
					while(rowIndex <= lastRowNumber && !bufferCell.equalsIgnoreCase("TestCaseId"))
					{
						if(bufferCell.equalsIgnoreCase(testCaseID))
						{
							testDataRow = sheet.getRow(rowIndex);
							Hashtable<String , String> dataValueSet = new Hashtable<String, String>();
							int clmNo = 0;
							//iterating over cells
							do
							{
								String header = "", testData = "";
								// Key Data
								header = this.getCellValue(headerRow,clmNo);
								// Value
								testData = this.getCellValue(testDataRow,clmNo);

								if(!header.equals(""))
									dataValueSet.put(header, testData);
								clmNo++;

							}while(clmNo < headerRow.getLastCellNum());
							//put the hash-table in list
							hashTableList.add(dataValueSet);
							dataValueSet = null;
							clmNo = 0;
						}
						rowIndex++;
						if(rowIndex > lastRowNumber)
							bufferCell = "";
						else
							bufferCell = this.getCellValue(sheet.getRow(rowIndex),0);
					};
					break;
				}
				rowIndex++;     
			}

			objDataProvider = new Object[hashTableList.size()][2];
			int rowCount = 0;
			for( Hashtable<String, String>hashTable : hashTableList) 
			{
				objDataProvider[rowCount][0] = "Run"+(rowCount+1);
				objDataProvider[rowCount][1] = hashTable;
				rowCount++;
			}
			excelFileIS.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			hashTableList = null;
		}
		return objDataProvider;
	}

	/**
	 * @Method      : updateTestDataSheet(String testDataFile, String testCaseID, String runID,Hashtable<String, String> testDataToUpdate)
	 * @Description : Load Data from Excel for the running testCase and return as Object array    
	 * @author      : Indrajeet Chavan (SQS) 
	 * @CreationDate: 04 December 2015  
	 * @ModifiedDate:
	 */
	public boolean updateTestDataSheet(String testDataFilePath, String testCaseID, String runID,Hashtable<String, String> testDataToUpdate)
	{
		int lastRowNumber = 1;
		String bufferCell = "";
		// Logic to read the Excel workBook
		try 
		{
			excelFileIS = new FileInputStream(testDataFilePath);
			workbook = new XSSFWorkbook(excelFileIS);
			sheet = workbook.getSheetAt(0);
			headerRow = sheet.getRow(0);
			testDataRow = sheet.getRow(1);
			lastRowNumber = sheet.getLastRowNum();
			int rowIndex = 0, runCounter = 0;

			while( rowIndex <= lastRowNumber)
			{
				String cellData = this.getCellValue(sheet.getRow(rowIndex),0);
				if(cellData.equalsIgnoreCase("TestCaseId"))
				{
					headerRow = sheet.getRow(rowIndex);
					cellData = this.getCellValue(sheet.getRow(++rowIndex), 0);
				}
				if(cellData.equalsIgnoreCase(testCaseID))
				{

					bufferCell = this.getCellValue(sheet.getRow(rowIndex),0);
					while(rowIndex <= lastRowNumber && !bufferCell.equalsIgnoreCase("TestCaseId"))
					{
						if(bufferCell.equalsIgnoreCase(testCaseID))
						{
							runCounter++;
							if(runID.equalsIgnoreCase("Run"+runCounter))
							{
								testDataRow = sheet.getRow(rowIndex);
								int clmNo = 0;
								//iterating over cells
								do
								{
									String header = "";
									// Key Data
									header = this.getCellValue(headerRow,clmNo);
									if(testDataToUpdate.containsKey(header))
										this.putCellValue(testDataRow, clmNo, testDataToUpdate.get(header));
									clmNo++;
								}while(clmNo < headerRow.getLastCellNum());
								// Save and close file
								clmNo = 0;
								excelFileIS.close();
								FileOutputStream fileOut = new FileOutputStream(testDataFilePath);
								workbook.write(fileOut);
								fileOut.close();
								return true; 
							}
						}
						rowIndex++;
						if(rowIndex > lastRowNumber)
							bufferCell = "";
						else
							bufferCell = this.getCellValue(sheet.getRow(rowIndex),0);
					};
					break;
				}
				rowIndex++;     
			}
			excelFileIS.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
		return false;		
	}

	/**
	 * @Method      : putCellValue(Row testDataRow, int columnNumber, String testData)
	 * @Description : Put Cell value for given cell (Used in loadDataProvider)    
	 * @author      : Indrajeet Chavan (SQS) 
	 * @CreationDate: 04 December 2015  
	 * @ModifiedDate:
	 */
	private boolean putCellValue(Row testDataRow, int columnNumber, String testData)
	{
		if (testDataRow != null)
		{
			Cell testDataCell = testDataRow.getCell(columnNumber,Row.CREATE_NULL_AS_BLANK);
			if(testDataCell != null)
			{
				testDataCell.setCellValue(testData);
				return true;
			}
		}
		return false;
	}

	/**
	 * @Method      : getCellValue(Cell testDataCell, int columnNumber)
	 * @Description : Get Cell value for given cell (Used in loadDataProvider)    
	 * @author      : Indrajeet Chavan (SQS) 
	 * @CreationDate: 04 December 2015  
	 * @ModifiedDate:
	 */
	private String getCellValue(Row testDataRow, int columnNumber)
	{
		String returnValue = "";
		if (testDataRow == null)
			return "";
		else 
		{
			Cell testDataCell = testDataRow.getCell(columnNumber,Row.RETURN_BLANK_AS_NULL);
			if(testDataCell == null)
				return "";
			else
			{
				if(testDataCell.getCellType() == Cell.CELL_TYPE_FORMULA) 
				{
					switch(testDataCell.getCachedFormulaResultType()) 
					{
					case Cell.CELL_TYPE_NUMERIC:
						returnValue = testDataCell.toString().trim();
						break;
					case Cell.CELL_TYPE_STRING:
						returnValue = testDataCell.getRichStringCellValue().toString().trim();
						break;
					}
				}
				if(testDataCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
				{
					returnValue =  testDataCell.toString().trim();
				}
				if(testDataCell.getCellType() == Cell.CELL_TYPE_STRING)
				{
					returnValue = testDataCell.getRichStringCellValue().toString().trim();
				}
			}
			return returnValue;
		}
	}

}
