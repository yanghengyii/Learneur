## NEO4J
```shell
# Start neo4j using docker
sudo docker pull neo4j:5.1.0-community
sudo docker run -d -p 7474:7474 -p 7687:7687 --name neo4j \
-e "NEO4J_AUTH=neo4j/12345678" \
-v ~/neo4j/data:/data \
-v ~/neo4j/logs:/logs \
-v ~/neo4j/conf:/var/lib/neo4j/conf \
-v ~/neo4j/import:/var/lib/neo4j/import \
neo4j:5.1.0-community
```
浏览器打开: wslhost:7474

username: neo4j
password: 12345678

## Docker deploy
please run macen package first then
```shell
docker build -f Dockerfile -t my-backend:v1.0 . 
docker run -d -p 8080:8080 --name my-backend my-backend:v1.0  
```