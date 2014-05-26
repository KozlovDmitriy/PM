
package simplecbrapp.report;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Класс чтения и формирования сводного отчета.
 * @author M. Navrotskiy
 * @version 1.0
 */
public class ExcelReport {
    
    /* Поля класса. */
    /** Имя файла - отчета. */
    private String filename;
    /** Поток чтения. */
    private FileInputStream fis;
    /** Книга файла. */
    private HSSFWorkbook wb;
    /** Таблица. */
    private HSSFSheet sheet;
    
}
