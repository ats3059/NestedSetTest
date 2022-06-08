package go.travel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    public Menu(String content) {
        this.content = content;
    }

    public Menu(Menu parent, String content) {
        this.parent = parent;
        this.content = content;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menuId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private List<Menu> child  = new ArrayList<>();






}
