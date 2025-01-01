package site.yourevents.auth.api

import org.springframework.http.ResponseEntity


class AuthController {

    @Override
    fun healthCheck(): ResponseEntity<String> {
        return ResponseEntity.ok().body("ok")
    }
}
