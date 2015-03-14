package todo.service.react;

import org.springframework.stereotype.Service;
import todo.service.react.component.Component;

@Service
public interface ComponentRenderService {
    public String propsToJSON(Object props);

    /**
     * Renders a React component.
     *
     * @param component The component to render.
     * @return The HTML for the rendered component.
     */
    public String render(Component component);
}
