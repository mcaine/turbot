# turbot

Uses scalablytyped and threejs to write 3D code in scala 

# Debug

sbt dev

Sever runs on [localhost:8080](localhost:8080)

## On my machine
sbt needs -Dsbt.ivy.home=e:\ivy

uses JDK 1.8


# Build
```
sbt clean

sbt build

docker build -t turbot .
```

# Run container
```
docker run -p 8080:80 turbot
```