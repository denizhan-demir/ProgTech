package hu.nye.progtech;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

import java.util.Map;

public class HelloWorld {

    private static final String DEFAULT_LANG = "hun";

    private static final Map<String, String> GREETINGS = Map.of(
            "hun", "Szia",
            "eng", "Hello",
            "spa", "Hola",
            "tur", "Selam",
            "ger", "Hallo"
    );

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: name is required. Usage: java -jar hello-world.jar <name> [hun|eng|hin|tur]");
            System.exit(1);
        }

        String name = args[0];
        String lang = args.length > 1 ? args[1].toLowerCase() : DEFAULT_LANG;

        String greeting = GREETINGS.get(lang);
        if (greeting == null) {
            System.err.println("Error: unknown language: " + lang);
            System.exit(1);
        }

        System.out.println(render(greeting + ", " + name + "!"));
    }

    private static String render(String message) {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(message.length() * 11).height(12);
        builder.element(new PseudoText(message));
        ICanvas canvas = render.render(builder.build());
        return canvas.getText();
    }
}