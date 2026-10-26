package dev.sio.todoalt.ui;

import lombok.*;

@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UiMessage {
    @NonNull
    private String title;
    @NonNull
    private String content;

    private UiMessageType type=UiMessageType.INFO;
}
