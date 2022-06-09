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
public class TestRepository {

    private final JPAQueryFactory query;

    public TestRepository(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    public Shop shopTest() {
        return query.selectFrom(shop).where(shop.depth.eq(1)).fetchOne();
    }

    //같은 루트에 해당되는 상위 메뉴들의 노드번호를 증가시킨다.
    public void menuUpdate(Shop menu,Integer id) {
        query.update(shop)
                .set(shop.lft, shop.lft.add(2))
                .where(shop.lft.goe(menu.getLft())
                        .and(shop.rootMenuId.eq(menu.getRootMenuId()))
                        .and(shop.parentMenuId.ne(menu.getParentMenuId()))
                        .and(shop.id.ne(id))
                )
                .execute();
        query.update(shop)
                .set(shop.rgt, shop.rgt.add(2))
                .where(shop.rgt.goe(menu.getRgt())
                        .and(shop.rootMenuId.eq(menu.getRootMenuId()))
                        .and(shop.parentMenuId.ne(menu.getParentMenuId()))
                        .and(shop.id.ne(menu.getParentMenuId().getId()))
                        .or(shop.rootMenuId.isNull())
                )
                .execute();
    }



}
