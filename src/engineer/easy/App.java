package engineer.easy;

import engineer.easy.dev.ui.DuPage;
import engineer.easy.internal.Page;

public class App {
    public static void main(String[] args) {
        Page mainPage = new DuPage();
        mainPage.open();
    }
}
