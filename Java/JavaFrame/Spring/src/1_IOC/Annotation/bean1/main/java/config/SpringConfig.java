package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MrLi on 2022/02/11/16:56
 */

//作为配置类，替代xml配置文件
@Configuration
@ComponentScan(basePackages = {"java"})
public class SpringConfig {
}
