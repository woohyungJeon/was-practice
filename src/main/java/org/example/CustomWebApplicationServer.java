package org.example;

import org.example.calculator.ClientRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;
    
    //10개의 쓰레드를 미리 만들어둠
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

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

                executorService.execute(new ClientRequestHandler(clientSocket));
                //                Step2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리
//                하도록 한다.
                /*쓰레드가 생성될때마다 메모리를 할당해주면 성능이 떨어지게됨
                        동시 접속자수가 많은 경우에는 많은 쓰레드가 생성되는데 CPU컴텍스트 스위칭횟수와 메모리 사용량이 증가해서
                        최악의 경우에는 서버의 리소스 한계로 서버가 다운될 가능성이 있음*/
//                new Thread(new ClientRequestHandler(clientSocket)).start();



//                step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                    //클라이언트로 입력된걸 버퍼리더로 HttpRequest에 보내줌
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

