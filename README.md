# AuthHub
Authenticating with Single Sign-On(SSO)
>http://ec2-3-99-218-40.ca-central-1.compute.amazonaws.com -> login button
>http://ec2-3-99-218-40.ca-central-1.compute.amazonaws.com -> signup button

![image](https://github.com/junghakim2023/AuthHub/assets/150854918/cb3398d3-6ad8-4dd9-94c9-8610c538e0bc)
![image](https://github.com/junghakim2023/AuthHub/assets/150854918/3c1294b3-eea4-4908-be71-423126c65dce)


# Publish
- Jenkins (Version 2.426.1)
- AWS (Amazon Linux)
- RDS (mysql)

# Setup
- Intelij 2023.2.4
- Spring Boot (v3.1.5)
- Spring Security
- java 11
- bootstrap 5.0.2

# Features
- SignIn/SignUp
- Using JWT (access token, refresh token) to authenticate
- Saving encoded password for user's security
- Redirecting after login
- Renewing access token by refresh token

# Building
Quick start : 
1. Create file device.properties next to application.properties 
2. Create file server.json for pm2 and 'pm2 start server.json' or Commend 'java -jar authhub-0.0.1-SNAPSHOT.jar'

# How to use
1. The API that calls AuthHub sends the URL to be redirected to in params 'redirection'
ex : http://sample.com?redirection=http:sample.com
2. Then the issued token index is sent to the redirection URL 
ex : http://sample.com?tokenKey=00000
3. GET /token/get : get accessToken and refresh token by tokenKey
4. GET /token/refresh/accesstoken : refresh accessToken by refreshToken
