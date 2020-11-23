package scope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import scope.impls.Notepad;
import scope.impls.UniqueThing;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Config.class)
public class UniqueTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testProxyScope() {
        Notepad notepad1 = context.getBean(Notepad.class);
        Notepad notepad2 = context.getBean(Notepad.class);
        assertNotSame(notepad1, notepad2);
    }

    @Test
    public void testSingletonScope() {
        UniqueThing thing1 = context.getBean(UniqueThing.class);
        UniqueThing thing2 = context.getBean(UniqueThing.class);
        assertSame(thing1, thing2);
    }


}
