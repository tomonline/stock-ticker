package online.iamtom.stock_ticker.repository;

import online.iamtom.stock_ticker.entity.SampleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDataRepository extends JpaRepository<SampleData, Long> {
}
