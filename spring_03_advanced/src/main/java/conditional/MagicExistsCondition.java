
package conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class MagicExistsCondition implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//获取环境
		Environment env = context.getEnvironment();
		//包含环境属性 magic 则返回true
		return env.containsProperty("magic");
	}

}
