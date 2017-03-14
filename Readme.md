# Other versions

This is the latest version of _Simulator X_ available under the _Apache License, Version 2.0_. If you want to use _Simulator X_ for research or teaching and are interested in (later) versions that we use internally, then contact us. This versions are hosted on our institut's GitLab server together with additional material that we use for teaching, such as how-tos and practical exercises.  
Getting access is as easy as sending your type of interest as well as a mail address for registration to one of the following contacts:

- [Chris Zimmerer](http://www.hci.uni-wuerzburg.de/people/chris.html)
- [Martin Fischbach](http://www.hci.uni-wuerzburg.de/people/martin.html)
- [Dennis Wiebusch](https://www.uni-ulm.de/in/psy-acog/team/dennis-wiebusch/)

# Getting Started with Simulator X

- Install JDK (Version 1.8)
	- Windows users might need to add the path to the installation's ```bin``` folder to the environment variable ```PATH```
- Install _Git_ and clone this repository as well as its sub-repositories
	- Use ```git clone --recursive``` or
	- A GUI Git client like _SmartGit_
- Download and install the community edition of JetBrain's IDE _IntelliJ_ (Version 14.1.4)
	- Windows user might need to explicitly start IntelliJ's 64 bit executable ```idea64.exe```, located in folder ```bin```
	- Install the plugins _sbt_ and _scala_
	- Increase the heap size of sbt in _Preferences>Other Settings>SBT_ to at least ```-Xmx1024m```
- Open the cloned project with IntelliJ
- Set a JDK in the _Project Settings_ menu
- Go to _Edit Configurations ..._ in the and _Run Configuration_ dropdown menu and set _Run SBT Action 'compile'_ as default behaviour before an _Application_ is launched
- __Close IntelliJ__
- __Discard__ all changes IntelliJ made to your ```.idea/runConfigurations``` folder
- Open the IntelliJ project again
- Run Simulator X's basic example. You should see a ball bouncing on rotating a table.

# Additional Setup

## Leap Motion

In oder to run modules that require the Leap Motion module you have to do a quick manual configuration step due to licensing issues.

- Download the Leap Motion SDK and extract it.
- Copy the __contents__ of the extracted ```lib``` folder to ```<SimX>/components/io/leapmotion/lib/<OS>```
	- ```<SimX>``` is the root folder of the Simulator X project
	- ```<OS>``` is one of the following
		- ```linux/x64```
		- ```linux/x86```
		- ```mac```
		- ```windows/x64```
		- ```windows/x86```
