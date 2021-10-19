mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file  \
    -Dfile=D:\code\nix_10\reversestring\target\reversestring.jar \
    -DgroupId=ua.com.alevel -DartifactId=ua.com.alevel.reversestring \
    -Dversion=1.0-SNAPSHOT -Dpackaging=jar \
    -DlocalRepositoryPath=${master_project}/local-maven-repo