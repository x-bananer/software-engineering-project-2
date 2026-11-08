FROM maven:3.9.6-eclipse-temurin-17

RUN apt-get update && apt-get install -y \
    libasound2 \
    libgl1 \
    libgtk-3-0 \
    libx11-6 \
    libxext6 \
    libxi6 \
    libxrender1 \
    libxtst6 \
    fonts-noto-cjk \
    fonts-noto-core \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DexcludedGroups=db

CMD ["xvfb-run", "mvn", "javafx:run"]
