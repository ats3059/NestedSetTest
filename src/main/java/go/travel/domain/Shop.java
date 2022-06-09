package go.travel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","shopRole","lft","rgt","depth"})
public class Shop {

    public Shop(String shopRole, Integer lft, Integer rgt, Integer depth) {
        this.shopRole = shopRole;
        this.lft = lft;
        this.rgt = rgt;
        this.depth = depth;
    }

    public Shop(Shop parent, String shopRole) {
        this.shopRole = shopRole;
        this.lft = parent.getRgt();
        this.rgt = parent.getRgt() + 1;
        this.depth = parent.getDepth() +1;
        this.rootMenuId = parent.depth == 1 ? parent : parent.rootMenuId;
        parent.addRgt(2);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Integer id;

    @Column(name = "shop_role")
    private String shopRole;

    private Integer lft;
    private Integer rgt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_menu_id")
    private Shop rootMenuId;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "parent_menu_id")
    private Shop parentMenuId;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "parentMenuId" ,cascade = CascadeType.PERSIST)
    private List<Shop> shops = new ArrayList<>();


    private Integer depth;


    public void addShop(Shop shop) {
        shop.addParentMenu(this);
        shops.add(shop);
    }

    public void addRgt(Integer rtg) {
        this.rgt += rtg;
    }

    public Shop addParentMenu(Shop parent) {
        return this.parentMenuId = parent;
    }
}
