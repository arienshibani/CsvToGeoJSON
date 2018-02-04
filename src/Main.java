import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    private static String start_of_file =
            "{" +
                "\"name\":\"Lekeplasser\"," +
                "\"type\":\"FeatureCollection\"\n," +
                "\"features\":[";

    private static String end_of_file = "]}";

    public static void main(String[] args) {

        String csvFile = "C:/Users/Arien/Documents/Java/CSVParser2/res/dokart.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine(); //Skip first column
            String geojsoncontent = start_of_file;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] playground = line.split(cvsSplitBy);
                geojsoncontent += makeGeoJsonLekeplass(playground);

            }
            geojsoncontent = geojsoncontent.substring(0, geojsoncontent.length() - 1);
            geojsoncontent += end_of_file;
            System.out.println(geojsoncontent);
            try(  PrintWriter out = new PrintWriter( "dokart.geojson" )  ){
                out.println( geojsoncontent );
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String makeGeoJsonLekeplass(String[] toilet){
        return "{\n" +
                "  \"type\": \"Feature\",\n" +
                "  \"geometry\": {\n" +
                "    \"type\": \"Point\",\n" +
                "    \"coordinates\": ["+toilet[14]+", "+toilet[13]+"]\n" +
                "  },\n" +
                "  \"properties\": {\n" +
                "    \"name\": \""+toilet[0]+"\",\n" +
                "    \"plassering\": \""+toilet[1]+"\""+
                "    \"place\": \""+toilet[2]+"\",\n" +
                "    \"adresse\": \""+toilet[3]+"\",\n" +
                "    \"pris\": \""+toilet[4]+"\",\n" +
                "    \"tid_hverdag\": \""+toilet[5]+"\",\n" +
                "    \"tid_lørdag\": \""+toilet[6]+"\",\n" +
                "    \"tid_søndag\": \""+toilet[7]+"\",\n" +
                "    \"rullestol\": \""+toilet[8]+"\",\n" +
                "    \"stellerom\": \""+toilet[9]+"\",\n" +
                "    \"dame\": \""+toilet[10]+"\",\n" +
                "    \"herre\": \""+toilet[11]+"\",\n" +
                "    \"pissoir_only\": \""+toilet[12]+"\",\n" +
                "  }\n},";
    }

}