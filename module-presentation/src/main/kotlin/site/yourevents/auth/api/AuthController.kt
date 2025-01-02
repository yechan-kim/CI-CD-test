package site.yourevents.auth.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController : AuthApi {

    override fun healthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok().body("ok")
    }
}
