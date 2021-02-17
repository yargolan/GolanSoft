package com.ygsoft.nav;

import javax.swing.*;


class UiDropDownsGenerator {

    static JComboBox<String> generate() {

        JComboBox<String> dropDown = new JComboBox<>();
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.RIGHT);
        dropDown.setRenderer(listRenderer);

        return dropDown;
    }
}




