package org.koko;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.koko.rxb2f.RxB2fApp;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RxB2fApp.class)
@WebAppConfiguration
public class RxB2fApplicationTests {

	@Test
	public void contextLoads() {
	}

}
