package com.github.kirsirinnesalo.katas.fizzbuzzgame;

public class GameRules {
    protected enum RULES {
        ORIGINAL(1, "Original"),
        POP(2, "Pop");

        private final int selectionValue;
        private String description;

        RULES(int selectionValue, String description) {
            this.selectionValue = selectionValue;
            this.description = description;
        }

        public String selectionValue() {
            return Integer.toString(selectionValue);
        }

        public static RULES getTypeForSelection(int selection) {
            for (RULES type : values()) {
                if (type.selectionValue == selection) {
                    return type;
                }
            }
            return ORIGINAL;
        }

        public String description() {
            return description;
        }
    }

    private Rules rules;

    public void useRules(RULES rules) {
        switch (rules) {
            case ORIGINAL: this.rules = new OriginalRules(); break;
            case POP: this.rules = new PopRules(); break;
            default: this.rules = new OriginalRules();
        }
    }

    public String answerFor(int number) {
        return rules.answerFor(number);
    }

}
