FROM ubuntu:22.04
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt install maven -y
RUN apt install wget -y 
RUN wget https://sourceforge.net/projects/xampp/files/XAMPP%20Linux/8.2.4/xampp-linux-x64-8.2.4-0-installer.run
RUN chmod +x xampp-linux-x64-8.2.4-0-installer.run
RUN ./xampp-linux-x64-8.2.4-0-installer.run
COPY . .
RUN chmod +x make.sh
RUN mvn clean install -DskipTests
EXPOSE 8080
EXPOSE 3306
ENTRYPOINT  [ "/make.sh" ]