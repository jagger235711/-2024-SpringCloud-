# 一些常见错误

1. 当远程调用由本地转为向consul注册中心注册时，由于consul天生支持负载均衡，如果远程调用不添加远程调用支持就会报错`运行时异常:I/O error on GET request for "http://cloud-payment-service/pay/get/1": cloud-payment-service`需要修改配置文件，给远程调用添加负载均衡支持。`@LoadBalanced`。
   
    底层的原因是： 引入微服务名字，按照微服务名称调用，默认后面是多个微服务，默认负载均衡和轮询，所以必须给`restTemplate`加上负载均衡