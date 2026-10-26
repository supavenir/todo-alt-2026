package dev.sio.todoalt.ui;

public enum UiMessageType {
    INFO("info"),
    SUCCESS("success"),
    WARNING("warning"),
    ERROR("danger");

    private String label;

    UiMessageType(String label){
        this.label=label;
    }

    public String getLabel() {
        return label;
    }
}
