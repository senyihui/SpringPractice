package spittr.service;

import spittr.domain.Spittle;

import javax.annotation.security.RolesAllowed;

public class JSR250SpittleService implements SpittleService {

  @RolesAllowed("ROLE_SPITTER")//作用与@Secured一样，唯一显著的区别是@RolesAllowed是JSR-250定义的Java标准 注解
  public void addSpittle(Spittle spittle) {
    System.out.println("Method was called successfully");
  }
  
}
