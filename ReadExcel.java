import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 
public class ReadExcel {
 
    public static void main( String [] args ) 
    {
      Writer writer = null;
      	int i=0;
        try {
             InputStream input = new BufferedInputStream(
                        new FileInputStream("C:/users/johnn/Desktop/rawdata.xls"));
            POIFSFileSystem fs = new POIFSFileSystem( input );
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0); //sheet of excel
            File file = new File("C:/Users/johnn/Desktop/sorteddata.txt");  
            writer = new BufferedWriter(new FileWriter(file));
            Iterator rows = sheet.rowIterator(); 
            while( rows.hasNext() ) 
            {   
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                while( cells.hasNext() ) 
                {
                    HSSFCell cell = (HSSFCell) cells.next();
                    if(HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()) 
                    {
                     writer.write(String.valueOf(cell.getNumericCellValue()+"#"));
                    }
                    else
                    if(HSSFCell.CELL_TYPE_STRING==cell.getCellType()) 
                    {
                        writer.write(""+cell.getStringCellValue()+"#");
                    }
                    else
                        if(HSSFCell.CELL_TYPE_BOOLEAN==cell.getCellType()) 
                        {
                       writer.write(""+String.valueOf(cell.getBooleanCellValue()+"#"));
                        }
                    i++;
                    if(i==5){
                    	writer.write(System.lineSeparator());
                    	System.out.println("\n");
                    	i=0;}
                   
                }
                //i=0;
            }
            writer.close(); 
             
        } 
        catch ( IOException ex ) 
        {
            
        }
    }
     
}