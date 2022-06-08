package go.travel.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import go.travel.domain.QShop;
import go.travel.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import static go.travel.domain.QShop.shop;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public Shop shopTest() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        return query.selectFrom(shop).where(shop.depth.eq(1)).fetchOne();
    }




}
