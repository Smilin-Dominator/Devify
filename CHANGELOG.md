# Changelog
Pretty much everything I change will be here, I suppose.

## [v1.0] - 30th December, 2021
### Notes
This is the first version of the program, with a functioning User Interface and a few
good backend functions!
### Features
- Minimalistic UI
  - Has a File Searcher and a menu that shows up only when a file is selected
- Backend functions
  - Hashing, Verifying and other common functions are among them.
### Fixed
  - The Checksum Function, which couldn't get the path correctly. (085d484)


## [v1.0.1] - 30th December, 2021
### Notes
I added this very document and a README file with instructions on how to use it.
### Features
- New README File
- New CHANGELOG File (this)
### Fixed
- None

## [v1.1] - 30th December, 2021
### Notes
This release adds colours to the buttons
### Features
- All the items in the action bar are coloured
### Fixed
- None

## [v1.1.1] - 30th December, 2021
### Notes
This release added a test for Checksums as well as organizing the tests
### Features
- One new test
### Fixed
- None

## [v1.1.2] - 30th December, 2021
### Notes
This release added JavaDoc / API Docs for all the classes and their functions
### Features
- JavaDoc for all!
### Fixed
- None

## [v1.1.3] - 31st December, 2021
### Notes
This release was a code cleaning and maintenance release 
### Features
- Made the verify class useful
### Fixed
- Issue of the filename not being detected in the absolute path (42376de)

## [v1.1.4] - 31st December, 2021
### Notes
This release moved all the packages to 'com.smilin_dominator.devify.*'
### Features
- Package Names are now under 'com.smilin_dominator.devify'
- Made all the entries in the 'Fixed' field in this Changelog have the SHA of the Commit for reference
### Fixed
- None

## [v1.1.5] - 31st December, 2021
### Notes
This release added test classes for Verify and Common
### Features
- New test classes!
### Fixed
- None

## [v1.2] - 1st January, 2022
### Notes
This release added the ability to read a checksum file with a filename of your choice, instead of the default 'sha256.txt'.
### Features
- New text field at the very bottom, allowing you to specify the filename of the checksum file.
### Fixed
- The test class for Verify (9b237e8)
- The test class for Hashing (8054858)

## [v1.2.1] - 13th January, 2022
### Notes
I changed the license to a GNU AGPL v3 License. 
### Features
- Copyright String On The Top Of All Source Code
### Fixed
- None

## [v1.2.2] - 14th January, 2022
### Notes
I added a class that can return Fonts and ImageIcons from files that're in the Jar File
### Features
- New 'resources' class in the backend
  - font -> Returns a Font object
  - image -> Returns an ImageIcon object
- Gradle 'run' Task
  - Now you can type 'gradle run' and it'll launch the class; As well as 'gradle runShadow' which uses shadowJar
### Fixed
- None

## [v1.3] - 9th February, 2022
### Notes
I redesigned the main user interface by splitting the user interface into three;
- The Selection Screen
- Hashing
- Verifying
And unlike earlier, there's support for multi-file verifying!
### Features
- New user interface with similarities to the original versions
- Multi-File Verifying Support (from the same checksum file)
### Fixed
- None

## [v1.3.1] - 9th February, 2022
### Notes
I added the logo (designed by Manish) to all the UIs in the Frontend.
### Features
- Logo in all the UIs
### Fixed
- None

## [v1.3.2] - 10th February, 2022 
### Notes 
I added an 'Operations' Class to the program. This essentially wraps the functions
in `com.smilin_dominator.devify.backend.verify` and `com.smilin_dominator.devify.backend.hash` with Threads.
This ensures that the app runs smoothly and doesn't freeze when hashing large files.
### Features
- Threading Support with new class Operations
- Made the Verify Tree a Scrollable Element
- Redesigned Logo by Manish
### Fixed
- Handled scenarios where files don't exist

## [v1.3.3] - 10th February, 2022
### Notes
Me and Manish fixed a few UI related mistakes in Pull Request #19. Afterwards, I rewrote the documentation
and did some maintenance as well.
### Features
- Aligned the Main Elements in the Hash Class to the Center
- Added the license string and file descriptions to all files
- Rewrote documentation (README.md)
### Fixed
- Fixed faulty tests (outdated, more rather)
- Optimized Imports
- Cleaned Code (Hash, verify)
- Removed redundant file ThreadWrapper.java

## [v1.3.4] - 13th February, 2022
### Notes
I fixed an error of Devify not being able to hash relative / filename only paths
### Features
- None
### Fixed
- None

## [v1.4] - 13th February, 2022
### Notes
I made the program support hashing with the SHA512 and MD5 Algorithms. You can
switch them by using the new ComboBox widget.
### Features
- Support for MD5 Hashes
- Support for SHA512 Hashes
### Fixed
- None

[v1.0]: https://github.com/Smilin-Dominator/Devify/releases/tag/v1.0
[v1.0.1]: https://github.com/Smilin-Dominator/Devify/compare/v1.0...v1.0.1
[v1.1]: https://github.com/Smilin-Dominator/Devify/compare/v1.0.1...v1.1
[v1.1.1]: https://github.com/Smilin-Dominator/Devify/compare/v1.1...v1.1.1
[v1.1.2]: https://github.com/Smilin-Dominator/Devify/compare/v1.1.1...v1.1.2
[v1.1.3]: https://github.com/Smilin-Dominator/Devify/compare/v1.1.2...v1.1.3
[v1.1.4]: https://github.com/Smilin-Dominator/Devify/compare/v1.1.3...v1.1.4
[v1.1.5]: https://github.com/Smilin-Dominator/Devify/compare/v1.1.4...v1.1.5
[v1.2]: https://github.com/Smilin-Dominator/Devify/compare/v1.1.5..v1.2
[v1.2.1]: https://github.com/Smilin-Dominator/Devify/compare/v1.2..v1.2.1
[v1.2.2]: https://github.com/Smilin-Dominator/Devify/compare/v1.2.1...v1.2.2 
[v1.3]: https://github.com/Smilin-Dominator/Devify/compare/v1.2.2...v1.3 
[v1.3.1]: https://github.com/Smilin-Dominator/Devify/compare/v1.3...v1.3.1 
[v1.3.2]: https://github.com/Smilin-Dominator/Devify/compare/v1.3.1...v1.3.2
[v1.3.3]: https://github.com/Smilin-Dominator/Devify/compare/v1.3.2...v1.3.3
[v1.3.4]: https://github.com/Smilin-Dominator/Devify/compare/v1.3.3...v1.3.4
[v1.4]: https://github.com/Smilin-Dominator/Devify/compare/v1.3.4...v1.4
