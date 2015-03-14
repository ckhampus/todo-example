package todo.service.react.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;
import todo.service.react.ComponentRenderService;

import java.util.HashSet;
import java.util.Set;

public class ReactDialect extends AbstractDialect {
    private final ComponentRenderService renderService;

    public ReactDialect(ComponentRenderService renderService) {
        this.renderService = renderService;
    }

    @Override
    public String getPrefix() {
        return "react";
    }

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new ComponentAttributeProcessor(renderService));
        return processors;
    }
}
