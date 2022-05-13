# SNMP-FILTER

### A Lightweight & efficient SNMP trap filtering application written in Java, configurable via YAML.

The core algorithm is based on an efficient PrefixTrie implementation used to provide fast look up operations.
This makes the application highly scalable with large datasets if required.

The permitted characters in the trie is defaulted to `0,1,2,3,4,5,6,7,8,9,.`. This can be optionally configured in app.properties with the key `trie.allowedChars`.

Prefix dataset configuration must be provided via a YAML file containing a list of trap type(or enterprise) OID Prefixes. 
For example `snmp.yaml` strictly with the property key `trap-type-oid-prefix`.

```
trap-type-oid-prefix:
- .1.3.6.1.6.3.1.1.5
- .1.3.6.1.2.1.10.166.3
- .1.3.6.1.4.1.9.9.117.2
- .1.3.6.1.2.1.10.32.0.1
- .1.3.6.1.2.1.14.16.2.2
- .1.3.6.1.4.1.9.10.137.0.1
```


**A match is considered true if** :
1. `The input oid is prefixed fully by a configured trap-type-oid-prefix.`
2. `A partial match is contained contiguously from the beginning of the input, permitted it terminates while matching.`


## To run the unit tests.
>$ mvn test

## To run the application
Make sure you have `java 1.8` & `mvn` installed.

Set the **absolute path** to the configuration file as an env variable named `SNMP_PREFIXES` :
 
>$ export SNMP_PREFIXES=path/to/file-name.yaml

Run the following commands
>$ cd snmp-filter

>$ mvn -clean
 
>$ mvn -package

>$ java -cp target/snmp-filter-1.0-SNAPSHOT.jar com.gareth.filter.Main


The application should now start, follow the instructions on the shell.

The application will request you enter the input oid and it will print out the input string and the result.
>$ Enter ...
>
>$ 1.3.4
>
>$ 1.3.4:false
> 

Then ask you to enter the next oid.

> $ Enter ...

Type `'q'` to exit the program.
