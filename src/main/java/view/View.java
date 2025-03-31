package view;

public enum View {
    MAIN("main-view.fxml"),
    LOGIN("album-view.fxml");

    private String view;

    View(String view) {
        this.view = view;
    }

    String getName() {
        return view;
    }
}
