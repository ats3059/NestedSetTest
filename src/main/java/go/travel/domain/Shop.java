package go.travel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","shopRole","lft","rgt","depth"})
public class Shop {


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_menu_id")
    private Shop parentMenuId;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "parentMenuId")
    private List<Shop> shops = new ArrayList<>();

    private Integer depth;


}
