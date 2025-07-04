package online.iamtom.stock_ticker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sampleDataId;
    private String sampleDataName;
    private LocalDateTime updatedTs;

}
