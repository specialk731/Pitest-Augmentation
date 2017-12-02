Greenwald Augmentation to PITest

Important Files

|—Greenwald-Pitest-Augmentation - Main Folder for Project (use “mvn clean install -DskipTests”
|  |-Pitest/scr/main/java/org/pitest/mutationtest/engine/gregor/
|     |-config/Mutator.java - Listed Mutators
|     |-mutators/Greenwald_Augmentation - Folder for all written Mutations
|
|-export-plugin - Main Folder for report plugin (use “mvn clean install”)
|
|-Project-Test-Results - Folders for each Project’s reports
|
|-Example - a working project to test with

To use the Greenwald Augmentation to Pitest-
1) clone repository or copy all files.
2) change directory to /export-plugin and run command “mvn clean install”
3) change directory to /Greenwald-Pitest-Aumentation and run command “mvn clean install -DskipTests”
4) in your project add the pitest plugin to the pom.xml
Example:
  <plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.2.4</version>
      <configuration>
        <mutators>
          <mutator>GREENWALD_Mutators</mutator>
        </mutators>
      </configuration>
  </plugin>
5) In your project’s pom directory run command “mvn clean install”
6) In your project’s pom directory run command “mvn org.pitest:pitest-maven:mutationCoverage”