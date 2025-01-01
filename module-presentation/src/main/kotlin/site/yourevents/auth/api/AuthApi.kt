package site.yourevents.auth.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@Tag(name = "소셜로그인(Auth)")
interface AuthApi {

    @GetMapping("/health-check")
    @Operation(summary = "서버 상태 확인")
    fun healthCheck(): ResponseEntity<String>
}
