import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ktao on 4/20/15.
 *
 * example usage :
 *
 * curl -H "Content-Type: application/json" -X POST -d '{"user":"Kevin Tao","description":"Work Out","due-date":"April 30th"}' http://localhost:8080/create
 */
public class CreateServlet extends HttpServlet {

    public static int currentId;

    public CreateServlet() throws IOException {
        FileReader reader = new FileReader(Constants.TODO_LIST_DATA_CSV);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

        int maxId = 0;

        List<CSVRecord> records = csvParser.getRecords();

        for(CSVRecord record : records) {
            maxId = Math.max(maxId, Integer.parseInt(record.get(Constants.ITEM_ID)));
        }

        currentId = maxId + 1;
        reader.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        if(!req.getContentType().equals("application/json")){

        }

        StringBuilder data = getData(req);
        JSONObject jsonObject = new JSONObject(data.toString());

        FileWriter fileWriter = new FileWriter(Constants.TODO_LIST_DATA_CSV, true);

        //PrintWriter printWriter = new PrintWriter(Constants.TODO_LIST_DATA_CSV);

        StringBuilder newLine = new StringBuilder();

        newLine.append(currentId).append(',');


        newLine.append(jsonObject.getString(Constants.USER)).append(',');

        newLine.append(jsonObject.getString(Constants.DESCRIPTION)).append(',');

        newLine.append(jsonObject.getString(Constants.DUE_DATE)).append(',');

        newLine.append("true\n");

        fileWriter.write(newLine.toString());

        fileWriter.close();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{\"status\":\"ok\",\"item-id\":" + currentId + "}");


        currentId++;
    }

    private StringBuilder getData(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder data = new StringBuilder();
        String line = reader.readLine();
        while(line != null){
            data.append(line);
            line = reader.readLine();
        }
        return data;
    }
}
