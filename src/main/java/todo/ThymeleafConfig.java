package todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.templateresolver.ITemplateResolver;
import todo.service.react.ComponentRenderService;
import todo.service.react.thymeleaf.ReactDialect;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ThymeleafConfig {
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, ReactDialect reactDialect) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        final Set<IDialect> dialects = new HashSet<>();
        dialects.add(reactDialect);

        templateEngine.setAdditionalDialects(dialects);
        return templateEngine;
    }

    @Bean
    public ReactDialect reactDialect(ComponentRenderService renderService) {
        return new ReactDialect(renderService);
    }
}
