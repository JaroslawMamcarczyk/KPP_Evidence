package pl.kpp.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import pl.kpp.dao.workersDao.RangeDao;
import java.util.ArrayList;
import java.util.List;

public class Range {

    private StringProperty rangeName = new SimpleStringProperty();
    private ImageView pagons = null;
    private String path = null;
    private int id;

    public String getPath() {
        return path;
    }

    private static List<Range> listRange = new ArrayList<>();

    public static List<Range> getListRange() {
        listRange.clear();
        for(RangeDao rangeDao:RangeDao.getRangeDaoList()){
           listRange.add(new Range(rangeDao));
        }
        return listRange;
    }
    public int getId(){
        return id;
    }
    public String getRangeName() {
        return rangeName.get();
    }

    public ImageView getPagons() {
        return pagons;
    }

    /**
     * Constructor
     * @param dao object reading from database
     */
    public  Range(RangeDao dao){
        this.rangeName.set(dao.getRangeName());
        this.path = dao.getPath();
        this.pagons = new ImageView(this.path);
        this.id = dao.getId();
    }

    /**
     * Searching range
     * @param index index search range
     * @return finding range
     */
    public static Range searchRange(int index){
        Range rang =null;
        for(Range range: listRange){
            if (range.getId() == index) rang = range;
        }
        return rang;
    }
}
