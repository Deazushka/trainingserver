package by.training.echoserver;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

public class Message {
    private String name;
    private String message;

    public Message() {
    }

    public Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public Message(String source) throws Exception {
        ScriptEngine engine;
        ScriptEngineManager sem = new ScriptEngineManager();
        engine = sem.getEngineByName("javascript");
        String script = "Java.asJSONCompatible(" + source + ")";
        Object result = engine.eval(script);

        Map<String, String> contents = (Map) result;
        this.name = contents.get("name");
        this.message = contents.get("message");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
