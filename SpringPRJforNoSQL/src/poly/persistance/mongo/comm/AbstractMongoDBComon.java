package poly.persistance.mongo.comm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractMongoDBComon {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongodb;

    protected boolean createCollection(String colNm) throws Exception {
        return createCollection(colNm, "");
    }

    protected boolean createCollection(String colNm, String index) throws Exception {
        String[] indexArr = {index};
        return createCollection(colNm, indexArr);
    }

    protected boolean createCollection(String colNm, String[] index) throws Exception {

        log.info(this.getClass().getName() + ".createCollection start");

        boolean res = false;


        log.info(this.getClass().getName() + ".createCollection end");
        return res;
    }
}
