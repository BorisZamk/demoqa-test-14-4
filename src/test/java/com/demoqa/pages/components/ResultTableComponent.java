package com.demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    private final static String MODAL_TITLE = "Thanks for submitting the form";
    private SelenideElement
            modalDialogOutput = $(".modal-dialog"),
            modalTitle = $("#example-modal-sizes-title-lg"),
            resultTableOutput = $(".table-responsive table");

    public ResultTableComponent checkVisibility() {
        modalDialogOutput.shouldBe(visible);
        modalTitle.shouldHave(text(MODAL_TITLE));

        return this;
    }

    public ResultTableComponent checkResult(String key, String value){
        resultTableOutput.$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

}
