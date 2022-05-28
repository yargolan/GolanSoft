import smtplib
from email.mime.text import MIMEText


# Open a plain text file for reading.  For this example, assume that
# the text file contains only ASCII characters.
# text_file = "text.txt"
# with open(text_file, 'rb') as fp:
#     # Create a text/plain message
#     msg = MIMEText(fp.read().)

msg = MIMEText("xxx")
# me == the sender's email address
# you == the recipient's email address
msg['Subject'] = 'The contents of '
msg['From'] = "yargolan@gmail.com"
msg['To'] = "yargolan@gmail.com"

# Send the message via our own SMTP server, but don't include the
# envelope header.
s = smtplib.SMTP('smtp.google.com:465')
s.sendmail(msg['from'], msg['to'], msg.as_string())
s.quit()
