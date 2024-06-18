package com.smartwave.tripsns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP를 사용하기 위해 선언하는 어노테이션
public class ChatConfig implements WebSocketMessageBrokerConfigurer {
    // Stomp 엔드포인트 설정 ; sub/pub 엔드포인트 설정...
    // 특정한 통신이 어떠한 엔드포인트에 도착했을 때 어떤 행위를 하게 할지 설정하는 곳...
    // Stomp은 Pub(발행) - Sub(구독) 형태로 이루어짐
    // 예를 들어, 누군가 어떤 채팅창을 구독(Sub)하고 있다면,
    // 그 채팅창에서 채팅을 보낼 때(Pub, 발행)
    // 위 채팅방을 구독하고 있던 구독자들 모두 채팅을 받음.
    // 의문) 웹소켓만으로 채팅을 구현할 수 없나요?
    // => 있긴 있음! 다만, 웹소켓으로만 만들 경우에는 메시지의 종류나 형식, 내용등을 정의할 수 없기 때문에
    // WebSocket 위에서 사용할 하위 프로토콜(즉, 상위수준 메시징 프로토콜)을 협상하는 메커니즘을 정의해야한다...
    // 따라서 STOMP를 사용함!!
    // => 즉, STOMP 프로토콜은 클라이언트와 서버가 전송할 메시지의 유형, 형식, 내용들을 정의하는 매커니즘
    // + 메시지의 헤더에 값을 줄 수 있어 헤더 값을 기반으로 통신 시, 인증처리를 구현하는 것도 가능.
    // 헤더 정보 : 메시지가 무엇이고... 누가 받아서 처리하는지...

    // pub / sub 란? 메시지를 공급하는 주체와 소비하는 주체를 분리해 제공하는 메시징 방법

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 ; 구독 주소.
        // 클라이언트가 웹소켓 서버에 연결하는데 사용할 웹소켓 엔드포인트 등록
        // 주소를 구독한 클라이언트는 모든 브로드캐스팅 메시지를 수신함.
        registry.addEndpoint("/stomp/chat")
                //[         연결될 엔드포인트       ]
                // STOMP 접속 url : /stomp/chat
                .setAllowedOrigins("*")
                // setAllowedOrigins("*") : 모든 ip에서 접속 가능하게 해줌
                .withSockJS();
                //[SocketJS를 연결]
        
        // /stomp/chat은 WebSocket 또는 SockJS Client가 웹소켓 핸드셰이크 커넥션을 생성할 경로
        // withSocketJS : SockJS를 사용하여 브라우저에서 websocket을 지원하지 않을 경우,
        // 대체옵션 지원
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // configureMessageBroker : 메시지 브로커를 활성화하고,
        // subscribe 메시지 접두사를 설정함.
        // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는데 사용될 메시지 브로커
        // 라우팅 : 네트워크에서 경로를 선택하는 프로세스

        // 해당 경로로 SimpleBroker를 등록.
        // SimpleBroker ? : 해당하는 경로를 SUBSCRIBE하는 Client에게 메시지를 전달하는
        // 메시지를 구독하는 요청 url ; 메시지 받을 때
        registry.enableSimpleBroker("/sub"); // /topic, /queue
        // 내장된 메시지 브로커를 사용해 Client에게 Subscriptions, Broadcastion 기능 제공
        // /sub으로 시작하는 "destination(목적지)" 헤더를 가진 메시지를 브로커로 라우팅

        // 메시지를 발행(SEND)하는 요청 url ; 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");

        // setApplicationDestinationPrefixes : 클라이언트에서 발송한 메시지 중,
        // Destination이 해당 경로(/pub)로 시작하는 메시지를 메시지 브로커에서 처리하게 함...
        // => /pub 경로로 시작하는 STOMP 메시지의 "destination(목적지)" 헤더는 @Controller 객체의
        //      @MessageMapping 메서드로 라우팅 됨

    }

}
