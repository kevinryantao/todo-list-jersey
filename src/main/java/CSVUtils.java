import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ktao on 4/20/15.
 */
public class CSVUtils {

    private static final String[] HEADERS = {
            Constants.ITEM_ID,
            Constants.USER,
            Constants.DESCRIPTION,
            Constants.DUE_DATE,
            Constants.COMPLETED
    };


    public static List<CSVRecord> filterOutByUser(String user, List<CSVRecord> csvRecords){
        List<CSVRecord> filteredList = new ArrayList<CSVRecord>();

        for(CSVRecord csvRecord : csvRecords){
            if(user.equals(csvRecord.get(Constants.USER))){
                filteredList.add(csvRecord);
            }
        }

        return filteredList;
    }

    public static List<CSVRecord> filterOnlyIncomplete(List<CSVRecord> csvRecords){
        List<CSVRecord> filteredList = new ArrayList<CSVRecord>();

        for(CSVRecord csvRecord : csvRecords){
            if (!Boolean.parseBoolean(csvRecord.get(Constants.COMPLETED))) {
                filteredList.add(csvRecord);
            }
        }

        return filteredList;
    }

    public static String csvRecordListToString(List<CSVRecord> csvRecords){
        StringBuilder result = new StringBuilder();

        for(CSVRecord csvRecord : csvRecords) {
            for(String header : HEADERS){
                result.append(csvRecord.get(header)).append(',');
            }
            result.append("\n<br>");

        }

        return result.toString();

    }
}
