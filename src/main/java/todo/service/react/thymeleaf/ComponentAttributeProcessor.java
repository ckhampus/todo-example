package todo.service.react.thymeleaf;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractUnescapedTextChildModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import todo.service.react.component.Component;
import todo.service.react.ComponentRenderService;

public class ComponentAttributeProcessor extends AbstractUnescapedTextChildModifierAttrProcessor {

    private final ComponentRenderService renderService;

    protected ComponentAttributeProcessor(ComponentRenderService renderService) {
        super("component");
        this.renderService = renderService;
    }

    @Override
    protected String getText(Arguments arguments, Element element, String attributeName) {
        final String attributeValue = element.getAttributeValue(attributeName);

        final Configuration configuration = arguments.getConfiguration();
        final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration);

        final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments, attributeValue);

        final Component component = (Component) expression.execute(configuration, arguments);

        element.setAttribute("data-react-class", component.getName());
        element.setAttribute("data-react-props", renderService.propsToJSON(component.getProps()));

        return renderService.render(component);
    }

    @Override
    public int getPrecedence() {
        return 1000;
    }

}
