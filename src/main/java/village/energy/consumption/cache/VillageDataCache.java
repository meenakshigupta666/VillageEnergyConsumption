/**
 * Village Data Cache(villageeneryconsumption)
 */
package village.energy.consumption.cache;

import com.google.gson.Gson;
import org.springframework.beans.factory.InitializingBean;
import village.energy.consumption.dto.Village;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author meenakshi.gupta
 * @version $Id: VillageDataCache.java, v 0.1 2020-02-21 15:35 meenakshi.gupta Exp $$
 */
public class VillageDataCache implements InitializingBean {

    private static final Logger logger = Logger.getLogger("VillageDataCache");

    private Map<String, Village> villageData = new HashMap<String, Village>();

    public Map<String, Village> getVillageData() {
        return villageData;
    }

    private void setVillageData(Map<String, Village> villageData) {
        this.villageData = villageData;
    }

    public void refreshCache() {
        logger.info("Loading village Data from the endpoint--");
        String stringResponse = "";
        try {
            URL url = new URL("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                stringResponse += sc.nextLine();
            }
            sc.close();
            conn.disconnect();
            logger.info("Loading village Data Ends--");
        } catch (MalformedURLException e) {
            logger.info("URL Malformed");
        } catch (IOException e) {
            logger.info("Some IO Exception Occurred");
        }
        Gson g = new Gson();
        VillageDataCache response = g.fromJson(stringResponse, VillageDataCache.class);
        this.setVillageData(response.getVillageData());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshCache();
    }
}