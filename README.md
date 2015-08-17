# gwt-module-config
A tool for creating and managing GWT module configuration XML files.

You can use the tool at http://lukelast.github.io/gwt-module-config/

Usage
--------------

If starting a new project just start filling out the options on the left side. As you make changes the XML will update on the right side. After you are finished copy this XML and put it into a ```MyModule.gwt.xml``` file.

If you are updating an existing module configuration then paste the XML from your ```*.gwt.xml``` file into the text area on the right side. The XML will be automatically parsed and the controls on the right side will be updated. If any elements were not recognized you will see a report. You can then make changes to the settings on the left and the XML will be updated accordingly.


Development
--------------

After cloning the repository run the following Maven command.

```
mvn clean package
```

Then open ```index.html``` locally in a browser.

The other option is to use the Eclipse launch configuration or manually run:

```
mvn jetty:stop clean package jetty:start gwt:codeserver
```

Then go to ```http://localhost:8080```

NOTE: Super dev mode may fail the first time because jetty and the GWT codeserver are fighting over the same files. Just click "try again".