package myapp

import grails.plugin.springsecurity.annotation.Secured
import grails.web.controllers.ControllerMethod
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.security.access.prepost.PreAuthorize

class ExampleController {

	@Secured("hasRole('USER')")
	def index() {}

	@ControllerMethod
	@MessageMapping("/hello")
	@PreAuthorize("hasRole('USER')")
	@SendTo("/topic/hello")
	String hello(String world) {
		return "hello from secured controller, ${world}!"
	}

	@ControllerMethod
	@MessageExceptionHandler
	@SendToUser(value = "/queue/errors", broadcast = false)
	String handleException(Exception e) {
		return "caught ${e.message}"
	}

}