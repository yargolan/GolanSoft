package com.ygsoft.nav;

import java.awt.*;
import javax.swing.*;
import com.ygsoft.nav.enums.GuiEnum;


public class AlPoints {

    private final UiFramesGenerator uiFramesGenerator = new UiFramesGenerator();

    public AlPoints(){}



    public void pointAdd() {

        JFrame fPointAdd = uiFramesGenerator.generate(UiFramesGenerator.FRAME_POINT_ADD);

        // +----------------------
        // | Labels
        // +----------------------
        JLabel lPointId          = new JLabel(GuiEnum.L_POINT_NAME.getText());
        JLabel lPointArea        = new JLabel(GuiEnum.L_POINT_AREA.getText());
        JLabel lPointTenDigits   = new JLabel(GuiEnum.L_POINT_LOCATION.getText());
        JLabel lPointSeparator   = new JLabel(GuiEnum.L_POINT_SEPARATOR.getText());
        JLabel lPointDescription = new JLabel(GuiEnum.L_POINT_DESC.getText());

        lPointId.setBounds         (280, 20,  100, 20);
        lPointArea.setBounds       (280, 60,  100, 20);
        lPointTenDigits.setBounds  (280, 140, 100, 20);
        lPointSeparator.setBounds  (158, 140, 100, 20);
        lPointDescription.setBounds(280, 100, 100, 20);



        // +----------------------
        // | Text fields
        // +----------------------
        JTextField tfPointH    = new JTextField();
        JTextField tfPointW    = new JTextField();
        JTextField tfPointId   = new JTextField();
        JTextField tfPointDesc = new JTextField();

        tfPointId.setEnabled(false);
        tfPointDesc.setRequestFocusEnabled(true);

        tfPointH.setBounds   (50,  140, 100, 20);
        tfPointW.setBounds   (170, 140, 100, 20);
        tfPointId.setBounds  (150, 20,  120, 20);
        tfPointDesc.setBounds(50,  100, 220, 20);


        tfPointId.setText("YG - 1"); // todo: insert pre defined name
        tfPointH.setText (GuiEnum.TF_POINT_H.getText());
        tfPointW.setText (GuiEnum.TF_POINT_W.getText());

        tfPointH.setHorizontalAlignment(SwingConstants.RIGHT);
        tfPointW.setHorizontalAlignment(SwingConstants.RIGHT);



        // +----------------------
        // | Drop downs
        // +----------------------
        JComboBox<String> ddPointArea = UiDropDownsGenerator.generate();
        // todo: insert pre defined names
        ddPointArea.addItem(GuiEnum.DD_NEW.getText());

        ddPointArea.setBounds(115, 60, 150, 20);




        // +----------------------
        // | Buttons
        // +----------------------
        JButton bAdd = new JButton(GuiEnum.B_ADD.getText());

        bAdd.setBounds(130, 210, 90, 30);






        Container container = fPointAdd.getContentPane();
        container.add(lPointId);
        container.add(lPointArea);
        container.add(lPointTenDigits);
        container.add(lPointSeparator);
        container.add(lPointDescription);

        container.add(tfPointH);
        container.add(tfPointW);
        container.add(tfPointId);
        container.add(tfPointDesc);

        container.add(ddPointArea);

        container.add(bAdd);


        fPointAdd.setVisible(true);
    }
}
