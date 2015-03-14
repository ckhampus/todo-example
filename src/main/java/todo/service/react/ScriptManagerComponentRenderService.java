package todo.service.react;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import todo.service.react.component.Component;

import javax.script.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

@Service
public class ScriptManagerComponentRenderService implements ComponentRenderService {

    private final ScriptEngine engine;
    private final ObjectMapper mapper;

    public ScriptManagerComponentRenderService() {
        this.engine = (new ScriptEngineManager(null)).getEngineByName("nashorn");

        try {
            this.engine.eval("var global = global || this");
            this.engine.eval("var self = self || this;");
            this.engine.eval("var window = window || this;");
            this.engine.eval(loadScript("static/bundle.common.js"));
            this.engine.eval(loadScript("static/bundle.server.js"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mapper = new ObjectMapper();
    }

    @Override
    public String render(Component component) {
        try {
            return execute(component);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public String propsToJSON(Object props) {
        try {
            return mapper.writeValueAsString(props);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private FileReader loadScript(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return new FileReader(resource.getFile());
    }

    private String execute(Component component) throws IOException, NoSuchMethodException {
        try {
            String name = component.getName();
            String props = propsToJSON(component.getProps());

            StringWriter writer = new StringWriter();
            Bindings bindings = new SimpleBindings();
            bindings.put("writer", writer);

            this.engine.getContext().setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
            this.engine.eval(String.format("renderComponent('%1$s', %2$s, writer)", name, props));

            return writer.toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return "";
    }
}
