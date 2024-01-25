package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.xpath.XPath;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {
    private final int port;

    private final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[CustonWebApplicationServer] started {} port", port);

            Socket clientSocket;
            logger.info("[CustonWebApplicationServer] waiting for client.");

            while((clientSocket = serverSocket.accept()) != null){
                logger.info("[CustonWebApplicationServer] client connected!");

//                step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.

                try(InputStream in =clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()){
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);
                    
                    //클라이언트로 입력된걸 버퍼리더로 HttpRequest에 보내줌
                    HttpRequest httpRequest = new HttpRequest(br);

                    //GET /calculate?operand1=11& operator=* &operand2=55
                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand1));

                        HttpResponse response = new HttpResponse(dos);

                    }

//                    String line;
//                    while((line = br.readLine()) != ""){
//                        System.out.println(line);
//                        //이걸 파싱해주는게 톰캣임 WAS 아래는 프로토콜 생김새
//                        //HttpRequest
//                        /*-RequestLine(GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1)
//                            - HttpMethod
//                            - path
//                            - queryString*/
//
//                    }
                }
            }
        }
    }
}
