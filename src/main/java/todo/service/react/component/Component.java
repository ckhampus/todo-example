package todo.service.react.component;

import java.util.HashMap;

public class Component {
    private final String name;
    private final Object props;
    private final String rootNode;

    public Component(String name, Object props) {
        this(name, props, null);
    }

    public Component(String name, Object props, String rootNode) {
        this.name = name;
        this.props = props;
        this.rootNode = rootNode;
    }

    public String getName() {
        return name;
    }

    public Object getProps() {
        if (rootNode != null) {
            return putUnderRootNode();
        }

        return props;
    }

    private Object putUnderRootNode() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(rootNode, props);
        return map;
    }

    public static Component create(String name, Object props) {
        return new Component(name, props);
    }

    public static Component create(String name, Object props, String rootNode) {
        return new Component(name, props, rootNode);
    }
}
