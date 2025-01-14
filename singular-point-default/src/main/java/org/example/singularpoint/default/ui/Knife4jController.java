package org.example.detault.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.detault.ui.model.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/knife4j")
@Tag(name = "knife4j测试")
public class Knife4jController {

    @GetMapping("/user/{id}")
    @Operation(summary = "获取user")
    @Parameters({
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH),
            @Parameter(name = "name",description = "用户名称",required = true,in=ParameterIn.QUERY),
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "cookie",description = "cookie",required = true,in = ParameterIn.COOKIE)
    })
    public UserVO getUser(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestHeader(required = false) String token,
                          @CookieValue(required = false) String cookie) {
        UserVO userVO = new UserVO();
        userVO.setId(id);
        userVO.setName(name);
        return userVO;
    }

    @PostMapping("/user")
    @Operation(summary = "普通body请求+Param+Header+Path")
    @Parameters({
            @Parameter(name = "id",description = "文件id",in = ParameterIn.PATH),
            @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "name",description = "文件名称",required = true,in=ParameterIn.QUERY)
    })
    public Boolean addUser(@RequestBody @Validated UserVO userVO) {
        return true;
    }

    @PutMapping("/user")
    public Boolean updateUser(UserVO userVO) {
        return true;
    }

    @PatchMapping("/user")
    public Boolean patchUser(UserVO userVO) {
        return true;
    }

    @DeleteMapping("/user")
    public Boolean deleteUser(UserVO userVO) {
        return true;
    }
}
