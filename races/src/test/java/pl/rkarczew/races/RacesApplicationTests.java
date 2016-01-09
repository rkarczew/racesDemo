package pl.rkarczew.races;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RacesApplication.class)
@WebAppConfiguration
public class RacesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
