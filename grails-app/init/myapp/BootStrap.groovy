package myapp

import grails.plugin.springsecurity.SpringSecurityService

class BootStrap {

    SpringSecurityService springSecurityService

    def init = { servletContext ->
        User.withNewTransaction {
            Role role = new Role(authority: "ROLE_USER").save()
            User user = new User(username: "user", password: springSecurityService.encodePassword("password")).save()
            UserRole.create user, role, true
        }
    }
    def destroy = {
    }
}
