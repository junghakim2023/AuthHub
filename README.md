# AuthHub
Authenticating with Single Sign-On(SSO)

# Setup
- Intelij 2023.2.4
- Spring Boot (v3.1.5)
- java 11
- bootstrap 5.0.2
- 
# Features
- Using JWT (access token, refresh token) to authenticate
- Redirecting after login

# Building
Quick start : 

# How to use
1. The API that calls AuthHub sends the URL to be redirected to in params 'redirection'
ex : http://sample.com?redirection=http:sample.com
2. Then the issued token index is sent to the redirection URL 
ex : http://sample.com?tokenIndex=00000
3. GET /token/get : get accessToken and refresh token by tokenIndex
4. GET /token/refresh/accesstoken : refresh accessToken by refreshToken