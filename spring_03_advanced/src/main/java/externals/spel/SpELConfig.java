
package externals.spel;

 import externals.BlankDisc;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.beans.factory.config.PropertiesFactoryBean;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 import java.io.IOException;
 import java.util.Properties;


/**
 * Description: 使用SpEL的配置
 */
@Configuration
public class SpELConfig {

	@Bean
	public BlankDisc blankDisc() {
		return new BlankDisc("学不会", "林俊杰");
	}
	
	/**
	* <p>描述：使用SpEL引用beanId为blankDisc的bean并调用其方法</p>
	 */
	@Bean
	@Qualifier("blankDiscSpEL1")
	public BlankDiscSpEL blankDiscSpEL1(@Value("#{blankDisc.getTitle()}")String title,
										@Value("#{blankDisc.getArtist()}")String artist) {
		return new BlankDiscSpEL(title, artist);
	}
	
	/**
	 * <p>描述：使用SpEL读取属性文件内容</p>
	 */
	@Bean
	@Qualifier("blankDiscSpEL2")
	public BlankDiscSpEL blankDiscSpEL2(@Value("#{prop['disc.title']}")String title,
										@Value("#{prop['disc.artist']}")String artist) {
		return new BlankDiscSpEL(title, artist);
	}

	/**
	 * <p>描述：定义bean并加装一个自定义属性文件</p>
	 */
	@Bean("prop")
	public Properties properties() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("externals/app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * <p>描述：定义bean并加装多个自定义属性文件</p>
	 */
	@Bean("props")
	public PropertiesFactoryBean propertiesFactoryBean(@Qualifier("prop")Properties properties) {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		//加载一个属性文件
		factoryBean.setProperties(properties);
		
		/*
		 * 用来加装多个属性文件，其中属性名称相同的，后加载的会覆盖之前加载的
		 */
//		Properties properties1 = new Properties();
//		Properties properties2 = new Properties();
//		try {
//			properties1.load(this.getClass().getClassLoader().getResourceAsStream("externals/app.properties"));
//			properties2.load(this.getClass().getClassLoader().getResourceAsStream("externals.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		factoryBean.setPropertiesArray(properties1, properties2);
		return factoryBean;
	}
	
	/**
	 * <p>描述：使用SpEL读取系统属性。systemProperties是预定义的bean的id，等同于System.getProperties()</p>
	 */
	@Bean
	public SystemPropertiesBean systemPropertiesBean(
			@Value("#{systemProperties['java.version']}")String javaVersion,
			@Value("#{systemProperties['java.home']}")String javaHome) {
		return new SystemPropertiesBean(javaVersion, javaHome);
	}
}
