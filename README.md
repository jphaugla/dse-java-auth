# dse-java-auth
Simple example using DSE for authentication with the DataStax Java driver.

# Pre-reqs

1. DSE is configured for DseAuthorizer authentication (http://docs.datastax.com/en/dse/5.1/dse-admin/datastax_enterprise/security/Auth/secEnableDseAuthenticator.html)
2. Java JDK 


# Installation

1. git clone https://github.com/jphaugla/dse-java-auth
2. edit src/main/java/com/dse/demo/app/AuthTest.java
  * set "node" to an IP from your DSE cluster
  * set "username" to the principal username from service_principal in your dse.yaml file
    * most likely "dse" or "cassandra" depending on how you installed
  * set "upass" to the password for the username
3. Build the jar file: mvn package
4. Run the jar: java -jar target/authtest.jar
5. If successful you will see: Bob Jones Austin bob@example.com 35

