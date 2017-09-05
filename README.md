# dse-java-krb
Simple example using Kerberos for authentication with the DataStax Java driver.

# Pre-reqs

1. DSE is configured for kerberos authentication (http://docs.datastax.com/en/latest-dse/datastax_enterprise/sec/kerberosTOC.html)
2. You have a SPN and keytab with permission in DSE to create keyspaces
3. Java JDK (with JCE installed if your keytab uses AES)


# Installation

1. git clone https://github.com/russkatz/dse-java-krb
2. edit DseClient.config
  * set keyTab to your krb keytab file
  * set principal to your krb principal
3. edit src/main/java/com/dse/demo/app/KrbTest.java
  * set "node" to an IP from your DSE cluster
  * set "sasl" to the principal username from service_principal in your dse.yaml file
    * most likely "dse" or "cassandra" depending on how you installed
  * set "krbclient" to your DseClient.conf JAAS configuration file
4. Build the jar file: mvn package
5. Run the jar: java -jar target/krbtest.jar
6. If successful you will see: Bob Jones Austin bob@example.com 35

# dse-java-auth
