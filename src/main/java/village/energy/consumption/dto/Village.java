/**
 * Village(villageeneryconsumption)
 */
package village.energy.consumption.dto;

/**
 * @author meenakshi.gupta
 * @version $Id: Village.java, v 0.1 2020-02-20 21:14 meenakshi.gupta Exp $$
 */
public class Village {

    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setVillageName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}