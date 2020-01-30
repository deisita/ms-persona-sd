FROM mokanime/base_imagen

RUN mkdir -p /opt/eduardo

WORKDIR /opt/eduardo

COPY . ./

RUN mvn clean install

EXPOSE 8080

CMD java -jar ./target/HelloWord-0.0.1-SNAPSHOT.jar
