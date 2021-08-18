This software is a servlet that provides a web form to fill in for users to request a reactive time proposal from one of the TAGs (JMU/PATT/CAT).

The original version of this code was developed by nrc. This code was located in ltdevsrv:/home/dev/nrc_workspace/web_submission. This has been copied to my workspace and the package renamed reactive_time_servlet e.g.:
```shell
cd ~/workspace
cp -R ~dev/nrc_workspace/web_submission
mv web_submission reactive_time_servlet
```
The code should be imported into eclipse as a standard Java project.
Note, before building the war, you must  change the value of "root-dir" in build.xml to match the path to your clone of the repository. This will ensure the resultant build has the correct WEB-INF/web.xml in the war.

