package go.travel.domain;

import go.travel.domain.code.Vehicle;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class PlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_detail_id")
    private Integer planDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    private String cost;
    private String memo;

    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;
    private LocalTime time;

}
