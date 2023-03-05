package helpers;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class DataHelper {

    public static Map<String, String> convertTableToMap(DataTable dataTable) {
        List<Map<String, String>> updateMaps = dataTable.asMaps(String.class, String.class);
        return updateMaps.get(0);
    }
}
