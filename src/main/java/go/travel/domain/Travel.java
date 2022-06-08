package go.travel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Integer travelId;
    private String travelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    private LocalDateTime lastModifiedDate;
    private LocalDateTime lastModifiedName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "travel")
    private List<Plan> plans = new ArrayList<>();


}
