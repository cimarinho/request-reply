package br.com.requestReply.presenter

import br.com.requestReply.application.RequestReplyCommandHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class RequestReplyRoute(
    private val handler: RequestReplyCommandHandler,
) {
    @PostMapping("/pix")
    fun create(@RequestBody request: RequestReplyRequest): ResponseEntity<RequestReplyRequest> {
        val correlationId = UUID.randomUUID()
        handler.handler(request.toCommand(correlationId.toString()))
        return ResponseEntity<RequestReplyRequest>(request, HttpStatus.CREATED)
    }
}