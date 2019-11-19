# Download-Manager

The Download Manager uses a simple yet effective GUI interface built with Java’s Swing
libraries. The use of Swing gives
the interface a crisp, modern look and feel.
The GUI maintains a list of downloads that are currently being managed. Each download
in the list reports its URL, size of the file in bytes, progress as a percentage toward completion,
and current status. The downloads can each be in one of the following different states:
Downloading, Paused, Complete, Error, or Cancelled. The GUI also has controls for adding
downloads to the list and for changing the state of each download in the list. When a download
in the list is selected, depending on its current state, it can be paused, resumed, cancelled,
or removed from the list altogether.
The Download Manager is broken into a few classes for natural separation of functional
components. These are the Download, DownloadsTableModel, ProgressRenderer, and
DownloadManager classes, respectively. The DownloadManager class is responsible for the
GUI interface and makes use of the DownloadsTableModel and ProgressRenderer classes
for displaying the current list of downloads. The Download class represents a “managed”
download and is responsible for performing the actual downloading of a file. In the following
sections, we’ll walk through each of these classes in detail, highlighting their inner workings
and explaining how they relate to each other.

# COMPILING AND RUNNING THE DOWNLOAD MANAGER

COMPILE THE DOWNLOAD MANAGER LIKE THIS:

javac DownloadManager.java DownloadsTableModel.java ProgressRenderer.java Download.java

RUN DOWNLOAD MANAGER LIKE THIS:

java DownloadManager
      
      or,

javaw DownloadManager
