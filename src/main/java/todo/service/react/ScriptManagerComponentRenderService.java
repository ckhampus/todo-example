package todo.service.react;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Service
public class ScriptManagerComponentRenderService implements ComponentRenderService {

    private final ScriptEngine scriptManager;
    private final ObjectMapper objectMapper;

    public ScriptManagerComponentRenderService() {
        this.scriptManager = (new ScriptEngineManager(null)).getEngineByName("nashorn");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String render(String component, Object props) {
        String script = createRunnerScript(component, props);

        return execute(script);
    }

    private String createRunnerScript(String component, Object props) {
        return null;
    }

    private String execute(String script) {
        return null;
    }
}
