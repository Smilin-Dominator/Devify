# Devify - The Next Generation Verifier!
Every major and/or secure file that's uploaded has a File Hash or Digital Signature along with it. The most common are;
- SHA256 Hashes: sha256.txt
- MD5 Hashes: md5.txt
- GPG Signatures: *.sig

What Devify aims to do is, verify the safety of your files with the SHA256 Hashing Algorithm.

## User Interface

### Hash
- Input Field (JTextField)
  - You can either paste the absolute path to the file or select the button to the right of the entry field
- Select A File (JButton)
  - This will open a File Choosing dialog and as soon as you select, the main functions will appear
- Submit (JButton)
  - Should you choose to paste the path, click this button right afterwards, and the main functions will appear
- Status (JLablel)
  - This displays the status;
    - hash -> Comes after pressing the button below.
    - "Success!" -> Writing Checksum Worked
    - "Error While Writing To File!" -> IOException
    - "There's a checksum file in this directory!" -> AssertionError
- Calculate The Hash (JButton)
  - This calculates the hash and displays it in the status bar
- Write The Checksum (JButton)
  - This calculates the hash and writes the checksum and puts it's status to the status bar
- Checksum File (JTextField)
  - This is the filename of the checksum file to write to (in the same directory).

### Verify
- Input Field (JTextField)
  - You can either paste the absolute path to the file or select the button to the right of the entry field
- Select File (JButton)
  - This will open a File Choosing dialog and as soon as you select, the main functions will appear
- Verify (JButton)
  - Should you choose to paste the path, click this button right afterwards, and the main functions will appear.
- Files (JTree)
  - This Tree shows all the files as expandable items. Click on any of them to see four fields;
    1. Last Hash
    2. New Hash
    3. Equal
  - if the file isn't found, it'll include that int the New Hash field