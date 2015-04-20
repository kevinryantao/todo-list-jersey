import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * Created by ktao on 4/19/15.
 *
 * sample usage:
 * http://localhost:8080/readAllItems?user=John%20Doe
 */
public class ReadAllItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        FileReader reader = new FileReader(Constants.TODO_LIST_DATA_CSV);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

        String user = request.getParameter(Constants.USER);

        StringBuilder results = new StringBuilder();
        results.append("item-id,user,description,due-date,completed\n<br>");

        List<CSVRecord> records = csvParser.getRecords();

        if(user != null){

            records = CSVUtils.filterOutByUser(user, records);

        }

        results.append(CSVUtils.csvRecordListToString(records));


        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello Servlet</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId() + "<br>");
        response.getWriter().println(results.toString());
        reader.close();
    }


}
