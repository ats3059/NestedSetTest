package go.travel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import go.travel.domain.Country;
import go.travel.domain.Menu;
import go.travel.domain.QShop;
import go.travel.domain.Shop;
import go.travel.dto.ShopDto;
import go.travel.repository.MemberRepository;
import go.travel.repository.TestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static go.travel.domain.QCountry.country;
import static go.travel.domain.QMenu.menu;
import static go.travel.domain.QShop.shop;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class TravelTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TestRepository testRepository;
    JPAQueryFactory query = new JPAQueryFactory(em);

    @BeforeEach
    void init() {
        query = new JPAQueryFactory(em);
    }


//    @Test
//    void firstTest() {
//
//        Country ko = new Country("123", "ko");
//        em.persist(ko);
//
//        List<Country> fetch = query.selectFrom(country).fetch();
//        fetch.stream().forEach((inn) -> System.out.println(inn.getCountryId()));
//    }
//
//    @Test
//    void menuTest() throws JsonProcessingException {
//        Menu menu0 = new Menu("root");
//        Menu menu1 = new Menu(menu0, "rootChild1");
//        Menu menu2 = new Menu(menu0, "rootChild2");
//        em.persist(menu0);
//        em.persist(menu1);
//        em.persist(menu2);
//
//        query.select(menu.menuId, menu.content).from(menu).join(menu.parent).fetchJoin().fetch();
//
////        System.out.println(objectMapper.writeValueAsString(fetch));
//
//    }


    @Test
    void 계층형_쿼리_테스트() throws JsonProcessingException {
        Shop depth1 = new Shop("여행", 1, 2, 1);

        //2번째 계층
        Shop depth2_1 = new Shop(depth1, "국내 여행");
        Shop depth2_2 = new Shop(depth1, "해외 여행");


        depth1.addShop(depth2_1);
        depth1.addShop(depth2_2);

        em.persist(depth1);
    }


    @Test
    void 계층형_쿼리_테스트2() {
        Shop depth2_1 = query.selectFrom(shop).where(shop.id.eq(2)).fetchOne();

        //3번째 계층
        Shop depth3_1 = new Shop(depth2_1,"서울");
        Shop depth3_2 = new Shop(depth2_1,"여수");

        depth2_1.addShop(depth3_1);
        depth2_1.addShop(depth3_2);
        //벌크성 업데이트 -> 영속성 컨텍스트와 싱크가 안맞게된다 clear 필수.
        testRepository.menuUpdate(depth3_1,depth2_1.getId());
        testRepository.menuUpdate(depth3_2,depth2_1.getId());
        em.persist(depth2_1);



        Shop depth2_2 = query.selectFrom(shop).where(shop.id.eq(3)).fetchOne();
        Shop depth3_3 = new Shop(depth2_2,"미국");
        Shop depth3_4 = new Shop(depth2_2,"프랑스");
        depth2_2.addShop(depth3_3);
        depth2_2.addShop(depth3_4);
        testRepository.menuUpdate(depth3_3,depth2_2.getId());
        testRepository.menuUpdate(depth3_4,depth2_2.getId());
        em.persist(depth2_2);

    }


}
