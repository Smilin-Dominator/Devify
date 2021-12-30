# Devify - The Next Generation Verifier!
Every major and/or secure file that's uploaded has a File Hash or Digital Signature along with it. The most common are;
- SHA256 Hashes: sha256.txt
- GPG Signatures: *.sig

What Devify aims to do is, verify the safety of your files with the SHA256 Hashing Algorithm.

## User Interface
- The File Viewer (North)
  - Select a file and either Double-Click it or press 'Open' to select it.
- The Actions (South)
  - Status Bar (Top)
    - This will display all messages sent by the below functions
  - Generate Hash (First)
    - This will generate the Hash and display it in the Status Bar.
  - Generate Checksum (Second)
    - This will generate the Hash and write it to 'sha256.txt' in the same directory.
    - It's written in the global format;
      - "(hash)  (filename)"
  - Verify Checksum (Third)
    - This will check the 'sha256.txt' file for the filename;
      - If it's the same; it'll verify if the hashes are the same and display "File Is The Same!" if the hashes are
        equal and "File Is Not The Same!" if they aren't.
      - If it's not the same; it'll say 'This Is Not The File That's Hashed!'