package lab5;

import lombok.Getter;

@Getter
public class Bulletin {
    private final String[] possibleAnswer;

    private final String fullQuestion;

    public Bulletin() {
        this.possibleAnswer = new String[]{"Да", "Нет", "Воздержаться"};
        this.fullQuestion = "Вы что нибудь поддерживаете?";
    }
}
