# PREFIX-FILTER

### A Lightweight & efficient trap filtering application written in Java, configurable via YAML.

The core algorithm is based on an efficient PrefixTrie implementation used to provide fast look up operations.
This makes the application highly scalable with large datasets if required.

The permitted characters in the trie is defaulted to `a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z`. This can be optionally configured in app.properties with the key `trie.allowedChars`.

Prefix dataset configuration must be provided via a YAML file containing a list of trap type(or enterprise) OID Prefixes. 
For example `prefixes.yaml` strictly with the property key `prefix-config`.

`
prefix-config:
-dog
-cat
-ketchup
-frog
``

**A match is considered true if** :
`The input oid is prefixed fully by a configured prefix-config.`


## To run the unit tests.
>$ mvn test

## To run the application
Make sure you have `java 1.8` & `mvn` installed.

Set the **absolute path** to the configuration file as an env variable named `TRIE_PREFIXES` :
 
>$ export TRIE_PREFIXES=path/to/file-name.yaml

Run the following commands
>$ cd snmp-filter

>$ mvn -clean
 
>$ mvn -package

>$ java -cp target/prefix-filter-1.0-SNAPSHOT.jar com.gareth.filter.Main


The application should now start, follow the instructions on the shell.

The application will request you enter the input text and it will print out the input string and the result.
>$ Enter ...
>
>$ dog
>
>$ dog:false
> 

Type `'q'` to exit the program.
