package li.yifei4.datas.dao;

import li.yifei4.datas.entity.DigitalMarket;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository("digitalMarketDao")
public class DigitalMarketDao extends EntityDao<DigitalMarket>{
    public DigitalMarket find(String marketPlc) {
        try {
            MyCriteriaBuilder<DigitalMarket> builder = this.getCriteriaBuilder();
            builder.where(builder.predicate("equal", builder.get("name"), marketPlc));
            return builder.createExecutiveQuery().getSingleResult();
        }catch(Exception e) {
            RuntimeException re = new RuntimeException();
            re.setStackTrace(e.getStackTrace());
            throw re;
        }
    }
    public List<DigitalMarket> getAllSupportedMarket(){
        MyCriteriaBuilder<DigitalMarket> builder = this.getCriteriaBuilder();
        return builder.createExecutiveQuery().getResultList();
    }
}
