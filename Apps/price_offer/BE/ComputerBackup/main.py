
import sys

from PyQt5.QtWidgets import QApplication, QPushButton
from PyQt5.QtWidgets import QDialog
from PyQt5.QtWidgets import QDialogButtonBox
from PyQt5.QtWidgets import QFormLayout
from PyQt5.QtWidgets import QLineEdit
from PyQt5.QtWidgets import QVBoxLayout


class Dialog(QDialog):
    def __init__(self, parent=None):
        """Initializer."""
        super().__init__(parent)
        self.setWindowTitle('QDialog')
        dlg_layout = QVBoxLayout()
        form_layout = QFormLayout()
        form_layout.addRow('Name:', QLineEdit())
        form_layout.addRow('Age:', QLineEdit())
        form_layout.addRow('Job:', QLineEdit())
        form_layout.addRow('Hobbies:', QLineEdit())
        dlg_layout.addLayout(form_layout)
        buttons = QDialogButtonBox()
        btn_boo = QPushButton("boo")
        buttons.setStandardButtons(
            QDialogButtonBox.Cancel | QDialogButtonBox.Ok | btn_boo)
        dlg_layout.addWidget(buttons)
        self.setLayout(dlg_layout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    dlg = Dialog()
    dlg.show()
    sys.exit(app.exec_())
