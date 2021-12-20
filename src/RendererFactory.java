
public class RendererFactory {

    Renderer buildRenderer(String arg) {
        switch (arg) {
            case "console":
                return new ConsoleRenderer();
            case "none":
                return new VoidRenderer();
            default:
                return null;
        }
    }
}
