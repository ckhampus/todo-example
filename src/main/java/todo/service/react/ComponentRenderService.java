package todo.service.react;

import org.springframework.stereotype.Service;

@Service
public interface ComponentRenderService {
    /**
     * Renders a React component.
     *
     * @param component The name of the component to render.
     * @param props     The initial data to render the component with.
     * @return The HTML for the rendered component.
     */
    public String render(String component, Object props);
}
