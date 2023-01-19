import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static final String INPUT = "/src/main/resources/input.csv";
    public static final String OUTPUT = "/src/main/resources/output.pdf";

    public static void main(String args[]) throws IOException, CsvException {
        //Read input at /resources/input.csv and save all rows
        CSVReader reader = new CSVReader(new FileReader(System.getProperty("user.dir") + INPUT));
        List<String[]> rows = reader.readAll();

        //Create new pdf
        PdfDocument pdf = new PdfDocument(new PdfWriter(System.getProperty("user.dir") + OUTPUT));
        Document document = new Document(pdf);

        //Create Table with columns based on data
        Table table = new Table(rows.get(0).length);

        //Write rows to table
        for(String [] row : rows) {
            for(String entry : row) {
                table.addCell(entry);
            }
        }

        //Add table to document and save
        document.add(table);
        document.close();
    }
}