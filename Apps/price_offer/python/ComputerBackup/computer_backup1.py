
import sys

from PyQt5.QtWidgets import *
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QVBoxLayout


class Dialog(QDialog):

    def __init__(self, parent=None):

        super().__init__(parent)

        self.setWindowTitle('Computer backup')

        # Do not let the user resize the window.
        self.setFixedSize(400, 200)


        dlg_layout = QVBoxLayout()
        form_layout = QFormLayout()

        form_layout.addWidget(QComboBox)
        form_layout.addRow('Source dir:', QLineEdit)
        form_layout.addRow('Target dir:', QLineEdit)

        dlg_layout.addLayout(form_layout)

        self.setLayout(dlg_layout)



if __name__ == '__main__':
    app = QApplication(sys.argv)
    dlg = Dialog()
    dlg.show()
    sys.exit(app.exec_())



#
# form_layout.addRow('Age:', QLineEdit())
# form_layout.addRow('Job:', QLineEdit())
# form_layout.addRow('Hobbies:', QLineEdit())
#
# buttons = QDialogButtonBox()
# btn_boo = QPushButton("boo")
# buttons.setStandardButtons(
#     QDialogButtonBox.Cancel | QDialogButtonBox.Ok | btn_boo)
