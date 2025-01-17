
package conditional;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MagicConfig.class)
public class MagicExistsTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired(required=false)
	private MagicBean magicBean;

	@Test
	public void shouldNotNull() {
		assertTrue(context.containsBean("magicBean"));
		assertNotNull(magicBean);
	}

}
