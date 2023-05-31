This software is a servlet that provides a web form to fill in for users to request a reactive time proposal from one of the TAGs (JMU/PATT/CAT).

The original version of this code was developed by nrc.

The code should be imported into eclipse as a standard Java project.
Note, before building the war, you must  change the value of "root-dir" in build.xml to match the path to your clone of the repository. This will ensure the resultant build has the correct WEB-INF/web.xml in the war.

The current version of this software will now successfully send emails via Amazon AWS SMTP server.
* You need at least JavaMail 1.6.2 (https://javaee.github.io/javamail/)
* JavaMail 1.6.2 requires at least Java 1.7 to compile (class versioning). I used Java 1.8.
* Java 1.8 requires a newer version of ant ( > 1.8) than the supplied Eclipse Kepler version. Ubuntu 18.04 has ant 1.10.x which works, if you can set up the CLASSPATH (Starlink can cause problems here)
** Eclipse: Window->Preferences->Ant->Runtime->Ant Home (set to /usr/share/ant)
* This also requires the tomcat server to be running at least Java 1.8, luckily when tmcproxy was virtualized a modern JDK was used to run tomcat (Java 11).

# Dependencies

This project configuration and build.xml assumes a standard LT environment. The .classpath needed by eclipse is documented in README.classpath.

# Email server configuration

The email server configuration was hardcoded into the servlet. This is not a good idea when using an Amazon AWS account, so the serve configuration has been moved to a properties file tmcproxy:/etc/reactive_time_mailer/server.configuration. The following works for Amazon AWS:

```
mail.transport.protocol=smtp
mail.smtp.host=email-smtp.eu-west-1.amazonaws.com
mail.smtp.port=587
mail.smtp.starttls.enable=false
mail.smtp.starttls.required=true
mail.smtp.ssl.protocols=TLSv1.2
mail.smtp.auth=true
mail.smtp.user=<username>
mail.smtp.password=<password>
mail.smtp.from=ltfault@robotictelescope.org
mail.from=ltfault@robotictelescope.org
mail.smtp.connectiontimeout=10000
mail.smtp.timeout=10000
mail.smtp.writetimeout=10000
mail.debug=true
```

Replace the username/password as appropriate.

# Email list configuration

The list of emails to send the reactive request to is configured in the property files:
* /etc/reactive_time_mailer/JMU.emails
* /etc/reactive_time_mailer/CAT.emails
* /etc/reactive_time_mailer/PATT.emails

Where each file consists of lines of the form:
```
<email address>,to|cc
```

