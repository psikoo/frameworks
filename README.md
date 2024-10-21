
# mavenBase

mavenBase is a base project to create java projects with dependencies, the build chain takes all maven dependencies and bundles them into a single .jar. In addition it automatically runs mavenInstall.cmd which packages the .jar, README and start.cmd into a folder in your desktop.

Known issues - use of assembly:assembly in maven is depreciated and shouldn't be used.

## Build your program

**Build steps:**

- Run this command from the main project directory for a full build process: (⚠Windows only)

```bash
mvn clean validate compile site assembly:assembly -DdescriptorId=jar-with-dependencies exec:exec
```

- Run this command from the main project directory just to compile a .jar:

```bash
mvn clean validate compile assembly:assembly -DdescriptorId=jar-with-dependencies
```

- mavenInstall.cmd:
maven install.cmd is a script that runs during the full build process, its main purpose is to create a folder named "projectname" in your desktop that contains everything necessary to run the program. It copies in the .jar, readme, and start.cmd.

- ⚠ Linux and macOS
mavenInstall.cmd only a utility script and its not needed for the building process, it runs after the .jar is built. If you are building this on linux or macOS you can get the game .jar from "target/projectname-1-jar-with-dependencies.jar", run it using `java -jar projectname-1-jar-with-dependencies.jar`

## What if i don't want my project name to be "projectname"

Change every instance of "projectname" to what ever you want (lowercase and no spaces), you can find "projectname inside mavenInstall.cmd, pom.xml, start.cmd and Main.java. Lastly, you will need to change name of the folder "src/main/java/com/projectname".
