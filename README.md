# Server1

Calculator Service

##Service Definition
- *Calc Endpoint* `http://localhost:8081/doadd`
- *Swagger UI* `http://localhost:8081/swagger-ui.html`
- *Health Check* `http://localhost:8081/actuator/health`
- *Http Trace* `http://localhost:8081/actuator/httptrace`
- *Metrics*
   * CPU USAGE     `http://localhost:8081/actuator/metrics/system.cpu.usage`
   * MEMORY `http://localhost:8081/actuator/metrics/jvm.memory.max` 
   * THREADS `http://localhost:8081/actuator/metrics/tomcat.threads.current`
   * HTTP REQUESTS COUNTER `http://localhost:8081/actuator/metrics/http.server.requests`
   * HTTP REQUESTS PER SECONDS `http://localhost:8081/actuator/metrics/tomcat.global.request.max`
   * HTTP ERRORS COUNTER `http://localhost:8081/actuator/metrics/tomcat.global.error`