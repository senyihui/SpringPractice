import config.EncoreableConfig;
import interfaces.Encoreable;
import interfaces.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= EncoreableConfig.class)
public class EncoreableTest {

	@Autowired
	private Performance performance;
	
	@Test
	public void test() {
		performance.perform();
		//由于performance引入了Encoreable接口（指定了实现类），所以能强转为该接口
		Encoreable encoreable = (Encoreable) performance;
		encoreable.performEncore();
	}

}
