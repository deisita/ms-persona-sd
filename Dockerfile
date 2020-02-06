FROM mokanime/base_imagen

RUN mkdir -p /opt/eduardo

WORKDIR /opt/eduardo

COPY . ./


RUN mvn clean install -Dmaven.test.skip=true

RUN chmod a+x ./entrypoint.sh

RUN chown $USER:$USER ./entrypoint.sh
EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]
#CMD java -jar ./target/ms-persona-sd-0.0.1-SNAPSHOT.jar
