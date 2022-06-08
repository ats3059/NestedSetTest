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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static go.travel.domain.QCountry.country;
import static go.travel.domain.QMenu.menu;
import static go.travel.domain.QShop.shop;

@SpringBootTest
@Transactional
public class TravelTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void firstTest() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        Country ko = new Country("123", "ko");
        em.persist(ko);

        List<Country> fetch = query.selectFrom(country).fetch();
        fetch.stream().forEach((inn) -> System.out.println(inn.getCountryId()));
    }

    @Test
    void menuTest() throws JsonProcessingException {
        Menu menu0 = new Menu("root");
        Menu menu1 = new Menu(menu0, "rootChild1");
        Menu menu2 = new Menu(menu0, "rootChild2");
        em.persist(menu0);
        em.persist(menu1);
        em.persist(menu2);

        JPAQueryFactory query = new JPAQueryFactory(em);
        query.select(menu.menuId, menu.content).from(menu).join(menu.parent).fetchJoin().fetch();

//        System.out.println(objectMapper.writeValueAsString(fetch));

    }


    @Test
    void 계층형_쿼리_테스트() throws JsonProcessingException {
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Shop> depth1 = query.selectFrom(shop).where(shop.depth.eq(1)).fetch();
        ShopDto shopDto = new ShopDto(depth1.get(0));
        String str = objectMapper.writeValueAsString(shopDto);
        System.out.println(str);

//        List<Shop> shops = depth1.get(0).getShops();
//        for (Shop shop1 : shops) {
//            System.out.println(shop1.getShopRole());
//            for (Shop shop1Shop : shop1.getShops()) {
//                System.out.println(shop1Shop.getShopRole());
//            }
//        }


//        for (Shop fetch1 : fetch) {
//
//        }
//        System.out.println(fetch.getShops().size());
        /*fetch.getShops().forEach((in) -> {
            System.out.println("depth2 : " + in.getShopRole());
            in.getShops().stream().forEach((inn) -> System.out.println("depth3 : " + inn.getShopRole()));
        });*/

//        List<ShopDto> collect = fetch.stream().map((ShopDto::new)).collect(Collectors.toList());
//        String str = objectMapper.writeValueAsString(collect);
//        System.out.println(str);


    }

}
