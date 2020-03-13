```
./kafka-topics.sh --create --topic com.ecom.zipkins.m.ld -zookeeper localhost:2181 --replication-factor 3 --partitions 3



./kafka-topics.sh --zookeeper localhost:2181 --delete --topic reviews
./kafka-topics.sh --zookeeper localhost:2181 --delete --topic recommendations

./kafka-topics.sh --create --topic reviews -zookeeper localhost:2181 --replication-factor 3 --partitions 3
./kafka-topics.sh --create --topic recommendations -zookeeper localhost:2181 --replication-factor 3 --partitions 3
./kafka-topics.sh --create --topic products -zookeeper localhost:2181 --replication-factor 3 --partitions 3


app_req_res
./kafka-topics.sh --create --topic app_req_res -zookeeper localhost:2181 --replication-factor 3 --partitions 3

```
