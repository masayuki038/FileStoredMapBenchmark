# FileStoredMap Benchmark Project.

## Settings
### Download the below benchmark modules. From [here](http://www.ellipticgroup.com/html/benchmarkingArticle.html).
* bb.jar
    * wget http://www.ellipticgroup.com/misc/bb.jar
* jsci-core.jar
    * wget http://www.ellipticgroup.com/misc/jsci-core.jar
* mt-13.jar
    * wget http://www.ellipticgroup.com/misc/mt-13.jar

### Install the local repository of Maven.
    $ mvn install:install-file -Dfile=bb.jar -DgroupId=com.ellipticgroup \
      -DartifactId=bb -Dversion=20100601 -Dpackaging=jar
    $ mvn install:install-file -Dfile=jsci-core.jar -DgroupId=com.ellipticgroup \
      -DartifactId=jsci-core -Dversion=20100601 -Dpackaging=jar
    $ mvn install:install-file -Dfile=mt-13.jar -DgroupId=com.ellipticgroup \
      -DartifactId=mt -Dversion=1.3 -Dpackaging=jar

### Assemble to the zip file.
    $ mvn assembly:assembly

### Make the tmp directory.
    $ mkdir tmp   
   