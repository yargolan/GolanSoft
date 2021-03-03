
import sys
from PyQt5.QtWidgets import *



class MainWindow(QMainWindow):

    def __init__(self, *args, **kwargs):
        super(MainWindow, self).__init__(*args, **kwargs)


        self.setWindowTitle('Computer backup')

        # Do not let the user resize the window.
        self.setFixedSize(700, 200)


        layout_profiles = QHBoxLayout()

        # Labels
        lbl_profiles = QLabel("Select profile name :")

        # Drop downs
        dd_profile_name = QComboBox()

        # Buttons
        btn_profile_add = QPushButton("Create a profile")


        # Set sizes
        lbl_profiles.setFixedWidth(125)
        dd_profile_name.setFixedWidth(300)
        btn_profile_add.setFixedWidth(170)


        # Add the widgets
        layout_profiles.addWidget(lbl_profiles)
        layout_profiles.addWidget(dd_profile_name)
        layout_profiles.addWidget(btn_profile_add)





        full_layout = QVBoxLayout()

        full_layout.addLayout(layout_profiles)

        # layout.addWidget(QComboBox())
        # layout.addWidget(QLineEdit("X"))
        # layout.addWidget(QLineEdit("Y"))


        widget = QWidget()
        widget.setLayout(full_layout)
        self.setCentralWidget(widget)


if __name__ == '__main__':
    app = QApplication(sys.argv)

    window = MainWindow()
    window.show()

    # Start the event loop.
    app.exec_()

    print(1234)