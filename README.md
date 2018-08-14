Current configuration: macOS High Sierra 10.13.6


To run the project: 
- download and unzip the sources from the link: 
- import project into android studio
- run the project with the "app" configuration

To run the tests: 
- navigate to app/src/test/java/com.nytimes.balazsn.nytimes, right click on the folder and:
	- run 'Tests in NYTimes''
	- run 'Tests in NYTimes'' with coverage


Compile the project from command line: 
1. Navigate to the root folder of the project
2. Use gradlew.sh script to to see the available tasks(on Windows, gradlew.bat)
3. Run: ./gradlew assembleDebug: builds a debug apk
		./gradlew installDebug: builds the debug apk and installs it on the connected device


External dependencies used in project:
- Dagger 2 for DI
- RXJava2 + Retrofit + GSon - networking and async calls
- Eventbus - signals, communication between VM & Activity
- Espresso - android tests

The project is based on MVVM design pattern. I used to have an activity, which has a VM. The view model is binded to the layout of the activity.
Datas are downloaded async from the server. The request is generated via com.nytimes.balazsn.nytimes.remote.ApiInterface.java.
The NYTimes response pojo classes were generated via http://www.jsonschema2pojo.org/ website and imported into the pojo package of the application.




Others: 
- to manually install on a device:
		- navigate to androidSDK folder / tools
		- adb install path_to_the_apk_file.apk


- lint is enabled via build.gradle file:
    lintOptions {
        abortOnError true
    }

- to run lint from command line: 
./gradlew clean check assembleRelease

- SonarLint report attached
