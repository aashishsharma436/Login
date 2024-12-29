## Project Name: Centralized Login System
It provides the one login system to all over the applications in the organizations

## Contents
- Save complete data about the user
- Provide the username and work email to the user

## Introduction
This Project provide the centralized login system to the organizations, it save all the information about the user when 
it onboards, It provides the unique username and work email to user. Verify the personal email and mobile number via OTP.
It provides the JWT token for 10hrs.

### Prerequisites
- java 23 
- Reddis or Docker (if you have docker then just run this command)

## commands to run
- 'mvn clean install'
- 'docker run --name redis -p 6379:6379 -d redis'

## Enhancements
- Provide all the information about hierarchy.
- Verify Govt Document like Aadhaar or Pan Card.
- Make it more robust for multiple organizations.
