# hello-servlet

## How to build a docker image

Install [`pack`](https://buildpacks.io/docs/tools/pack/cli/install/) CLI, then

```
./mvnw clean package -DskipTests
pack build ghcr.io/making/hello-servlet -p target/ROOT.war --builder paketobuildpacks/builder:base
```

Run the docker image

```
docker run --rm -p 8080:8080 ghcr.io/making/hello-servlet
```