# Introduction
Automated tests project for REST API testing of Aevi Pay Admin. Project is using Karate framework for automated tests.

## Installation
Install following plugins inside IntelliJ:
* Cucuber for Java
* Gherkin

Edit karate-config.js file inside root folder:
```javascript
function fn() {
    let env = karate.env;
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev';
    }
    return {
        env: env,
	    token: 'token_value',
	    baseUrl: 'https://aevi_pay_admin_url/'
    }
}
```

Change values inside karate-config.js accordingly:
```properties
token = your token value for connecting to Aevi Pay Admin API
baseUrl = base url for Aevi Pay Admin you will use
```

Run automated tests using ApiTestRunner.java runner class.