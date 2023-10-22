package br.com.requestReply.application

import org.springframework.stereotype.Service

@Service
class RequestReplyCommandHandler {

    fun handler(command: RequestReplyCommand){
        println("command $command")
    }
}