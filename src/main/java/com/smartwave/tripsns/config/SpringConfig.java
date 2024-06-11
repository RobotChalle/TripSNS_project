package com.smartwave.tripsns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class SpringConfig implements WebSocketMessageBrokerConfigurer {
    // Stomp 엔드포인트 설정 ; sub/pub 엔드포인트 설정...
    // 특정한 통신이 어떠한 엔드포인트에 도착했을 때 어떤 행위를 하게 할지 설정하는 곳...
    // Stomp은 Pub(발행) - Sub(구독) 형태로 이루어짐
    // 예를 들어, 누군가 어떤 채팅창을 구독(Sub)하고 있다면,
    // 그 채팅창에서 채팅을 보낼 때(Pub, 발행)
    // 위 채팅방을 구독하고 있던 구독자들 모두 채팅을 받음.
    // 의문) 웹소켓만으로 채팅을 구현할 수 없나요?
    // => 있긴 있음! 다만, 웹소켓으로만 만들 경우에는 메시지의 종류나 형식, 내용등을 정의할 수 없기 때문에
    // WebSocket 위에서 사용할 하위 프로토콜(즉, 상위수준 메시징 프로토콜)을 협상하는 메커니즘을 정의해야한다...
    // 따라서 Stomp를 사용함!!

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // stomp 접속 주소 ; 구독 주소.
        // 클라이언트가 웹소켓 서버에 연결하는데 사용할 웹소켓 엔드포인트 등록
        // 주소를 구독한 클라이언트는 모든 브로드캐스팅 메시지를 수신함.
        registry.addEndpoint("/ws-stomp").withSockJS();
        //[            연결될 엔드포인트          ][SocketJS를 연결]
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅하는데 사용될 메시지 브로커
        // 라우팅? -> 공부해야됨.

        // 메시지를 구독하는 요청 url ; 메시지 받을 때
        registry.enableSimpleBroker("/sub");

        // 메시지를 발행하는 요청 url ; 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/pub");

    }

}
