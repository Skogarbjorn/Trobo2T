package view;

public enum View {
    MAIN("primary.fxml"),
    LOGIN("login-view.fxml");

    private String view;

    View(String view) {
        this.view = view;
    }

    String getName() {
        return view;
    }
}
