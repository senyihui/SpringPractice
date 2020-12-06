package orders.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "orders.db")//启用Repository自动生成功能
//@PropertySource("classpath:mongodb.properties")
public class MongoConfig extends AbstractMongoConfiguration {
	
//	@Autowired
//	private Environment env;

	@Override
	protected String getDatabaseName() {
		return "OrdersDB";
	}
	
	//默认端口27017
//	@Override
//	public Mongo mongo() throws Exception {
//		return new MongoClient();
		//创建MongoDB凭证
//		MongoCredential credential = 
//				MongoCredential.createMongoCRCredential(
//						env.getProperty("mongo.username"), 
//						"OrdersDB", 
//						env.getProperty("mongo.password").toCharArray());
//		//创建MongoClient，
//		return new MongoClient(new ServerAddress("localhost", 37017), 
//				Arrays.asList(credential));
//	}

	public MongoClient mongoClient() {
		return new MongoClient();
	}

	/*
	 * 继承AbstractMongoConfiguration后，将会隐式地创建MongoTemplate bean
	 * 因为mongo()会创建MongoClient实例并抛出Exception，故可不使用MongoFactoryBean
	 */
	//不必担心MongoClient的UnknownHostException
//	@Bean
//	public MongoFactoryBean mongo() {
//		MongoFactoryBean mongo = new MongoFactoryBean();
//		mongo.setHost("localhost");
//		return mongo;
//	}
//
//	@Bean
//	public MongoOperations mongoTemplate(Mongo mongo) {
//		return new MongoTemplate(mongo, "OrdersDB");
//	}

}