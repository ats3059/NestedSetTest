package go.travel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country {

    public Country(String countryStatus, String countryName) {
        this.countryStatus = countryStatus;
        this.countryName = countryName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;
    private String countryStatus;
    private String countryName;

//
//    @OneToMany(mappedBy = "country")
//    private List<Travel> travels = new ArrayList<>();


}
