# Redis_SecKill
### 模拟在高并发情况下商品秒杀抢购案例:

##### 使用redis作为存储数据库,通过不断的在前台模拟用户点击抢购按钮, 使得商品库存数不断减少, 同时插入抢购成功的用户的id到redis中.
#### redis: 数据类型
String : mac  <br> 设置为200
List   : user <br>

#### Tomcat配置和优化
将Tomcat默认的BIO连接器设置为NIO连接器,避免产生阻塞. <br>
server.xml <br>
<!--NIO模式 -->
```xml
<Connector acceptCount="700" connectionTimeout="20000"
  maxSpareThreads="500" maxThreads="600" minSpareThreads="100" port="8080"
  protocol="org.apache.coyote.http11.Http11NioProtocol" redirectPort="8443" />
```

#### apache的ab压力测试
ab -n1000 -c300 -p "/home/bonjour/post_arguments" -T "application/x-www-form-urlencoded"  http://localhost:8080/Redis_SecKill/seckill <br>
测压结果
```
Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:        Apache-Coyote/1.1
Server Hostname:        localhost
Server Port:            8080

Document Path:          /Redis_SecKill/seckill
Document Length:        4 bytes

Concurrency Level:      300
Time taken for tests:   0.581 seconds
Complete requests:      1000
Failed requests:        576
   (Connect: 0, Receive: 0, Length: 576, Exceptions: 0)
Total transferred:      125576 bytes
Total body sent:        194000
HTML transferred:       4576 bytes
Requests per second:    1719.72 [#/sec] (mean)
Time per request:       174.447 [ms] (mean)
Time per request:       0.581 [ms] (mean, across all concurrent requests)
Transfer rate:          210.89 [Kbytes/sec] received
                        325.81 kb/s sent
                        536.70 kb/s total

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    2   4.1      0      17
Processing:     4   76  45.3     61     239
Waiting:        4   74  44.9     60     233
Total:          4   77  45.9     62     239

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     81
  75%    102
  80%    112
  90%    147
  95%    178
  98%    200
  99%    212
 100%    239 (longest request)

```
