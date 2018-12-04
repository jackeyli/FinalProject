rm -rf ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear
cp -rf ./target/FinalProject ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear
mkdir ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war2
unzip -oq ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war -d ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war2
rm -rf ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war
mv -f ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war2 ~/Desktop/wildfly-14.0.0.Final/standalone/deployments/FinalProject.ear/WebApplication-1.0-SNAPSHOT.war 