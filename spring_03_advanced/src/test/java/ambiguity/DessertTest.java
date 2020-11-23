package ambiguity;

import ambiguity.config.ImplConfig1;
import ambiguity.config.ImplConfig2;
import ambiguity.config.ImplConfig3;
import ambiguity.config.ImplConfig4;
import ambiguity.impl4.Cold;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class DessertTest {

    /**
     * desription: 多个Dessert的实现类，将抛出NoUniqueBeanDefinitionException
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes= ImplConfig1.class)
    public static class Impl1Test {

        private Dessert dessert;

        @Test
        public void test() {
            dessert.sweet();
        }
    }

    /**
     * desription: @Primary Bean 的测试
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes=ImplConfig2.class)
    public static class Impl2Test {
        @Autowired
        private Dessert dessert;

        @Test
        public void testPrimaryBean() {
            /*
             * 当只有一个@Primary修饰Dessert的实现类，则成功
             * 多个则抛出将抛出NoUniqueBeanDefinitionException
             */
            dessert.sweet();
        }
    }

    /**
     * desription: @Qualifier Bean 的测试
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes= ImplConfig3.class)
    public static class Impl3Test {
        @Autowired
        @Qualifier("cake")
        private Dessert dessert;

        @Test
        public void testQualifierBean() {
            /*
             * 当@Qualifier的限定符在Dessert的实现类中唯一时，则成功
             * 多个则抛出将抛出NoUniqueBeanDefinitionException
             */
            dessert.sweet();
        }
    }

    /**
     * desription: 自定义@Qualifier Bean 的测试
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes= ImplConfig4.class)
    public static class Impl4Test {
        @Autowired
        @Cold
        private Dessert dessert1;

        @Test
        public void testQualifierBean() {
            dessert1.sweet();
        }
    }
}
