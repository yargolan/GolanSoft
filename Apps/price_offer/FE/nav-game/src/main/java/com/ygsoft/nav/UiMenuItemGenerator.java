package com.ygsoft.nav;

import javax.swing.*;


class UiMenuItemGenerator {

    static JMenuItem generate() {
        return generate("");
    }


    static JMenuItem generate(String text) {

        JMenuItem jMenuItem = new JMenuItem(text);

        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.RIGHT);
        jMenuItem.setHorizontalAlignment(SwingConstants.RIGHT);

        return jMenuItem;
    }
}



