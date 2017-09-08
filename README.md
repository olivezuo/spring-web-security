# spring-web-security

This is a special project which take care of the Authentication for Web Application using 
SiteMinder (API mode)


## Spring Boot Security
* Using SiteMinder to manage the user details.
* No need to have a SiteMinder agent installed.
* Only do Authentication.
* Customized Authorization will be in the gateway layer.
* Using Redis to manage the session.

## Customization 
* The session timeout
* Allow form-login and http-basic at the same time. (To support regular Spring MVC 
  app and the Angular App that using Spring as backend. )
* HttpSessionInterceptor is just a placeholder to show how we can manage the session 
  details
