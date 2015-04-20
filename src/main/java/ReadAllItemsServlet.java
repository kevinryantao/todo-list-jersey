import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ktao on 4/19/15.
 */
public class ReadAllItemsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CSVParser csvParser = new CSVParser(new FileReader("todo-list-data.csv"), CSVFormat.DEFAULT.withHeader());

        String user = request.getParameter(Constants.USER);

        StringBuilder results = new StringBuilder();
        results.append("item-id,user,description,due-date,completed\n<br>");

        if(user != null){
            List<CSVRecord> records = csvParser.getRecords();
            for(CSVRecord csvRecord : records) {
                if( user.equals(csvRecord.get(Constants.USER)) ){
                    results.append(csvRecord.toString()).append("\n<br>");
                }
            }
        } else {
            List<CSVRecord> records = csvParser.getRecords();
            for(CSVRecord csvRecord : records) {
                    results.append(csvRecord.toString()).append("\n<br>");
            }

        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello Servlet</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId() + "<br>");
        response.getWriter().println(results.toString());
    }


}
