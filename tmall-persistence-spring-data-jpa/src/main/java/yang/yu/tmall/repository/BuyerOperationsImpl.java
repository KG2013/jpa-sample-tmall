package yang.yu.tmall.repository;

import yang.yu.tmall.domain.Buyer;
import yang.yu.tmall.domain.ImType;
import yang.yu.tmall.domain.PersonalBuyer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.Optional;

@Named
public class BuyerOperationsImpl implements BuyerOperations {

    private final EntityManager entityManager;

    public BuyerOperationsImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PersonalBuyer> findPersonalBuyerByQQ(String qq) {
        String jpql = "select o from PersonalBuyer o join o.imInfos i where KEY(i) = :key and VALUE(i) = :value";
        return entityManager.createQuery(jpql, PersonalBuyer.class)
                .setParameter("key", ImType.QQ)
                .setParameter("value", qq)
                .getResultStream()
                .findAny();
    }
}
