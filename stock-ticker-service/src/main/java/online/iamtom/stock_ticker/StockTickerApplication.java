package online.iamtom.stock_ticker;

import online.iamtom.stock_ticker.entity.SampleData;
import online.iamtom.stock_ticker.repository.SampleDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StockTickerApplication implements CommandLineRunner {

	private SampleDataRepository sampleDataRepository;

	StockTickerApplication(SampleDataRepository sampleDataRepository) {
		this.sampleDataRepository = sampleDataRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockTickerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SampleData sample = SampleData.builder()
				.sampleDataName("Sample Data")
				.updatedTs(LocalDateTime.now())
				.build();

		sampleDataRepository.save(sample);
	}
}
