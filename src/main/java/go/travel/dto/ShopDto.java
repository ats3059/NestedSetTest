package go.travel.dto;

import go.travel.domain.Shop;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ShopDto {

    public ShopDto(Shop shop) {
        this.id = shop.getId();
        this.shopRole = shop.getShopRole();
        this.lft = shop.getLft();
        this.rgt = shop.getRgt();
        this.shops = shop.getShops().stream().map(ShopDto::new).collect(Collectors.toList());
        this.depth = shop.getDepth();
    }



    private Integer id;
    private String shopRole;
    private Integer lft;
    private Integer rgt;
    List<ShopDto> shops = new ArrayList<>();
    private Integer depth;

}
